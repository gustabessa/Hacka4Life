package com.example.hackaforlife.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hackaforlife.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;


public class Form1Activity extends AppCompatActivity {
    private String nomeUnidadeSaude;
    private String medEscolhido;
    private String nomePaciente;
    private String cpfPaciente;
    private String cartaoSus;
    private TextView textView;
    private List<String> listaCpf;
    private boolean buscaAcabou = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form1);
        listaCpf = new ArrayList<>();
        final View errorView = findViewById(R.id.frame12);
        final View formView = findViewById(R.id.frame11);
        final View confView = findViewById(R.id.frame10);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nomeUnidadeSaude = extras.getString("nomeUnidadeSaude");
            medEscolhido = extras.getString("medEscolhido");
        }


        textView = (TextView) findViewById(R.id.editTextTextPersonName);
        Button btn = (Button) findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nomePaciente = (String) textView.getText().toString();
                textView = (TextView) findViewById(R.id.editTextTextPersonName2);
                cpfPaciente = (String) textView.getText().toString();
                textView = (TextView) findViewById(R.id.editTextTextPersonName3);
                cartaoSus = (String) textView.getText().toString();

                // CONDICIONAL QUE VALIDA CPF REMOVIDA PARA TESTES MAIS FÁCEIS!
                // O ELSE E TUDO CONTIDO NELE ESTÁ COMENTADO,
                // E O IF COM SUAS CHAVES.
                // IF NA LINHA 66, E DA ELSE DA LINHA 111 À 114

//                if(isCPF(cpfPaciente)) {
                    DatabaseReference filaDatabase = FirebaseDatabase.getInstance().getReference();
                    Query buscaCpf = filaDatabase.child("PostosSaude/" + nomeUnidadeSaude + "/forms");
                    buscaCpf.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                    listaCpf.add(dataSnapshot.getKey());
                                }
                            }
                            buscaAcabou = true;
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    final Handler handler = new Handler();
                    final int delay = 500; //milliseconds

                    handler.postDelayed(new Runnable() {
                        public void run() {
                            if (!listaCpf.isEmpty() || buscaAcabou)
                            {
                                buscaAcabou = false;
                                if(listaCpf.contains(cpfPaciente)) {
                                    formView.setVisibility(View.INVISIBLE);
                                    confView.setVisibility(View.VISIBLE);
                                } else {
                                    Intent i = new Intent(new Intent(Form1Activity.this, Form2Activity.class));
                                    i.putExtra("nomeUnidadeSaude", nomeUnidadeSaude);
                                    i.putExtra("medEscolhido", medEscolhido);
                                    i.putExtra("nomePaciente", nomePaciente);
                                    i.putExtra("cpfPaciente", cpfPaciente);
                                    i.putExtra("cartaoSus", cartaoSus);
                                    startActivity(i);
                                }
                            } else
                                handler.postDelayed(this, delay);
                        }
                    }, delay);
//                } else {
//                    formView.setVisibility(View.INVISIBLE);
//                    errorView.setVisibility(View.VISIBLE);
//                }
            }
        });
        btn = findViewById(R.id.button60);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Form1Activity.this, FilaVirtualActivity.class);
                i.putExtra("nomeUnidadeSaude", nomeUnidadeSaude);
                i.putExtra("cpfPaciente", cpfPaciente);
                startActivity(i);
                finish();
            }
        });
        btn = findViewById(R.id.button61);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(new Intent(Form1Activity.this, Form2Activity.class));
                i.putExtra("nomeUnidadeSaude", nomeUnidadeSaude);
                i.putExtra("medEscolhido", medEscolhido);
                i.putExtra("nomePaciente", nomePaciente);
                i.putExtra("cpfPaciente", cpfPaciente);
                i.putExtra("cartaoSus", cartaoSus);
                startActivity(i);
            }
        });

        btn = findViewById(R.id.button62);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                errorView.setVisibility(View.INVISIBLE);
                formView.setVisibility(View.VISIBLE);
            }
        });
    }

    public boolean isCPF(String CPF) {
        if (CPF.equals("00000000000") ||
                CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") ||
                (CPF.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;
        try {
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48);

            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return(true);
            else return(false);
        } catch (InputMismatchException erro) {
            return(false);
        }
    }
}
