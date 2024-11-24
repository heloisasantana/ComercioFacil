package com.example.comerciofacil.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.example.comerciofacil.model.Produto;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDao {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public ProdutoDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(Produto produto) throws SQLException {
        open();
        ContentValues values = new ContentValues();
        values.put("nome", produto.getNome());
        values.put("categoria", produto.getCategoria());
        values.put("preco", produto.getPreco());
        values.put("quantidade", produto.getQuantidade());
        values.put("validade", produto.getValidade());
        db.insert("Produto", null, values);
        close();
    }

    public int update(Produto produto) throws SQLException {
        open();
        ContentValues values = new ContentValues();
        values.put("nome", produto.getNome());
        values.put("categoria", produto.getCategoria());
        values.put("preco", produto.getPreco());
        values.put("quantidade", produto.getQuantidade());
        values.put("validade", produto.getValidade());
        int rows = db.update("Produto", values, "id = ?", new String[]{String.valueOf(produto.getId())});
        close();
        return rows;
    }

    public void delete(Produto produto) throws SQLException {
        open();
        db.delete("Produto", "id = ?", new String[]{String.valueOf(produto.getId())});
        close();
    }

    @SuppressLint("Range")
    public Produto findOne(int id) throws SQLException {
        open();
        Cursor cursor = db.query("Produto", null, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") Produto produto = new Produto(
                    cursor.getString(cursor.getColumnIndex("nome")),
                    cursor.getString(cursor.getColumnIndex("categoria")),
                    cursor.getDouble(cursor.getColumnIndex("preco")),
                    cursor.getInt(cursor.getColumnIndex("quantidade")),
                    cursor.getString(cursor.getColumnIndex("validade"))
            );
            produto.setId(cursor.getInt(cursor.getColumnIndex("id")));
            cursor.close();
            close();
            return produto;
        }
        close();
        return null;
    }

    @SuppressLint("Range")
    public List<Produto> findAll() throws SQLException {
        open();
        List<Produto> produtos = new ArrayList<>();
        Cursor cursor = db.query("Produto", null, null, null, null, null, "nome ASC");
        while (cursor.moveToNext()) {
            @SuppressLint("Range") Produto produto = new Produto(
                    cursor.getString(cursor.getColumnIndex("nome")),
                    cursor.getString(cursor.getColumnIndex("categoria")),
                    cursor.getDouble(cursor.getColumnIndex("preco")),
                    cursor.getInt(cursor.getColumnIndex("quantidade")),
                    cursor.getString(cursor.getColumnIndex("validade"))
            );
            produto.setId(cursor.getInt(cursor.getColumnIndex("id")));
            produtos.add(produto);
        }
        cursor.close();
        close();
        return produtos;
    }
}
