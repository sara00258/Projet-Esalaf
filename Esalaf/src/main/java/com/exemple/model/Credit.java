package com.exemple.model;

import java.time.LocalDate;

public class Credit {

    private Long id_credit;
    private Double montant;
    private LocalDate date_credit;
    private Long id_client;

    public Credit() {
    }

    public Credit(Long id_credit , Double montant , LocalDate date_credit , Long id_client){
        this.id_credit=id_credit;
        this.montant=montant;
        this.date_credit=date_credit;
        this.id_client=id_client;
    }

    public Credit(Long id_client, Double montant) {
        this.id_client=id_client;
        this.montant=montant;
    }

    public Long getId_credit() {
        return id_credit;
    }
    public void setId_credit(Long id_credit) {
        this.id_credit = id_credit;
    }

    public Double getMontant() {
        return montant;
    }
    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public LocalDate getDate_credit() {
        return date_credit;
    }
    public void setDate_credit(LocalDate date_credit) {
        this.date_credit = date_credit;
    }

    public long getId_client() {
        return id_client;
    }

    public void setId_client(long id_client) {
        this.id_client = id_client;
    }

    @Override
    public String toString() {
        return "Credit{" +
                "idCredit=" + id_credit +
                ", montant=" + montant +
                ", dateCredit=" + date_credit +
                ", idClient=" + id_client +
                '}';
    }
}
