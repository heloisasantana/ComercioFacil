package com.example.comerciofacil.controller;

import android.content.Context;
import android.util.Log;
import com.example.comerciofacil.model.Cliente;
import com.example.comerciofacil.persistence.ClienteDao;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {
    private ClienteDao clienteDao;

    public ClienteController(Context context) {
        clienteDao = new ClienteDao(context);
    }

    public String cadastrarCliente(String nome, String telefone, String email, String historicoDeCompras) {
        try {
            if (nome.isEmpty()) {
                return "Erro: O nome do cliente é obrigatório!";
            }
            Cliente cliente = new Cliente(nome, telefone, email, historicoDeCompras);
            clienteDao.insert(cliente);
            return "Cliente cadastrado com sucesso!";
        } catch (Exception e) {
            Log.e("ClienteController", "Erro ao cadastrar cliente", e);
            return "Erro ao cadastrar cliente: " + e.getMessage();
        }
    }

    public String atualizarCliente(int id, String nome, String telefone, String email, String historicoDeCompras) {
        try {
            if (nome.isEmpty()) {
                return "Erro: O nome do cliente é obrigatório!";
            }
            Cliente cliente = new Cliente(nome, telefone, email, historicoDeCompras);
            cliente.setId(id);
            int rowsUpdated = clienteDao.update(cliente);
            if (rowsUpdated > 0) {
                return "Cliente atualizado com sucesso!";
            } else {
                return "Nenhum cliente foi atualizado!";
            }
        } catch (Exception e) {
            Log.e("ClienteController", "Erro ao atualizar cliente", e);
            return "Erro ao atualizar cliente: " + e.getMessage();
        }
    }

    public String excluirCliente(int id) {
        try {
            Cliente cliente = new Cliente(null, null, null, null);
            cliente.setId(id);
            clienteDao.delete(cliente);
            return "Cliente excluído com sucesso!";
        } catch (Exception e) {
            Log.e("ClienteController", "Erro ao excluir cliente", e);
            return "Erro ao excluir cliente: " + e.getMessage();
        }
    }

    public Cliente buscarCliente(int id) {
        try {
            return clienteDao.findOne(id);
        } catch (Exception e) {
            Log.e("ClienteController", "Erro ao buscar cliente", e);
            return null;
        }
    }

    public List<Cliente> listarClientes() {
        try {
            return clienteDao.findAll();
        } catch (Exception e) {
            Log.e("ClienteController", "Erro ao listar clientes", e);
            return new ArrayList<>();
        }
    }
}
