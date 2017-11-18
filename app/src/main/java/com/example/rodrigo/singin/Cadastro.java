package com.example.rodrigo.singin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rodrigo.singin.Config.Codificar;
import com.example.rodrigo.singin.Config.ConfigFB;
import com.example.rodrigo.singin.Config.OnOff;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class Cadastro extends AppCompatActivity {

    private EditText Cadnome;
    private EditText Cadcep;
    private EditText Cademail;
    private EditText Cadsenha;
    private EditText Cadsenha1;
    private Button enviar;
    private Usuario user;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);



        Cadnome=(EditText)findViewById(R.id.nome);
        Cademail=(EditText)findViewById(R.id.Cademail);
        Cadsenha=(EditText)findViewById(R.id.Cadsenha);
        Cadsenha1=(EditText)findViewById(R.id.Cadsenha1);
        Cadcep=(EditText)findViewById(R.id.cep);
        enviar=(Button)findViewById(R.id.enviar);




        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Cadsenha.getText().toString().equals(Cadsenha1.getText().toString())){

                    user = new Usuario();
                    user.setNome(Cadnome.getText().toString());
                    user.setcep(Cadcep.getText().toString());
                    user.setEmail(Cademail.getText().toString());
                    user.setSenha(Cadsenha.getText().toString());

                    cadastrar();


                }else {
                    Toast.makeText(Cadastro.this,"Senhas não conferem",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private void cadastrar(){
        autenticacao= ConfigFB.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                user.getEmail(),
                user.getSenha()
        ).addOnCompleteListener(Cadastro.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Cadastro.this,"Usuario Cadastrado com Sucesso",Toast.LENGTH_LONG).show();

                    String Identificador= Codificar.codificacao(user.getEmail());
                    FirebaseUser usuariofirebase= task.getResult().getUser();
                    user.setId(Identificador);
                    user.save();

                    OnOff preferencias=new OnOff(Cadastro.this);
                    preferencias.SalvarUsuario(Identificador,user.getNome());


                    abrirHome();
                }else{
                    String error="";
                    try{
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                        error="Digite uma senha mais forte,contendo  no minimo 8 caracteres de letras e numeros";

                    }catch (FirebaseAuthInvalidCredentialsException e){
                        error="E-mail digitado e invalido.";

                    }catch (FirebaseAuthUserCollisionException e){
                        error="O E-mail digitado ja esta cadastrado!";
                    }catch (Exception e){
                        error="Erro ao efetuar  o cadastro";
                        e.printStackTrace();
                    }
                    Toast.makeText(Cadastro.this,"Error" + error,Toast.LENGTH_LONG).show();

                }

            }
        });

    }

    public void abrirHome(){
        Intent intent =new Intent(Cadastro.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

}