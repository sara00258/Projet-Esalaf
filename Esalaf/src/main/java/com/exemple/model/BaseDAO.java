package com.exemple.model;

import java.sql.*;
import java.util.List;

public abstract class BaseDAO<T> {

    //Interfaces JDBC

    protected Connection connection;
    protected Statement statement;
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;

    private String url = "jdbc:mysql://localhost:3306/esalaf";
    private String login = "root";
    private String password ="";

    public BaseDAO() throws  SQLException{
        this.connection =DriverManager.getConnection(url , login , password);
    }

    public abstract void save (T object) throws SQLException;
    public abstract void update(T objet) throws SQLException;
    public abstract void delete(T objet) throws SQLException;
    public abstract T getOne(Long id) throws SQLException;
    public abstract List<T> getAll() throws SQLException;
}
