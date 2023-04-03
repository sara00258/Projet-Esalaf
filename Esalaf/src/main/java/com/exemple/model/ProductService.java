package com.exemple.model;

import java.util.List;
import com.exemple.model.Produit;
import com.exemple.model.ProduitDAO;
import java.sql.SQLException;

public class ProductService {
    private ProduitDAO produitDAO;

    public ProductService(ProduitDAO produitDAO) {
        this.produitDAO = produitDAO;
    }

    public void saveProduct(Produit product) throws SQLException{
        produitDAO.save(product);
    }
    public void updateProduct(Produit product) throws SQLException {
        produitDAO.update(product);
    }

    public void deleteProduct(Produit product) throws SQLException {
        produitDAO.delete(product);
    }

    public Produit getProductById(Long id) throws SQLException {
        return produitDAO.getOne(id);
    }

    public List<Produit> getAllProducts() throws SQLException {
        return produitDAO.getAll();
    }
}
