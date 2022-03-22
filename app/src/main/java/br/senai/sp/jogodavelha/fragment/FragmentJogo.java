package br.senai.sp.jogodavelha.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.senai.sp.jogodavelha.R;
import br.senai.sp.jogodavelha.databinding.FragmentJogoBinding;

public class FragmentJogo extends Fragment {
    private FragmentJogoBinding binding;
    private Button [] botoes;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentJogoBinding.inflate(inflater,container,false);
        botoes = new Button[9];
        // agrupar os botões no vetor
        botoes[0] = binding.bt00;
        botoes[1] = binding.bt01;
        botoes[2] = binding.bt02;
        botoes[3] = binding.bt10;
        botoes[4] = binding.bt11;
        botoes[5] = binding.bt12;
        botoes[6] = binding.bt20;
        botoes[7] = binding.bt21;
        botoes[8] = binding.bt22;

        // associa o Listerne aos botões
        for (Button bt : botoes){
            bt.setOnClickListener(listerneBotoes);
        }
        return binding.getRoot();
    }
    private View.OnClickListener listerneBotoes = press -> {
        // pege o nome do botão
        String nomeBotao = getContext().getResources().getResourceName(press.getId());
        //extrai os dois ultimos caracteres
        String posicao = nomeBotao.substring(nomeBotao.length()-2);
        //extrai a posição em linha e coluna
        int linha = Character.getNumericValue(posicao.charAt(0)) ;
        int coluna =Character.getNumericValue( posicao.charAt(1));

        Log.w("BOTAO",linha+"");
        Log.w("BOTAO",coluna+"");
    };
}