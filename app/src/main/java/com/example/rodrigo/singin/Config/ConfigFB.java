package com.example.rodrigo.singin.Config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Rodrigo on 13/11/2017.
 */

public class ConfigFB {
    private static DatabaseReference referenciaFB;
    public static FirebaseAuth autenticacao;

    public static DatabaseReference getFB(){
        if (referenciaFB == null){
            referenciaFB= FirebaseDatabase.getInstance().getReference();
        }
        return referenciaFB;
    }

    public static FirebaseAuth getFirebaseAutenticacao(){
        if (autenticacao== null){
            autenticacao= FirebaseAuth.getInstance();
        }
        return autenticacao;

    }
}
