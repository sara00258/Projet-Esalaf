package com.exemple.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO extends BaseDAO<Client> {
    public ClientDAO() throws SQLException {
        super();
    }

    @Override
    public void save(Client object) throws SQLException {

        String req = " insert into client (nom , telephone) values (? , ?);";

        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setString(1, object.getNom());
        this.preparedStatement.setString(2, object.getTelephone());

        this.preparedStatement.execute();

    }
    @Override
    public void update(Client objet) throws SQLException {

    }
    @Override
    public void delete(Client objet) throws SQLException {
    }
    @Override
    public Client getOne(Long id) throws SQLException {
        return null;
    }
    @Override
    public List<Client> getAll() throws SQLException {
        List<Client> mylist = new ArrayList<Client>();
        String req = "select * from client";
        this.statement = this.connection.createStatement();
        this.resultSet = this.statement.executeQuery(req);
        while (this.resultSet.next()) {

            mylist.add(new Client(this.resultSet.getLong(1), this.resultSet.getString(2),
                    this.resultSet.getString(3)));


        }
        return mylist;
    }

}
