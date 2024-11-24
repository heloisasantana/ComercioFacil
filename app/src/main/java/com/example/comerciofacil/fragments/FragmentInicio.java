package com.example.comerciofacil.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import com.example.comerciofacil.R;

public class FragmentInicio extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        Button btnProduto = view.findViewById(R.id.buttonProduto);
        Button btnCliente = view.findViewById(R.id.buttonCliente);

        // Navegar para Produto
        btnProduto.setOnClickListener(v -> {
            Fragment fragment = new FragmentProduto();
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment, fragment)
                    .commit();
        });

        // Navegar para Cliente
        btnCliente.setOnClickListener(v -> {
            Fragment fragment = new FragmentCliente();
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment, fragment)
                    .commit();
        });

        return view;
    }
}
