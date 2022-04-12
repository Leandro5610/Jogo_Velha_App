package br.senai.sp.jogodavelha.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PrefUtil {
   public static void salvarSimJo(String simbolo, Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("sim_jog_1", simbolo);
        editor.commit();
    }


    public static String getSimboloJog1(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("simboloJog1","X") ;
    }

    public static String getSimboloJog2(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("simboloJog2","O") ;
    }
    public static  String numeroRodadas(Context context){
       SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
       return preferences.getString("numeroDeRodadas", "0");
    }



}
