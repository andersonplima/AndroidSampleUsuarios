package com.apl.usuarios;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;
import java.util.Arrays;


public class AddFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Usuario[] usuarios = AddFragmentArgs.fromBundle(getArguments()).getUserList();
        final ArrayList<Usuario> usuariosArrayList = new ArrayList<>(Arrays.asList(usuarios));

        view.findViewById(R.id.fabAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextNome = AddFragment.this.getView().findViewById(R.id.editTextNome);
                String nome = editTextNome.getText().toString();

                EditText editTextEndereco = AddFragment.this.getView().findViewById(R.id.editTextEndereco);
                String endereco = editTextEndereco.getText().toString();

                EditText editTextIdade = AddFragment.this.getView().findViewById(R.id.editTextIdade);
                String idade = editTextIdade.getText().toString();

                Usuario usuario = new Usuario();
                usuario.setNome(nome);
                usuario.setEndereco(endereco);
                usuario.setIdade(Integer.parseInt(idade));

                usuariosArrayList.add(usuario);

                Toast.makeText(getContext(), R.string.success, Toast.LENGTH_LONG).show();

                AddFragmentDirections.ActionAddFragmentToListFragment action =
                        AddFragmentDirections.
                                actionAddFragmentToListFragment(usuariosArrayList.toArray(new Usuario[0]));
                NavHostFragment.findNavController(AddFragment.this)
                        .navigate(action);
            }
        });
    }
}
