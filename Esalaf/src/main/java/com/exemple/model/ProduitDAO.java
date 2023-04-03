package com.exemple.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAO extends BaseDAO<Produit>{
    public ProduitDAO() throws SQLException {
        super();
    }

    @Override
    public void save(Produit object) throws SQLException {
        String req = "INSERT INTO produit (nom, prix, quantite) VALUES (?, ?, ?);";
        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setString(1, object.getNom());
        this.preparedStatement.setDouble(2, object.getPrix());
        this.preparedStatement.setInt(3, object.getQuantite());

        this.preparedStatement.execute();

    }

    @Override
    public void update(Produit objet) throws SQLException {
        String req = "UPDATE produit SET nom = ?, prix = ?, quantite = ? WHERE id_produit = ?";
        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setString(1, objet.getNom());
        this.preparedStatement.setDouble(2, objet.getPrix());
        this.preparedStatement.setInt(3, objet.getQuantite());
        this.preparedStatement.setLong(4, objet.getId_produit());

        this.preparedStatement.executeUpdate();

    }

    @Override
    public void delete(Produit objet) throws SQLException {
        String req = "DELETE FROM produit WHERE id_produit = ?";
        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setLong(1, objet.getId_produit());

        this.preparedStatement.executeUpdate();

    }

    @Override
    public Produit getOne(Long id) throws SQLException {
        String req = "SELECT * FROM produit WHERE id_produit = ?";
        this.preparedStatement = this.connection.prepareStatement(req);

        int id_produit = 0;
        this.preparedStatement.setLong(1, id_produit);

        this.resultSet = this.preparedStatement.executeQuery();

        if (this.resultSet.next()) {
            return new Produit(this.resultSet.getLong(1), this.resultSet.getString(2), this.resultSet.getDouble(3),
                    this.resultSet.getInt(4));
        }
        return null;
    }

    @Override
    public List<Produit> getAll() throws SQLException {
        List<Produit> produits = new ArrayList<>();
        String req = "SELECT * FROM produit";
        this.statement = this.connection.createStatement();
        this.resultSet = this.statement.executeQuery(req);

        while (this.resultSet.next()) {
            produits.add(new Produit(this.resultSet.getLong(1), this.resultSet.getString(2), this.resultSet.getDouble(3),
                    this.resultSet.getInt(4)));
        }
        return produits;
    }


}
