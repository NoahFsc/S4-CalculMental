package com.example.tpcalculmental.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.tpcalculmental.entities.Joueur;

public class JoueurDao extends BaseDao<Joueur> {

    public static String nom = "NOM";
    public static String score = "SCORE";
    public static String tableName = "Joueurs";

    public JoueurDao(DataBaseHelper helper) {
        super(helper);
    }

    @Override
    protected String getTableName() {
        return "Joueurs";
    }

    @Override
    protected void putValues(ContentValues values, Joueur entity) {
        values.put(nom, entity.getNom());
        values.put(score, entity.getScore());
    }
    @Override
    protected Joueur getEntity(Cursor cursor) {
        Joueur joueur = new Joueur();
        Integer indexNom = cursor.getColumnIndex(nom);
        joueur.setNom(cursor.getString(indexNom));

        Integer indexScore = cursor.getColumnIndex(score);
        joueur.setScore(cursor.getInt(indexScore));

        return joueur;
    }
}
