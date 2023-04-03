package com.exemple.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreditDAO extends BaseDAO<Credit> {
    public CreditDAO() throws SQLException {
        super();
    }

    @Override
    public void save(Credit object) throws SQLException {
        String req = "INSERT INTO credit (montant, date_credit, id_client) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(req);
        preparedStatement.setDouble(1, object.getMontant());
        preparedStatement.setDate(2, java.sql.Date.valueOf(object.getDate_credit()));
        preparedStatement.setLong(3, object.getId_client());
        preparedStatement.executeUpdate();

    }

    public void addCredit(Long id, Double montant) throws SQLException {
        String req = "UPDATE credit SET montant = montant + ? WHERE id_credit = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(req);
        preparedStatement.setDouble(1, montant);
        preparedStatement.setLong(2, id);
        preparedStatement.executeUpdate();
    }
    @Override
    public void update(Credit objet) throws SQLException {
        String req = "UPDATE credit SET montant=?, date_credit=?, id_client=? WHERE id_credit=?";
        PreparedStatement preparedStatement = connection.prepareStatement(req);
        preparedStatement.setDouble(1, objet.getMontant());
        preparedStatement.setDate(2, java.sql.Date.valueOf(objet.getDate_credit()));
        preparedStatement.setLong(3, objet.getId_client());
        preparedStatement.setLong(4, objet.getId_credit());
        preparedStatement.executeUpdate();

    }

    @Override
    public void delete(Credit objet) throws SQLException {
        String req = "DELETE FROM credit WHERE id_credit=?";
        PreparedStatement preparedStatement = connection.prepareStatement(req);
        preparedStatement.setLong(1, objet.getId_credit());
        preparedStatement.executeUpdate();

    }
    @Override
    public Credit getOne(Long id) throws SQLException {
        String req = "SELECT * FROM credit WHERE id_credit=?";
        PreparedStatement preparedStatement = connection.prepareStatement(req);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return new Credit(
                    resultSet.getLong("id_credit"),
                    resultSet.getDouble("montant"),
                    resultSet.getDate("date_credit").toLocalDate(),
                    resultSet.getLong("id_client")
            );
        } else {
            return null;
        }
    }

    @Override
    public List<Credit> getAll() throws SQLException {
        List<Credit> credits = new ArrayList<>();
        String req = "SELECT * FROM credit";
        PreparedStatement preparedStatement = connection.prepareStatement(req);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Credit credit = new Credit(
                    resultSet.getLong("id_credit"),
                    resultSet.getDouble("montant"),
                    resultSet.getDate("date_credit").toLocalDate(),
                    resultSet.getLong("id_client")
            );
            credits.add(credit);
    }
        return credits;
    }
    public List<Credit> getAllForClient(Client client) throws SQLException {
        List<Credit> credits = new ArrayList<Credit>();
        String req = "SELECT * FROM credit WHERE id_client=?";
        PreparedStatement preparedStatement = connection.prepareStatement(req);
        preparedStatement.setLong(1, client.getId_client()!= null ? client.getId_client() : 0L); // check for null before calling getId_client()
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Credit credit = new Credit(
                    resultSet.getLong("id_credit"),
                    resultSet.getDouble("montant"),
                    resultSet.getDate("date_credit").toLocalDate(),
                    resultSet.getLong("id_client")
            );
            credits.add(credit);
        }
        return credits;
    }

    public List<Credit> getAllForProduit(Produit produit) throws SQLException {
        List<Credit> credits = new ArrayList<>();
        String req = "SELECT * FROM credit WHERE id_produit=?";
        PreparedStatement preparedStatement = connection.prepareStatement(req);
        preparedStatement.setLong(1, produit != null ? produit.getId_produit() : 0L);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Credit credit = new Credit(
                    resultSet.getLong("id_credit"),
                    resultSet.getDouble("montant"),
                    resultSet.getDate("date_credit").toLocalDate(),
                    resultSet.getLong("id_client")
            );
            credits.add(credit);
        }
        return credits;
    }

}





