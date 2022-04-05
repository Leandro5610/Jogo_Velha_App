package br.senai.sp.jogodavelha.fragment;

import android.os.Bundle;

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
    private Button botaoJogar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentInicioBinding.inflate(inflater,container,false);
        botaoJogar =binding.botaoJogar;
        botaoJogar.setOnClickListener(view -> {
            NavHostFragment.findNavController(FragmentInicio.this).navigate(R.id.action_fragmentInicio_to_fragmentJogo);
        });

        return binding.getRoot();

    }



}