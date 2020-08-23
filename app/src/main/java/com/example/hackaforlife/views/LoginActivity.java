package com.example.hackaforlife.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hackaforlife.R;
import com.example.hackaforlife.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private FirebaseDatabase mDatabase;
    private String login;
    private String senha;
    private User logado = null;
    private boolean userFound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        logado = new User();


        Button btn = (Button) findViewById(R.id.button8);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn = (Button) findViewById(R.id.button7);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logado = new User();
                TextView textView = (TextView) findViewById(R.id.editTextTextPersonName4);
                login = textView.getText().toString();
                textView = findViewById(R.id.editTextTextPassword);
                senha = textView.getText().toString();

                DatabaseReference dbRef = mDatabase.getInstance().getReference();
                Query buscaLogin = dbRef.child("/adm/" + login);
                buscaLogin.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            System.out.println(dataSnapshot.child("senha").getValue().equals(senha));
                                if(dataSnapshot.child("senha").getValue().equals(senha)) {
                                    userFound = true;
                                    logado.setNomeUnidadeSaude((String) dataSnapshot.child("nome_und").getValue());
                                }
                            }
                            if(userFound == false) {
                                logado = null;
                                userFound = true;
                            }
                        }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("Erro no banco...");
                    }
                });
                final Handler handler = new Handler();
                final int delay = 500; //milliseconds

                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (userFound == true)//checking if the data is loaded or not
                        {
                            if(logado == null) {
                                System.out.println("Nao achou ...");
                                logado = new User();
                                userFound = false;
                            }
                            else {
                                System.out.println("achou ...");
                                Intent i = new Intent(LoginActivity.this, AdmPanelActivity.class);
                                i.putExtra("unidadeSaude", logado.getNomeUnidadeSaude());
                                startActivity(i);
                                userFound = false;
                                //finish();
                            }

                        } else
                            handler.postDelayed(this, delay);
                    }
                }, delay);

            }
        });
    }
}
