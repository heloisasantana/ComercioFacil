package com.example.comerciofacil.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.fragment.app.Fragment;
import com.example.comerciofacil.R;
import com.example.comerciofacil.controller.ClienteController;
import com.example.comerciofacil.model.Cliente;
import java.util.List;

public class FragmentCliente extends Fragment {

    private EditText editNome, editTelefone, editEmail, editHistorico, editId;
    private ClienteController clienteController;
    private TextView textViewResultado;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cliente, container, false);

        // Referência aos elementos do layout
        editNome = view.findViewById(R.id.editNome);
        editTelefone = view.findViewById(R.id.editTelefone);
        editEmail = view.findViewById(R.id.editEmail);
        editHistorico = view.findViewById(R.id.editHistorico);
        editId = view.findViewById(R.id.editId);
        textViewResultado = view.findViewById(R.id.textViewResultado);

        Button buttonCadastrar = view.findViewById(R.id.buttonCadastrar);
        Button buttonAtualizar = view.findViewById(R.id.buttonAtualizar);
        Button buttonExcluir = view.findViewById(R.id.buttonExcluir);
        Button buttonBuscar = view.findViewById(R.id.buttonBuscar);
        Button buttonListar = view.findViewById(R.id.buttonListar);
        Button buttonIrParaProduto = view.findViewById(R.id.buttonIrParaProduto); // Botão para navegar

        // Inicializando o controlador
        clienteController = new ClienteController(view.getContext());

        // Botão Cadastrar
        buttonCadastrar.setOnClickListener(v -> {
            try {
                String nome = editNome.getText().toString();
                String telefone = editTelefone.getText().toString();
                String email = editEmail.getText().toString();
                String historico = editHistorico.getText().toString();

                clienteController.cadastrarCliente(nome, telefone, email, historico);
                textViewResultado.setText("Cliente cadastrado com sucesso!");
            } catch (Exception e) {
                textViewResultado.setText("Erro: " + e.getMessage());
            }
        });

        // Botão Atualizar
        buttonAtualizar.setOnClickListener(v -> {
            try {
                int id = Integer.parseInt(editId.getText().toString());
                String nome = editNome.getText().toString();
                String telefone = editTelefone.getText().toString();
                String email = editEmail.getText().toString();
                String historico = editHistorico.getText().toString();

                int rows = Integer.parseInt(clienteController.atualizarCliente(id, nome, telefone, email, historico));
                if (rows > 0) {
                    textViewResultado.setText("Cliente atualizado com sucesso!");
                } else {
                    textViewResultado.setText("Cliente não encontrado.");
                }
            } catch (Exception e) {
                textViewResultado.setText("Erro: " + e.getMessage());
            }
        });

        // Botão Excluir
        buttonExcluir.setOnClickListener(v -> {
            try {
                int id = Integer.parseInt(editId.getText().toString());
                clienteController.excluirCliente(id);
                textViewResultado.setText("Cliente excluído com sucesso!");
            } catch (Exception e) {
                textViewResultado.setText("Erro: " + e.getMessage());
            }
        });

        // Botão Buscar
        buttonBuscar.setOnClickListener(v -> {
            try {
                int id = Integer.parseInt(editId.getText().toString());
                Cliente cliente = clienteController.buscarCliente(id);
                if (cliente != null) {
                    textViewResultado.setText(cliente.toString());
                } else {
                    textViewResultado.setText("Cliente não encontrado.");
                }
            } catch (Exception e) {
                textViewResultado.setText("Erro: " + e.getMessage());
            }
        });

        // Botão Listar
        buttonListar.setOnClickListener(v -> {
            try {
                List<Cliente> clientes = clienteController.listarClientes();
                StringBuilder resultado = new StringBuilder();
                for (Cliente cliente : clientes) {
                    resultado.append(cliente.toString()).append("\n");
                }
                textViewResultado.setText(resultado.toString());
            } catch (Exception e) {
                textViewResultado.setText("Erro: " + e.getMessage());
            }
        });

        // Botão para ir para o FragmentProduto
        buttonIrParaProduto.setOnClickListener(v -> {
            Fragment fragment = new FragmentProduto(); // Instância do FragmentProduto
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment, fragment) // Substitui o Fragment atual pelo FragmentProduto
                    .addToBackStack(null) // Adiciona à pilha para permitir voltar
                    .commit();
        });

        return view;
    }
}
