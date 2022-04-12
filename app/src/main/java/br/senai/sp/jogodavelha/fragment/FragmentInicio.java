package br.senai.sp.jogodavelha.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.senai.sp.jogodavelha.R;
import br.senai.sp.jogodavelha.databinding.FragmentInicioBinding;
import br.senai.sp.jogodavelha.databinding.FragmentJogoBinding;


public class FragmentInicio extends Fragment {
    private FragmentInicioBinding  binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentInicioBinding.inflate(inflater,container,false);


        binding.botaoJogar.setOnClickListener(view -> {
            NavHostFragment.findNavController(FragmentInicio.this).navigate(R.id.action_fragmentInicio_to_fragmentJogo);
        });

        return binding.getRoot();

    }

    @Override
    public void onStart() {
        // remover a toolbar
        //pegar uma referencia do tio AppCOmpact Activity
        AppCompatActivity minhaActivity =(AppCompatActivity) getActivity();
        // oculta a actionBar
        minhaActivity.getSupportActionBar().hide();
        super.onStart();
    }
}