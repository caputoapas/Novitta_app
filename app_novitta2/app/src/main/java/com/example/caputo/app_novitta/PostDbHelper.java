package com.example.caputo.app_novitta;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// CRIA BANCO DE DADOS

public class PostDbHelper extends SQLiteOpenHelper {
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_POSTS =
            "CREATE TABLE " + PostContract.PostEntry.TABLE_NAME + " (" +
                    PostContract.PostEntry._ID + " INTEGER PRIMARY KEY," +
                    PostContract.PostEntry.COLUMN_NAME_TIPO + TEXT_TYPE + COMMA_SEP +
                    PostContract.PostEntry.COLUMN_NAME_VOTO + TEXT_TYPE + COMMA_SEP +
                    PostContract.PostEntry.COLUMN_NAME_DATA + TEXT_TYPE + " )";

    private static final String SQL_DELETE_POSTS =
            "DROP TABLE IF EXISTS " + PostContract.PostEntry.TABLE_NAME;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Pesquisa.db";

    public PostDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_POSTS);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_POSTS);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
