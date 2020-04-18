package com.apl.usuarios;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.fabLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editTextEmail = LoginFragment.this.getView().findViewById(R.id.editTextEmail);
                String email = editTextEmail.getText().toString();

                EditText editTextPassword = LoginFragment.this.getView().findViewById(R.id.editTextPassword);
                String password = editTextPassword.getText().toString();

                if (!email.equals("a@b.com") || !password.equals("123")){
                    Toast.makeText(LoginFragment.this.getContext().getApplicationContext() ,R.string.invalid_user, Toast.LENGTH_LONG).show();
                    return;
                }

                LoginFragmentDirections.ActionLoginFragmentToListFragment action =
                        LoginFragmentDirections.
                                actionLoginFragmentToListFragment(new Usuario[]{});
                NavHostFragment.findNavController(LoginFragment.this)
                        .navigate(action);
            }
        });
    }
}
