package com.apl.usuarios;

import android.os.Bundle;
import android.widget.*;
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
        String[] enderecos = {"Rua das Flores", "Rua da Glória", "Avenida Brasil", "Rua Sem Saída",
                "Rua do Limoeiro"};

        final int[] bandeiras = {R.drawable.acre, R.drawable.alagoas, R.drawable.amapa, R.drawable.amazonas,
                R.drawable.bahia, R.drawable.ceara, R.drawable.espirito_santo, R.drawable.goias,
                R.drawable.maranhao, R.drawable.mato_grosso, R.drawable.mato_grosso_do_sul, R.drawable.minas_gerais,
                R.drawable.para, R.drawable.paraiba, R.drawable.parana, R.drawable.pernambuco, R.drawable.piaui,
                R.drawable.rio_de_janeiro, R.drawable.rio_grande_do_norte, R.drawable.rio_grande_do_sul,
                R.drawable.rondonia, R.drawable.roraima, R.drawable.santa_catarina,
                R.drawable.sao_paulo, R.drawable.sergipe, R.drawable.tocantins};
        String[] estados = {"AC", "AL", "AP", "AM", "BA", "CE", "ES", "GO", "MA", "MG", "MS", "MG", "PA", "PB",
                "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};

        ArrayAdapter<String> enderecosAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_dropdown_item_1line, enderecos);

        final AutoCompleteTextView editTextEndereco = view.findViewById(R.id.editTextEndereco);
        editTextEndereco.setAdapter(enderecosAdapter);

        ArrayAdapter<String> estadosAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, estados);

        final Spinner spinnerEstados = view.findViewById(R.id.spinnerEstados);
        spinnerEstados.setAdapter(estadosAdapter);

        final ImageView imageViewBandeira = view.findViewById(R.id.imageViewBandeira);

        spinnerEstados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                imageViewBandeira.setImageResource(bandeiras[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                imageViewBandeira.setImageResource(android.R.drawable.menuitem_background);
            }
        });

        view.findViewById(R.id.fabAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextNome = AddFragment.this.getView().findViewById(R.id.editTextNome);
                String nome = editTextNome.getText().toString();

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
