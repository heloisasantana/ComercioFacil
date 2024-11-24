package com.example.comerciofacil.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "comerciofacil.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Criação da tabela Produto
        String createTableProduto = "CREATE TABLE Produto (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL," +
                "categoria TEXT NOT NULL," +
                "preco REAL NOT NULL," +
                "quantidade INTEGER NOT NULL," +
                "validade TEXT);";

        // Criação da tabela Cliente
        String createTableCliente = "CREATE TABLE Cliente (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL," +
                "telefone TEXT," +
                "email TEXT," +
                "historicoDeCompras TEXT);";

        db.execSQL(createTableProduto);
        db.execSQL(createTableCliente);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Produto");
        db.execSQL("DROP TABLE IF EXISTS Cliente");
        onCreate(db);
    }
}
