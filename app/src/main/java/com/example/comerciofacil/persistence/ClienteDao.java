package com.example.comerciofacil.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.example.comerciofacil.model.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public ClienteDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(Cliente cliente) throws SQLException {
        open();
        ContentValues values = new ContentValues();
        values.put("nome", cliente.getNome());
        values.put("telefone", cliente.getTelefone());
        values.put("email", cliente.getEmail());
        values.put("historicoDeCompras", cliente.getHistoricoDeCompras());
        db.insert("Cliente", null, values);
        close();
    }

    public int update(Cliente cliente) throws SQLException {
        open();
        ContentValues values = new ContentValues();
        values.put("nome", cliente.getNome());
        values.put("telefone", cliente.getTelefone());
        values.put("email", cliente.getEmail());
        values.put("historicoDeCompras", cliente.getHistoricoDeCompras());
        int rows = db.update("Cliente", values, "id = ?", new String[]{String.valueOf(cliente.getId())});
        close();
        return rows;
    }

    public void delete(Cliente cliente) throws SQLException {
        open();
        db.delete("Cliente", "id = ?", new String[]{String.valueOf(cliente.getId())});
        close();
    }

    @SuppressLint("Range")
    public Cliente findOne(int id) throws SQLException {
        open();
        Cursor cursor = db.query("Cliente", null, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") Cliente cliente = new Cliente(
                    cursor.getString(cursor.getColumnIndex("nome")),
                    cursor.getString(cursor.getColumnIndex("telefone")),
                    cursor.getString(cursor.getColumnIndex("email")),
                    cursor.getString(cursor.getColumnIndex("historicoDeCompras"))
            );
            cliente.setId(cursor.getInt(cursor.getColumnIndex("id")));
            cursor.close();
            close();
            return cliente;
        }
        close();
        return null;
    }

    @SuppressLint("Range")
    public List<Cliente> findAll() throws SQLException {
        open();
        List<Cliente> clientes = new ArrayList<>();
        Cursor cursor = db.query("Cliente", null, null, null, null, null, "nome ASC");
        while (cursor.moveToNext()) {
            @SuppressLint("Range") Cliente cliente = new Cliente(
                    cursor.getString(cursor.getColumnIndex("nome")),
                    cursor.getString(cursor.getColumnIndex("telefone")),
                    cursor.getString(cursor.getColumnIndex("email")),
                    cursor.getString(cursor.getColumnIndex("historicoDeCompras"))
            );
            cliente.setId(cursor.getInt(cursor.getColumnIndex("id")));
            clientes.add(cliente);
        }
        cursor.close();
        close();
        return clientes;
    }
}
