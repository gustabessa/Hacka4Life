package com.example.hackaforlife.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hackaforlife.R;
import com.example.hackaforlife.model.UnidadeSaude;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AtendimentoMedicoActivity extends AppCompatActivity {
    private Spinner espMedicos;
    private String medEscolhido;
    private FirebaseDatabase mDatabase;
    private Context self = AtendimentoMedicoActivity.this;
    private List<String> listMedicos;
    private ArrayAdapter<String> adapter;
    private String nomeUnidadeSaude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atendimento_medicos);
        espMedicos = (Spinner) findViewById(R.id.spinner);
        listMedicos = new ArrayList<>();
        nomeUnidadeSaude = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nomeUnidadeSaude = extras.getString("nomeUnidadeSaude");
        }

        DatabaseReference MedDatabase = mDatabase.getInstance().getReference();

        final Query buscaMedicos = MedDatabase.child("PostosSaude/" + nomeUnidadeSaude + "/especialidades").orderByValue();
        buscaMedicos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        listMedicos.add(issue.getValue().toString());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Erro no banco...");
            }
        });

        final View loadingView = findViewById(R.id.frame2);
        final Button btn = findViewById(R.id.containedButton);
        btn.setEnabled(false);
        final Handler handler = new Handler();
        final int delay = 500; //milliseconds
        handler.postDelayed(new Runnable() {
            public void run() {
                if (!listMedicos.isEmpty())//checking if the data is loaded or not
                {
                    adapter = new ArrayAdapter(self, android.R.layout.simple_spinner_dropdown_item, listMedicos);
                    espMedicos.setAdapter(adapter);
                    loadingView.setVisibility(View.GONE);
                    btn.setEnabled(true);
                } else
                    handler.postDelayed(this, delay);
            }
        }, delay);


        espMedicos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                medEscolhido = espMedicos.getSelectedItem().toString();
                System.out.println(medEscolhido);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                System.out.println("Nada escolhido ...");
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(new Intent(AtendimentoMedicoActivity.this, Form1Activity.class));
                i.putExtra("nomeUnidadeSaude", nomeUnidadeSaude);
                i.putExtra("medEscolhido", medEscolhido);
                startActivity(i);
                // finish();
            }
        });
    }
}
