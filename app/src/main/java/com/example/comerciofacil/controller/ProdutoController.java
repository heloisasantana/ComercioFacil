package com.example.comerciofacil.controller;

import android.content.Context;
import android.util.Log;
import com.example.comerciofacil.model.Produto;
import com.example.comerciofacil.persistence.ProdutoDao;
import java.util.ArrayList;
import java.util.List;

public class ProdutoController {
    private ProdutoDao produtoDao;

    public ProdutoController(Context context) {
        produtoDao = new ProdutoDao(context);
    }

    public String cadastrarProduto(String nome, String categoria, double preco, int quantidade, String validade) {
        try {
            if (nome.isEmpty() || categoria.isEmpty() || preco <= 0 || quantidade < 0) {
                return "Erro: Dados inválidos para cadastro do produto!";
            }
            Produto produto = new Produto(nome, categoria, preco, quantidade, validade);
            produtoDao.insert(produto);
            return "Produto cadastrado com sucesso!";
        } catch (Exception e) {
            Log.e("ProdutoController", "Erro ao cadastrar produto", e);
            return "Erro ao cadastrar produto: " + e.getMessage();
        }
    }

    public String atualizarProduto(int id, String nome, String categoria, double preco, int quantidade, String validade) {
        try {
            if (nome.isEmpty() || categoria.isEmpty() || preco <= 0 || quantidade < 0) {
                return "Erro: Dados inválidos para atualização do produto!";
            }
            Produto produto = new Produto(nome, categoria, preco, quantidade, validade);
            produto.setId(id);
            int rowsUpdated = produtoDao.update(produto);
            if (rowsUpdated > 0) {
                return "Produto atualizado com sucesso!";
            } else {
                return "Nenhum produto foi atualizado!";
            }
        } catch (Exception e) {
            Log.e("ProdutoController", "Erro ao atualizar produto", e);
            return "Erro ao atualizar produto: " + e.getMessage();
        }
    }

    public String excluirProduto(int id) {
        try {
            Produto produto = new Produto(null, null, 0, 0, null);
            produto.setId(id);
            produtoDao.delete(produto);
            return "Produto excluído com sucesso!";
        } catch (Exception e) {
            Log.e("ProdutoController", "Erro ao excluir produto", e);
            return "Erro ao excluir produto: " + e.getMessage();
        }
    }

    public Produto buscarProduto(int id) {
        try {
            return produtoDao.findOne(id);
        } catch (Exception e) {
            Log.e("ProdutoController", "Erro ao buscar produto", e);
            return null;
        }
    }

    public List<Produto> listarProdutos() {
        try {
            return produtoDao.findAll();
        } catch (Exception e) {
            Log.e("ProdutoController", "Erro ao listar produtos", e);
            return new ArrayList<>();
        }
    }
}
