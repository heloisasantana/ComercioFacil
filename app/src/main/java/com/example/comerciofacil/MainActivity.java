package com.example.comerciofacil;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.comerciofacil.fragments.FragmentCliente;
import com.example.comerciofacil.fragments.FragmentInicio;
import com.example.comerciofacil.fragments.FragmentProduto;
import com.example.comerciofacil.model.MenuOption;

/*
 *@author:Heloísa Santana da Silva
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, new FragmentInicio())
                .commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Fragment fragment = null;
        MenuOption option = getMenuOption(item.getTitle().toString());

        switch (option) {
            case PRODUTO:
                fragment = new FragmentProduto();
                break;
            case CLIENTE:
                fragment = new FragmentCliente();
                break;
        }

        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment, fragment)
                    .commit();
        }

        return super.onOptionsItemSelected(item);
    }

    private MenuOption getMenuOption(String title) {
        // Retorna o MenuOption com base no título do item do menu
        switch (title) {
            case "Gerenciar Produtos":
                return MenuOption.PRODUTO;
            case "Gerenciar Clientes":
                return MenuOption.CLIENTE;
            default:
                throw new IllegalArgumentException("Opção de menu desconhecida: " + title);
        }
    }
}
