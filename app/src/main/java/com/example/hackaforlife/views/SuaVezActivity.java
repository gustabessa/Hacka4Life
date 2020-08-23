package com.example.hackaforlife.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.example.hackaforlife.R;
import com.example.hackaforlife.model.UnidadeSaude;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SuaVezActivity extends AppCompatActivity {
    private FirebaseDatabase mDatabase;
    private UnidadeSaude unidadeSaude = new UnidadeSaude() ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sua_vez);
        unidadeSaude.setEndereco(null);
        String nomeUnidadeSaude = null;
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            nomeUnidadeSaude = extras.getString("nomeUnidadeSaude");
        }
        final DatabaseReference unidadeSaudeDB = mDatabase.getInstance().getReference();
        final Query dadosUnidadeSaude = unidadeSaudeDB.child("PostosSaude/" + nomeUnidadeSaude);
        final String finalNomeUnidadeSaude = nomeUnidadeSaude;
        dadosUnidadeSaude.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    HashMap dadosUnidade = (HashMap) dataSnapshot.getValue();
                    unidadeSaude.setNome(finalNomeUnidadeSaude);
                    unidadeSaude.setTipo_unidade((String) dadosUnidade.get("tipo_unidade"));
                    unidadeSaude.setEndereco((String) dadosUnidade.get("endereco"));
                    unidadeSaude.setNumero((Long) dadosUnidade.get("numero"));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Erro no banco...");
            }
        });

        final Handler handler = new Handler();
        final int delay = 500; //milliseconds
        final View loadingView = findViewById(R.id.frame6);
        handler.postDelayed(new Runnable() {
            public void run() {
                if (!unidadeSaude.getEndereco().equals(null))//checking if the data is loaded or not
                {
                    TextView textView = findViewById(R.id.textView53);
                    textView.setText(unidadeSaude.getTipo_unidade() + "/" + unidadeSaude.getNome());
                    textView = findViewById(R.id.textView73);
                    textView.setText(unidadeSaude.getEndereco() + ", " + unidadeSaude.getNumero());
                    loadingView.setVisibility(View.GONE);
                } else
                    handler.postDelayed(this, delay);
            }
        }, delay);
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
