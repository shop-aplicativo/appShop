package com.example.rodrigo.singin;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Anuncio extends AppCompatActivity {

    private EditText AnuNome;
    private EditText AnuCep;
    private EditText AnuEmail;
    private EditText AnuTelefone;
    private EditText AnuDescricao;
    private EditText AnuValor;
    private EditText AnuQuantidade;
    private EditText AnuLat;
    private EditText AnuLog;
    private Button enviar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anuncio);
        AnuNome = (EditText) findViewById(R.id.nome);
        AnuCep = (EditText) findViewById(R.id.cep);
        AnuTelefone = (EditText) findViewById(R.id.telefone);
        AnuValor = (EditText) findViewById(R.id.valor);
        AnuQuantidade = (EditText) findViewById(R.id.quantidade);
        AnuLat = (EditText) findViewById(R.id.latitude);
        AnuLog = (EditText) findViewById(R.id.longitute);
        enviar = (Button) findViewById(R.id.enviar);
        AnuDescricao = (EditText) findViewById(R.id.descricao);

    }

    public void onClick(View v) {

        try{
        Produto produto = new Produto();
        produto.setNome(AnuNome.getText().toString());
        produto.setCep(Integer.parseInt(AnuCep.getText().toString()));
        produto.setTelefone(Integer.parseInt(AnuTelefone.getText().toString()));
        produto.setValor(Float.parseFloat(AnuValor.getText().toString()));
        produto.setQuantidade(Integer.parseInt(AnuQuantidade.getText().toString()));
        produto.setLatitude(Integer.parseInt(AnuLat.getText().toString()));
        produto.setLongitudo(Integer.parseInt(AnuLog.getText().toString()));
        produto.setDescricao(AnuDescricao.getText().toString());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("anúncios").push();
        myRef.setValue(produto);
        produto.setId(myRef.getKey().toString());
        myRef.setValue(produto);
        finish();
        Toast.makeText(this, "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
    }catch(Exception e){
        e.getStackTrace();
    }
    }

}


