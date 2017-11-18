package com.example.rodrigo.singin.Config;

import android.util.Base64;

/**
 * Created by Rodrigo on 13/11/2017.
 */

public class Codificar {
    public static String codificacao(String texto){
        return Base64.encodeToString(texto.getBytes(),Base64.DEFAULT).replaceAll("(\\n|\\r)", "");
    }

    public static String decodificacao(String textocod){
        return new String(Base64.decode(textocod,Base64.DEFAULT));
    }
}
