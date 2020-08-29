package com.example.hackaforlife.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.hackaforlife.R;
import com.example.hackaforlife.model.FichaCadastro;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class FilaVirtualActivity extends AppCompatActivity {
    private FirebaseDatabase mDatabase;
    private String nomeUnidadeSaude;
    private String cpfPaciente;
    private List<FichaCadastro> fichasFila;
    private int tempoAnterior;
    private boolean primeiroTempo;
    private boolean ocupado = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fila_virtual);
        final View mainView = findViewById(R.id.frame8);
        final View notifyView = findViewById(R.id.frame9);
        primeiroTempo = true;
        tempoAnterior = 0;
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            nomeUnidadeSaude = extras.getString("nomeUnidadeSaude");
            cpfPaciente = extras.getString("cpfPaciente");
        }

        final TextView mTextField = (TextView) findViewById(R.id.textView17);
        DatabaseReference filaDatabase = mDatabase.getInstance().getReference();
        Query buscaFila = filaDatabase.child("PostosSaude/" + nomeUnidadeSaude + "/forms");
        buscaFila.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    ocupado = true;
                    fichasFila = new ArrayList<>();
                    if (dataSnapshot.exists()) {
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            FichaCadastro fichaCadastro = new FichaCadastro();
                            HashMap formMap = (HashMap) snapshot.getValue();
                            fichaCadastro.setPesoForm((Long) formMap.get("pesoForm"));
                            fichaCadastro.setCpfPaciente(snapshot.getKey());
                            fichasFila.add(fichaCadastro);

                        }
                    }
                final Handler handler = new Handler();
                final int delay = 500; //milliseconds
                final View semFichasView = findViewById(R.id.frame7);
                final View loadingView = findViewById(R.id.frame1);
                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (!fichasFila.isEmpty())//checking if the data is loaded or not
                        {
                            Collections.sort(fichasFila, new Comparator<FichaCadastro>() {
                                @Override
                                public int compare(FichaCadastro f1, FichaCadastro f2) {
                                    return Long.compare(f2.getPesoForm(), f1.getPesoForm());
                                }
                            });
                            int tempoEstimado = 0;
                            for(FichaCadastro f : fichasFila) {
                                System.out.println(cpfPaciente + f.getCpfPaciente() + " " + f.getPesoForm() + tempoEstimado);
                                if(!f.getCpfPaciente().equals(cpfPaciente)) {
                                    tempoEstimado += 30;
                                }
                                else break;
                            }
                            if(tempoEstimado <= 30) {
                                Intent i = new Intent(FilaVirtualActivity.this, SuaVezActivity.class);
                                System.out.println(cpfPaciente +  " "  + tempoEstimado);
                                i.putExtra("nomeUnidadeSaude", nomeUnidadeSaude);
                                startActivity(i);
                                finish();
                            } else {
                                if(tempoEstimado >= 60) {
                                    if(tempoEstimado % 60 == 0) {
                                        mTextField.setText(String.valueOf(tempoEstimado / 60) + " hora(s).");
                                    } else {
                                        int horasEstimadas = 0;
                                        int minsEstimados = 0;
                                        horasEstimadas = tempoEstimado / 60;
                                        minsEstimados = tempoEstimado - horasEstimadas*60;
                                        mTextField.setText(String.valueOf(horasEstimadas) + " hora(s) e "
                                                + String.valueOf(minsEstimados) + " minutos.");
                                    }
                                } else {
                                    mTextField.setText(String.valueOf(tempoEstimado) + " minutos.");
                                }
                            }
                            if(primeiroTempo) {
                                tempoAnterior = tempoEstimado;
                                primeiroTempo = false;
                            }
                            else if(tempoEstimado > tempoAnterior) {
                                mainView.setVisibility(View.INVISIBLE);
                                notifyView.setVisibility(View.VISIBLE);
                                new CountDownTimer(2000, 1000) {
                                    public void onFinish() {
                                        mainView.setVisibility(View.VISIBLE);
                                        notifyView.setVisibility(View.INVISIBLE);
                                    }
                                    public void onTick(long millisUntilFinished) {}
                                }.start();
                            }
                            tempoAnterior = tempoEstimado;
                        }
                    }
                }, delay);
                }



            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Erro no banco...");
            }
        });


    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
