package com.example.tpcalculmental.database;

import android.content.Context;

public class JoueurBaseHelper extends DataBaseHelper{
    public JoueurBaseHelper(Context context, String dataBaseName, int dataBaseVersion) {
        super(context, dataBaseName, dataBaseVersion);
    }

    @Override
    protected String getCreationSql() {
        return "CREATE TABLE IF NOT EXISTS " + JoueurDao.tableName + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                JoueurDao.nom + " VARCHAR(255) not null," +
                JoueurDao.score + " INTEGER NOT NULL," +
                JoueurDao.typeDifficulte + " VARCHAR(255) not null" +
                ")";
    }

    @Override
    protected String getDeleteSql() {
        return "DROP TABLE IF EXISTS "+JoueurDao.tableName;    }
}
