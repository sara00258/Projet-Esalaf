package com.exemple.model;

public class Produit {
    private Long id_produit;
    private String nom;
    private double prix;
    private int quantite;
    private ProductService productService;

    public Produit(Long id_produit, String nom, double prix) {
        this.id_produit = id_produit;
        this.nom = nom;
        this.prix = prix;
    }

    public Produit(String nom, double prix) {
        this.nom = nom;
        this.prix = prix;
    }
    public Produit() {
    }

    public Produit(Long id_produit, String nom, double prix, int quantite) {
        this.id_produit = id_produit;
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public Long getId_produit() {
        return id_produit;
    }

    public void setId_produit(Long id_produit) {
        this.id_produit = id_produit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
