package com.example.rodrigo.singin;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rodrigo on 15/11/2017.
 */

public class Produto {
    private String id;
    private String nome ;
    private Float valor;
    private int cep;
    private String descricao;
    private int telefone;
    private int quantidade;
    private int imagem;
    private float latitude;
    private float longitude;


    public Produto() {

    }

    @Exclude
    public Map<String, Object> tomap(){
        HashMap<String,Object> hashMapProuto=new HashMap<>();

        hashMapProuto.put("id",getId());
        hashMapProuto.put("nome",getNome());
        hashMapProuto.put("cep ",Integer.valueOf(getCep()));
        hashMapProuto.put("valor",Float.valueOf(getValor()));
        hashMapProuto.put("telefone",Integer.valueOf(getTelefone()));
        hashMapProuto.put("quantidade",Integer.valueOf(getQuantidade()));
        hashMapProuto.put("latitude",Float.valueOf(getLatitude()));
        hashMapProuto.put("longitude",Float.valueOf(getLongitudo()));
        hashMapProuto.put("descricao",getDescricao());


        return hashMapProuto;


    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitudo() {
        return longitude;
    }

    public void setLongitudo(float longitudo) {
        this.longitude = longitudo;
    }
}
