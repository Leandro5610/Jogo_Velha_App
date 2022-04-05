package br.senai.sp.jogodavelha.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

import br.senai.sp.jogodavelha.R;
import br.senai.sp.jogodavelha.databinding.FragmentJogoBinding;
import br.senai.sp.jogodavelha.util.PrefUtil;

public class FragmentJogo extends Fragment {
    private FragmentJogoBinding binding;
    private Button [] botoes;
    //variavel do tabuleiro
    private String[][] tabuleiro;
    //variavel para os simbolos
    private String simboloJog1,simboloJog2,simbolo;
    //variavel random para sortear quem começa
    private Random random;
    // conta o numero de jogadas
    private int numJogadas = 0;
    //variaveis de placar
    private  int placarJog1=0, placarJog2=0,placarEmpate=0;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

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
        // inicializa o tabuleiro
        tabuleiro = new String[3][3];

        //preencher o tabuleiro com " "
        for (String[] vetor : tabuleiro){
            Arrays.fill(vetor ,"");
        }

        //instancia o random
        random = new Random();

        // define os simbolos dos jogadore
        simboloJog1 = PrefUtil.getSimboloJog1(getContext());
        simboloJog2 = PrefUtil.getSimboloJog2(getContext());

        //altera o dimbolo do jogador no placar
        binding.jogadorUm.setText(getResources().getString(R.string.jogadorX ,simboloJog1));
        binding.jogadorDois.setText(getResources().getString(R.string.jogadorO ,simboloJog2));

        //sorteia quem inicia o jogo
        sorteia();
        atualizaVez();

        //retorna a view no fragment
        return binding.getRoot();

    }

    private void sorteia(){
        //caso orandom gere um valor um valor verddadeiro jog1 começa
        if (random.nextBoolean()){
            simbolo = simboloJog1;
        }
        //caso contrario o jog2 começa
        else {
            simbolo = simboloJog2;
        }
    }
    private void atualizaVez(){
        // verifivca de quem é a vez
        if(simbolo.equals(simboloJog1)){
            binding.linear.setBackgroundColor(getResources().getColor(R.color.X));
            binding.linear2.setBackgroundColor(Color.WHITE);
        }else {
            binding.linear2.setBackgroundColor(getResources().getColor(R.color.X));
            binding.linear.setBackgroundColor(Color.WHITE);
        }
    }

    private boolean ganhooooo() {
        //verifica se venceu nas linhas
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0].equals(simbolo) && tabuleiro[i][1].equals(simbolo) && tabuleiro[i][2].equals(simbolo)) {
                return true;
            }
        }
        //verifica se venceu na coluna
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[0][i].equals(simbolo) && tabuleiro[1][i].equals(simbolo) && tabuleiro[2][i].equals(simbolo)) {
                return true;
            }
        }
        //verifica se ganhaou na diagonal
        if (tabuleiro[0][0].equals(simbolo) && tabuleiro[1][1].equals(simbolo) && tabuleiro[2][2].equals(simbolo)) {
            return true;
        }

        if (tabuleiro[0][2].equals(simbolo) && tabuleiro[1][1].equals(simbolo) && tabuleiro[2][0].equals(simbolo)) {
            return true;
        }
        return false;

    }
    public void resetar(){
            for (String[] vetor : tabuleiro) {
                Arrays.fill(vetor, "");
            }
           for(Button botao :botoes){
               botao.setText("");
               botao.setClickable(true);
               botao.setBackgroundColor(getResources().getColor(R.color.purple_200));
           }

           sorteia();
           atualizaVez();
           numJogadas= 0;
        }


        private void atalizarPlacar(){
            binding.placarUm.setText(placarJog1+"");
            binding.placarDois.setText(placarJog2+"");
            binding.placaEmpate.setText(placarEmpate+"");
        }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //verifica qual botão foi clicadp no menu
        switch (item.getItemId()){
            case R.id.menu_resetar:
                placarJog2 =0;
                placarJog1 =0;
                placarEmpate =0;
                resetar();
                atalizarPlacar();
                break;
            case R.id.menu_pref:
                NavHostFragment.findNavController(FragmentJogo.this).navigate(R.id.action_fragmentJogo_to_prefFragment);
                break;

        }
        return true;
    }

    private View.OnClickListener listerneBotoes = press -> {
        // incrementa as jogadas
        numJogadas++;
        // pege o nome do botão
        String nomeBotao = getContext().getResources().getResourceName(press.getId());
        //extrai os dois ultimos caracteres
        String posicao = nomeBotao.substring(nomeBotao.length()-2);
        //extrai a posição em linha e coluna
        int linha = Character.getNumericValue(posicao.charAt(0)) ;
        int coluna =Character.getNumericValue( posicao.charAt(1));
        // marca no tabuleiro a posição do simbolo que foi jogado
        tabuleiro[linha][coluna] = simbolo;
        //faz um casting de view prara button
        Button botao =(Button) press;
        // troccar o simbolo do botão que foi clicado
        botao.setText(simbolo);
        //desalitar o botão
        botao.setClickable(false);
        //troco o backgroud do botao
        botao.setBackgroundColor(Color.GRAY);
        //verifica se venceu
        if(numJogadas >= 5 && ganhooooo()){
            //exibe um toast informando quem ganhou
            Toast.makeText(getContext(),R.string.ganhou, Toast.LENGTH_LONG).show();
            //verifica quem venceu e atualiza o placar
            if(simbolo.equals(simboloJog1)){
                placarJog1++;
            }else{
                placarJog2++;
            }

            //atualiza o placar
            atalizarPlacar();
            resetar();
        }else if(numJogadas == 9) {
            //exibe um toast informando que deu Velha
            Toast.makeText(getContext(),R.string.velha, Toast.LENGTH_LONG).show();
            placarEmpate++;
            atalizarPlacar();
            resetar();
        } else{
                //inverter a vez
                simbolo = simbolo.equals(simboloJog1) ? simboloJog2 : simboloJog1;
                //atualiza a vez
                atualizaVez();

            }





    };
}