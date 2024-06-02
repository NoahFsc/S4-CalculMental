package com.example.tpcalculmental.entities;

public class Joueur extends BaseEntity {
    String nom;
    Integer score;

    public Joueur() {
    }

    public Joueur(String nom, int score) {
        this.nom = nom;
        this.score = score;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
