package com.example.rodrigo.singin;
import com.example.rodrigo.singin.Config.ConfigFB;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import java.util.HashMap;
import java.util.Map;

public class Usuario {
    private String id;
    private String email;
    private String senha;
    private String nome;

    private String cep;

    public Usuario() {
    }

    public void save(){

        DatabaseReference ref= ConfigFB.getFB();
        ref.child("usuario").child(String.valueOf(getId())).setValue(this);
    }




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getcep() {
        return cep;
    }

    public void setcep(String cep) {
        this.cep = cep;
    }
}
