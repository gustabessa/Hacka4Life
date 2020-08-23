package com.example.hackaforlife.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hackaforlife.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;


public class ConfirmaDadosActivity extends AppCompatActivity {
    private String nomeUnidadeSaude;
    private String medEscolhido;
    private String nomePaciente;
    private String cpfPaciente;
    private String cartaoSus;
    private String faixaEtaria;
    private String alergicoMedicamentos;
    private String remediosUsados;
    private String doencas;
    private String unidadeSaude;
    private FirebaseDatabase mDatabase;
    private LocalDateTime horaAtendimento;
    private boolean temDiabetes;
    private boolean doencaCronica;
    private boolean remedioControlado;
    private boolean estadoFebril;
    private boolean ansiaVomito;
    private boolean diarreia;
    private boolean temAlergia;
    private boolean temAlergiaMedicamento;
    private boolean temDificuldadeRespirar;
    private boolean temTosse;
    private boolean temCoriza;
    private boolean temDorCorpo;
    private boolean prioritario;
    private Long pesoForm;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirma_dados);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            prioritario = extras.getBoolean("prioritario");
            nomeUnidadeSaude = extras.getString("nomeUnidadeSaude");
            medEscolhido = extras.getString("medEscolhido");
            nomePaciente = extras.getString("nomePaciente");
            cpfPaciente = extras.getString("cpfPaciente");
            cartaoSus = extras.getString("cartaoSus");
            faixaEtaria = extras.getString("faixaEtaria");
            alergicoMedicamentos = extras.getString("alergicoMedicamentos");
            remediosUsados = extras.getString("remediosUsados");
            doencas = extras.getString("doencas");
            temDiabetes = extras.getBoolean("temDiabetes");
            doencaCronica = extras.getBoolean("doencaCronica");
            remedioControlado = extras.getBoolean("remedioControlado");
            estadoFebril = extras.getBoolean("estadoFebril");
            ansiaVomito = extras.getBoolean("ansiaVomito");
            diarreia = extras.getBoolean("diarreia");
            temAlergia = extras.getBoolean("temAlergia");
            temAlergiaMedicamento = extras.getBoolean("temAlergiaMedicamento");
            temTosse = extras.getBoolean("temTosse");
            temCoriza = extras.getBoolean("temCoriza");
            temDificuldadeRespirar = extras.getBoolean("temDificuldadeRespirar");
            temDorCorpo = extras.getBoolean("temDorCorpo");
            pesoForm = extras.getLong("pesoForm");

        }

                    textView = (TextView) findViewById(R.id.textView24);
                    textView.setText(nomePaciente);
                    textView = (TextView) findViewById(R.id.textView26);
                    textView.setText(cpfPaciente);
                    textView = (TextView) findViewById(R.id.textView32);
                    textView.setText(cartaoSus);
                    textView = (TextView) findViewById(R.id.textView33);
                    textView.setText(faixaEtaria);
                    textView = (TextView) findViewById(R.id.textView34);
                    textView.setText(nomeUnidadeSaude);
                    textView = (TextView) findViewById(R.id.textView35);
                    textView.setText(medEscolhido);
        Button btn = (Button) findViewById(R.id.button5);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                horaAtendimento = LocalDateTime.now();
                DatabaseReference FormDatabase = FirebaseDatabase.getInstance().getReference();
                DatabaseReference insertForm = FormDatabase.child("PostosSaude/" + nomeUnidadeSaude).child("/forms").child("/" + cpfPaciente);
                HashMap form = new HashMap();
                form.put("nome", nomePaciente);
                form.put("cartaoSus", cartaoSus);
                form.put("faixaEtaria", faixaEtaria);
                form.put("especialidade", medEscolhido);
                form.put("alergicoMedicamentos", alergicoMedicamentos);
                form.put("remediosUsados", remediosUsados);
                form.put("doencas", doencas);
                form.put("horaAtendimento", horaAtendimento.format(DateTimeFormatter.ofPattern("dd/MM/YYYY - (HH:mm:ss)")));
                form.put("temDiabetes", temDiabetes);
                form.put("doencaCronica", doencaCronica);
                form.put("remedioControlado", remedioControlado);
                form.put("estadoFebril", estadoFebril);
                form.put("ansiaVomito", ansiaVomito);
                form.put("diarreia", diarreia);
                form.put("temAlergia", temAlergia);
                form.put("temAlergiaMedicamento", temAlergiaMedicamento);
                form.put("temTosse", temTosse);
                form.put("temCoriza", temCoriza);
                form.put("temDificuldadeRespirar", temDificuldadeRespirar);
                form.put("temDorCorpo", temDorCorpo);
                form.put("pesoForm", pesoForm);
                form.put("prioritario", prioritario);
                form.put("valorFebre", "");
                form.put("valorPressaoArterial", "");
                form.put("valorGlicemia", "");

                insertForm.updateChildren(form);
                Intent i = new Intent(new Intent(ConfirmaDadosActivity.this, FilaVirtualActivity.class));
                i.putExtra("cpfPaciente", cpfPaciente);
                i.putExtra("nomeUnidadeSaude", nomeUnidadeSaude);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
    }
}
