package com.example.tpcalculmental.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.tpcalculmental.entities.Calcul;

public class CalculDao extends BaseDao<Calcul> {

    public static String premierElement = "PREMIER_ELEMENT";
    public static String deuxiemeElement = "DEUXIEME_ELEMENT";
    public static String symbole = "SYMBOLE";
    public static String resultat = "RESULTAT";
    public static String tableName = "Calculs";

    public CalculDao(DataBaseHelper helper) {
        super(helper);
    }

    @Override
    protected String getTableName() {
        return "Calculs";
    }

    @Override
    protected void putValues(ContentValues values, Calcul entity) {
        values.put(premierElement, entity.getPremierElement());
        values.put(deuxiemeElement, entity.getDeuxiemeElement());
        values.put(symbole, entity.getSymbole());
        values.put(resultat, entity.getResultat());
    }

    @Override
    protected Calcul getEntity(Cursor cursor) {
        Calcul calcul = new Calcul();
        Integer indexPremierElement = cursor.getColumnIndex(premierElement);
        calcul.setPremierElement(cursor.getInt(indexPremierElement));

        Integer indexDeuxiemeElement = cursor.getColumnIndex(deuxiemeElement);
        calcul.setDeuxiemeElement(cursor.getInt(indexDeuxiemeElement));

        Integer indexSymbole = cursor.getColumnIndex(symbole);
        calcul.setSymbole(cursor.getString(indexSymbole));

        Integer indexResultat = cursor.getColumnIndex(resultat);
        calcul.setResultat(cursor.getInt(indexResultat));

        return calcul;
    }
}
