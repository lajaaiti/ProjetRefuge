package models;

import android.graphics.Bitmap;

public class Pensionnaires {

    private int id;
    private String action;
    private String nom;
    private String espece;
    private String sexe;
    private String num_enclos;
    private String age;
    private String vaccin;
    private String proprietaire;
    private String mail;
    private String telephone;
    private String date_arrive;
    private String date_sortie;
    private String commentaires;
    private Byte image;

    public Pensionnaires() {
    }


    public int getId() {
        return id;
    }


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEspece() {
        return espece;
    }

    public void setEspece(String espece) {
        this.espece = espece;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getNum_enclos() {
        return num_enclos;
    }

    public void setNum_enclos(String num_enclos) {
        this.num_enclos = num_enclos;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getVaccin() {
        return vaccin;
    }

    public void setVaccin(String vaccin) {
        this.vaccin = vaccin;
    }

    public String getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDate_arrive() {
        return date_arrive;
    }

    public void setDate_arrive(String date_arrive) {
        this.date_arrive = date_arrive;
    }

    public String getDate_sortie() {
        return date_sortie;
    }

    public void setDate_sortie(String date_sortie) {
        this.date_sortie = date_sortie;
    }

    public String getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

    public Byte getImage() {
        return image;
    }

    public void setImage(Byte image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "pensionnaires{" +
                "id=" + id +
                ", action='" + action + '\'' +
                ", nom='" + nom + '\'' +
                ", espece='" + espece + '\'' +
                ", sexe='" + sexe + '\'' +
                ", num_enclos='" + num_enclos + '\'' +
                ", age='" + age + '\'' +
                ", vaccin='" + vaccin + '\'' +
                ", proprietaire='" + proprietaire + '\'' +
                ", mail='" + mail + '\'' +
                ", telephone='" + telephone + '\'' +
                ", date_arrive='" + date_arrive + '\'' +
                ", date_depart='" + date_sortie + '\'' +
                ", commentaires='" + commentaires + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
