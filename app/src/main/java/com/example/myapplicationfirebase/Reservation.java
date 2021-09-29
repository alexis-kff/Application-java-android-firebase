package com.example.myapplicationfirebase;

public class Reservation {
    private String nom;
    private String date_reservation;
    private String heure_reservation;
    private String telephone;
    private String personnes;
    private String commentaire;
    private String date_envoie;
    private String confirmation;

    public Reservation(){}

    public Reservation(String nom, String date_reservation, String heure_reservation, String telephone, String personnes, String commentaire, String date_envoie, String confirmation) {
        this.nom = nom;
        this.date_reservation = date_reservation;
        this.heure_reservation = heure_reservation;
        this.telephone = telephone;
        this.personnes = personnes;
        this.commentaire = commentaire;
        this.date_envoie = date_envoie;
        this.confirmation = confirmation;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDate_reservation() {
        return date_reservation;
    }

    public void setDate_reservation(String date_reservation) {
        this.date_reservation = date_reservation;
    }

    public String getHeure_reservation() {
        return heure_reservation;
    }

    public void setHeure_reservation(String heure_reservation) {
        this.heure_reservation = heure_reservation;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPersonnes() {
        return personnes;
    }

    public void setPersonnes(String personnes) {
        this.personnes = personnes;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getDate_envoie() {
        return date_envoie;
    }

    public void setDate_envoie(String date_envoie) {
        this.date_envoie = date_envoie;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "nom='" + nom + '\'' +
                ", date_reservation='" + date_reservation + '\'' +
                ", heure_reservation='" + heure_reservation + '\'' +
                ", telephone='" + telephone + '\'' +
                ", personnes='" + personnes + '\'' +
                ", commentaire='" + commentaire + '\'' +
                ", date_envoie='" + date_envoie + '\'' +
                ", confirmation='" + confirmation + '\'' +
                '}';
    }


}
