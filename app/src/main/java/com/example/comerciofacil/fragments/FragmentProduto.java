package com.example.comerciofacil.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.fragment.app.Fragment;
import com.example.comerciofacil.R;
import com.example.comerciofacil.controller.ProdutoController;
import com.example.comerciofacil.model.Produto;
import java.util.List;

public class FragmentProduto extends Fragment {

        private EditText editNome, editCategoria, editPreco, editQuantidade, editValidade, editId;
        private ProdutoController produtoController;
        private TextView textViewResultado;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                View view = inflater.inflate(R.layout.fragment_produto, container, false);

                // Referência aos elementos do layout
                editNome = view.findViewById(R.id.editNome);
                editCategoria = view.findViewById(R.id.editCategoria);
                editPreco = view.findViewById(R.id.editPreco);
                editQuantidade = view.findViewById(R.id.editQuantidade);
                editValidade = view.findViewById(R.id.editValidade);
                editId = view.findViewById(R.id.editId);
                textViewResultado = view.findViewById(R.id.textViewResultado);

                Button buttonCadastrar = view.findViewById(R.id.buttonCadastrar);
                Button buttonAtualizar = view.findViewById(R.id.buttonAtualizar);
                Button buttonExcluir = view.findViewById(R.id.buttonExcluir);
                Button buttonBuscar = view.findViewById(R.id.buttonBuscar);
                Button buttonListar = view.findViewById(R.id.buttonListar);

                // Inicializando o controlador
                produtoController = new ProdutoController(view.getContext());

                // Botão Cadastrar
                buttonCadastrar.setOnClickListener(v -> {
                        try {
                                String nome = editNome.getText().toString();
                                String categoria = editCategoria.getText().toString();
                                double preco = Double.parseDouble(editPreco.getText().toString()); // Conversão para double
                                int quantidade = Integer.parseInt(editQuantidade.getText().toString()); // Conversão para int
                                String validade = editValidade.getText().toString();

                                produtoController.cadastrarProduto(nome, categoria, preco, quantidade, validade);
                                textViewResultado.setText("Produto cadastrado com sucesso!");
                        } catch (Exception e) {
                                textViewResultado.setText("Erro: " + e.getMessage());
                        }
                });

                // Botão Atualizar
                buttonAtualizar.setOnClickListener(v -> {
                        try {
                                int id = Integer.parseInt(editId.getText().toString()); // Conversão para int
                                String nome = editNome.getText().toString();
                                String categoria = editCategoria.getText().toString();
                                double preco = Double.parseDouble(editPreco.getText().toString()); // Conversão para double
                                int quantidade = Integer.parseInt(editQuantidade.getText().toString()); // Conversão para int
                                String validade = editValidade.getText().toString();

                                int rows = Integer.parseInt(produtoController.atualizarProduto(id, nome, categoria, preco, quantidade, validade));
                                if (rows > 0) {
                                        textViewResultado.setText("Produto atualizado com sucesso!");
                                } else {
                                        textViewResultado.setText("Produto não encontrado.");
                                }
                        } catch (Exception e) {
                                textViewResultado.setText("Erro: " + e.getMessage());
                        }
                });

                // Botão Excluir
                buttonExcluir.setOnClickListener(v -> {
                        try {
                                int id = Integer.parseInt(editId.getText().toString()); // Conversão para int
                                produtoController.excluirProduto(id);
                                textViewResultado.setText("Produto excluído com sucesso!");
                        } catch (Exception e) {
                                textViewResultado.setText("Erro: " + e.getMessage());
                        }
                });

                // Botão Buscar
                buttonBuscar.setOnClickListener(v -> {
                        try {
                                int id = Integer.parseInt(editId.getText().toString()); // Conversão para int
                                Produto produto = produtoController.buscarProduto(id);
                                if (produto != null) {
                                        textViewResultado.setText(produto.toString());
                                } else {
                                        textViewResultado.setText("Produto não encontrado.");
                                }
                        } catch (Exception e) {
                                textViewResultado.setText("Erro: " + e.getMessage());
                        }
                });

                // Botão Listar
                buttonListar.setOnClickListener(v -> {
                        try {
                                List<Produto> produtos = produtoController.listarProdutos();
                                StringBuilder resultado = new StringBuilder();
                                for (Produto produto : produtos) {
                                        resultado.append(produto.toString()).append("\n");
                                }
                                textViewResultado.setText(resultado.toString());
                        } catch (Exception e) {
                                textViewResultado.setText("Erro: " + e.getMessage());
                        }
                });

                Button buttonIrParaCliente = view.findViewById(R.id.buttonIrParaCliente);
                buttonIrParaCliente.setOnClickListener(v -> {
                        Fragment fragment = new FragmentCliente();
                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment, fragment)
                                .addToBackStack(null) // Adiciona à pilha para voltar
                                .commit();
                });


                return view;
        }
}
