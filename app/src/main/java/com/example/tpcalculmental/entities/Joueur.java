package com.example.tpcalculmental.entities;

public class Joueur extends BaseEntity {
    String nom;
    Integer score;

    String typeDifficulte;

    public Joueur() {
    }

    public Joueur(String nom, int score, String typeDifficulte) {
        this.nom = nom;
        this.score = score;
        this.typeDifficulte = typeDifficulte;

    }

    public String getTypeDifficulte() {
        return typeDifficulte;
    }

    public void setTypeDifficulte(String typeDifficulte) {
        this.typeDifficulte = typeDifficulte;
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
