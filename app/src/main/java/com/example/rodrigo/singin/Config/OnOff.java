package com.example.rodrigo.singin.Config;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Rodrigo on 13/11/2017.
 */

public class OnOff {
    private Context context;
    private SharedPreferences preferences;
    private String arquivo="projetoAppshop.OnOff";
    private int modo=0;
    private SharedPreferences.Editor editor;


    private  final String  CI="IdentificadorUsuarioOn";
    private final String CN="NomeUsuarioOn";

    public OnOff(Context context) {
        this.context = context;
        preferences=context.getSharedPreferences(arquivo,modo);

        editor=preferences.edit();

    }

    public void SalvarUsuario(String IU,String NU){
        editor.putString(CI,IU);
        editor.putString(CN,NU);
        editor.commit();
    }

    public String getIdentificador(){
        return preferences.getString(CI,null);
    }

    public String getNome(){
        return preferences.getString(CN,null);
    }
}
