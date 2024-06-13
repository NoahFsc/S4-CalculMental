package com.example.tpcalculmental.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tpcalculmental.entities.Joueur;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JoueurDao extends BaseDao<Joueur> {

    public static String nom = "NOM";
    public static String score = "SCORE";
    public static String typeDifficulte = "TYPE_DIFFICULTE";
    public static String tableName = "Joueurs";
    private DataBaseHelper helper;

    public JoueurDao(DataBaseHelper helper) {
        super(helper);
        this.helper = helper;
    }

    @Override
    protected String getTableName() {
        return "Joueurs";
    }

    @Override
    protected void putValues(ContentValues values, Joueur entity) {
        values.put(nom, entity.getNom());
        values.put(score, entity.getScore());
        values.put(typeDifficulte, entity.getTypeDifficulte());
    }
    @Override
    protected Joueur getEntity(Cursor cursor) {
        Joueur joueur = new Joueur();
        Integer indexNom = cursor.getColumnIndex(nom);
        joueur.setNom(cursor.getString(indexNom));

        Integer indexScore = cursor.getColumnIndex(score);
        joueur.setScore(cursor.getInt(indexScore));

        Integer indexTypeDifficulte = cursor.getColumnIndex(typeDifficulte);
        joueur.setTypeDifficulte(cursor.getString(indexTypeDifficulte));

        return joueur;
    }

    public List<Joueur> getAll() {
        List<Joueur> joueurs = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(getTableName(), null, null, null, null, null, score + " DESC");
        while (cursor.moveToNext()) {
            joueurs.add(getEntity(cursor));
        }
        cursor.close();
        return joueurs;
    }


    public void insertFakeData(int numberOfPlayers) {
        Faker faker = new Faker();

        for (int i = 0; i < numberOfPlayers; i++) {
            String fakeName = faker.name().firstName();
            int fakeScore = faker.number().numberBetween(1, 100);

            String[] difficulties = {"Facile", "Moyen", "Difficile"};
            Random random = new Random();
            String fakeTypeDifficulte = difficulties[random.nextInt(difficulties.length)];

            Joueur fakeJoueur = new Joueur(fakeName, fakeScore, fakeTypeDifficulte);

            insertJoueur(fakeJoueur);
        }
    }

    public void insertJoueur(Joueur joueur) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(nom, joueur.getNom());
        values.put(score, joueur.getScore());
        values.put(typeDifficulte, joueur.getTypeDifficulte());

        long newRowId = db.insert(tableName, null, values);

        db.close();
    }

}
