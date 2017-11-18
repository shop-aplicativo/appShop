package com.example.rodrigo.singin;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rodrigo.singin.Config.ConfigFB;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class Login extends AppCompatActivity {
    public  int i=0;
    private EditText edtEmail;
    private EditText edtSenha;
    private Button Ent2;
    private FirebaseAuth autenticacao;
    private Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtEmail=(EditText) findViewById(R.id.edtEmail);
        edtSenha=(EditText) findViewById(R.id.editSenha);
        Ent2=(Button) findViewById(R.id.Ent2);



        Ent2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtEmail.getText().toString().equals("") && !edtSenha.getText().toString().equals("")){

                    usuario=new Usuario();
                    usuario.setEmail(edtEmail.getText().toString());
                    usuario.setSenha(edtSenha.getText().toString());

                    validar();


                }
                else {
                    Toast.makeText(Login.this,"Preencha o E-mail ou Senha",Toast.LENGTH_SHORT).show();

                }
            }
        });



    }
    private void validar(){
        autenticacao= ConfigFB.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    finish();
                    abrirTL();
                    Toast.makeText(Login.this,"Login Efetuado Com Sucesso",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Login.this,"Login ou senha inseridos está incorreto(s). ",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void abrirTL(){
        i++;
         Intent abrirTelaP=new Intent(this,Anuncio.class);
         startActivity(abrirTelaP);
    }


    private void cadastrar(){
        //  Intent cadastrarUsuario=new Intent(Login.this,Cadastro.class);
        // startActivity(cadastrarUsuario);

    }

}
