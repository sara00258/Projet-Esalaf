package com.exemple.model;

import java.sql.SQLException;
import java.util.List;
import java.time.LocalDate;
public class Test {

    public static void main(String[] args) throws SQLException {

        try{
            Client cli = new Client(0L,"sara", "0612345678");
            ClientDAO clidao = new ClientDAO();
            clidao.save(cli);


            List<Client> mylist = clidao.getAll();
            for (Client temp :mylist){
                System.out.println(temp.toString());
            }

            Credit credit = new Credit();
            credit.setMontant(1000.0);
            credit.setDate_credit(LocalDate.now());
            credit.setId_client(1L);

            CreditDAO creditDAO = new CreditDAO();
            creditDAO.save(credit);

            List<Credit> credits = creditDAO.getAll();
            for (Credit temp : credits) {
                System.out.println(temp);
            }


            Client client = new Client();
            List<Credit> creditsForClient = creditDAO.getAllForClient(client);
            for (Credit temp : creditsForClient) {
                System.out.println(temp.toString());

            }

            Produit produit = new Produit();
            produit.setNom("produit1");
            produit.setPrix(10.0);

            ProduitDAO produitDAO = new ProduitDAO();
            produitDAO.save(produit);

            List<Produit> produits = produitDAO.getAll();
            for (Produit temp : produits) {
                System.out.println(temp);
            }

            Produit produit2 = produitDAO.getOne(1L);
            List<Credit> creditsForProduit = creditDAO.getAllForProduit(produit2);
            for (Credit temp : creditsForProduit) {
                System.out.println(temp.toString());
            }


    } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
