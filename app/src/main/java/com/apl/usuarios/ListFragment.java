package com.apl.usuarios;

import android.database.Cursor;
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
import java.util.HashMap;
import java.util.Map;

public class ListFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Usuario[] usuarios = ListFragmentArgs.fromBundle(getArguments()).getUserList();

        ArrayList<HashMap<String, String>> data = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            HashMap<String, String> usuarioMap = new HashMap<>();
            usuarioMap.put("nome", usuario.getNome());
            usuarioMap.put("endereco", usuario.getEndereco());
            usuarioMap.put("idade", String.valueOf(usuario.getIdade()));

            data.add(usuarioMap);
        }

        SimpleAdapter arrayAdapter = new SimpleAdapter(getContext(), data, android.R.layout.simple_list_item_2,
                new String[]{"nome", "endereco"}, new int[]{android.R.id.text1, android.R.id.text2});

        ListView listView = getView().findViewById(R.id.listView);
        listView.setAdapter(arrayAdapter);

        view.findViewById(R.id.fabAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListFragmentDirections.ActionListFragmentToAddFragment action =
                        ListFragmentDirections.
                                actionListFragmentToAddFragment(usuarios);
                NavHostFragment.findNavController(ListFragment.this)
                        .navigate(action);
            }
        });

    }
}
