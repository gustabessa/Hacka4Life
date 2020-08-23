package com.example.hackaforlife.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hackaforlife.R;
import com.example.hackaforlife.model.FichaCadastro;

public class DetalharFichaActivity extends AppCompatActivity {
    private FichaCadastro fichaCadastro;
    private String nomeUnidadeSaude;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhar_ficha);
        fichaCadastro = new FichaCadastro();
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            fichaCadastro.setPrioritario(extras.getBoolean("prioritario"));
            nomeUnidadeSaude = extras.getString("unidadeSaude");
            fichaCadastro.setCpfPaciente(extras.getString("cpfPaciente"));
            fichaCadastro.setNomePaciente(extras.getString("nomePaciente"));
            fichaCadastro.setFaixaEtaria(extras.getString("faixaEtaria"));
            fichaCadastro.setEstadoFebril(extras.getBoolean("estadoFebril"));
            fichaCadastro.setEspecialidade(extras.getString("especialidade"));
            fichaCadastro.setDataHora(extras.getString("dataHora"));
            fichaCadastro.setCartaoSus(extras.getString("cartaoSus"));
            fichaCadastro.setAlergicoMedicamentos( extras.getString("alergicoMedicamentos"));
            fichaCadastro.setRemediosUsados(extras.getString("remediosUsados"));
            fichaCadastro.setDoencas(extras.getString("doencas"));
            fichaCadastro.setTemDiabetes(extras.getBoolean("temDiabetes"));
            fichaCadastro.setDoencaCronica(extras.getBoolean("doencaCronica"));
            fichaCadastro.setRemedioControlado(extras.getBoolean("remedioControlado"));
            fichaCadastro.setAnsiaVomito(extras.getBoolean("ansiaVomito"));
            fichaCadastro.setDiarreia(extras.getBoolean("diarreia"));
            fichaCadastro.setTemAlergia(extras.getBoolean("temAlergia"));
            fichaCadastro.setTemAlergiaMedicamento(extras.getBoolean("temAlergiaMedicamento"));
            fichaCadastro.setTemCoriza(extras.getBoolean("temCoriza"));
            fichaCadastro.setTemTosse(extras.getBoolean("temTosse"));
            fichaCadastro.setTemDificuldadeRespirar(extras.getBoolean("temDificuldadeRespirar"));
            fichaCadastro.setTemDorCorpo(extras.getBoolean("temDorCorpo"));
            fichaCadastro.setPesoForm(extras.getLong("pesoForm"));
            fichaCadastro.setValorFebre(extras.getString("valorFebre"));
            fichaCadastro.setValorPressaoArterial(extras.getString("valorPressaoArterial"));
            fichaCadastro.setValorGlicemia(extras.getString("valorGlicemia"));
        }

        Button btn = findViewById(R.id.button10);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetalharFichaActivity.this, EditarFichaActivity.class);
                i.putExtra("especialidade", fichaCadastro.getEspecialidade());
                i.putExtra("nomePaciente", fichaCadastro.getNomePaciente());
                i.putExtra("cpfPaciente", fichaCadastro.getCpfPaciente());
                i.putExtra("cartaoSus", fichaCadastro.getCartaoSus());
                i.putExtra("faixaEtaria", fichaCadastro.getFaixaEtaria());
                i.putExtra("alergicoMedicamentos", fichaCadastro.getAlergicoMedicamentos());
                i.putExtra("remediosUsados", fichaCadastro.getRemediosUsados());
                i.putExtra("doencas", fichaCadastro.getDoencas());
                i.putExtra("temDiabetes", fichaCadastro.isTemDiabetes());
                i.putExtra("doencaCronica", fichaCadastro.isDoencaCronica());
                i.putExtra("remedioControlado", fichaCadastro.isRemedioControlado());
                i.putExtra("estadoFebril", fichaCadastro.isEstadoFebril());
                i.putExtra("ansiaVomito", fichaCadastro.isAnsiaVomito());
                i.putExtra("diarreia", fichaCadastro.isDiarreia());
                i.putExtra("dataHora", fichaCadastro.getDataHora());
                i.putExtra("temAlergia", fichaCadastro.isTemAlergia());
                i.putExtra("temAlergiaMedicamento", fichaCadastro.isTemAlergiaMedicamento());
                i.putExtra("temCoriza", fichaCadastro.isTemCoriza());
                i.putExtra("temTosse", fichaCadastro.isTemTosse());
                i.putExtra("temDorCorpo", fichaCadastro.isTemDorCorpo());
                i.putExtra("temDificuldadeRespirar", fichaCadastro.isTemDificuldadeRespirar());
                i.putExtra("nomeUnidadeSaude", nomeUnidadeSaude);
                i.putExtra("pesoForm", fichaCadastro.getPesoForm());
                i.putExtra("prioritario", fichaCadastro.isPrioritario());
                i.putExtra("valorFebre", fichaCadastro.getValorFebre());
                i.putExtra("valorGlicemia", fichaCadastro.getValorGlicemia());
                i.putExtra("valorPressaoArterial", fichaCadastro.getValorPressaoArterial());
                startActivity(i);
                finish();
            }
        });
        TextView textView = findViewById(R.id.textView37);
        textView.setText(fichaCadastro.getNomePaciente());
        textView = findViewById(R.id.textView41);
        textView.setText(fichaCadastro.getFaixaEtaria());
        textView = findViewById(R.id.textView77);
        if(fichaCadastro.isPrioritario())
            textView.setText("SIM");
        else
            textView.setText("NÃO");
        textView = findViewById(R.id.textView42);
        textView.setText(fichaCadastro.getCpfPaciente());
        textView = findViewById(R.id.textView51);
        textView.setText(fichaCadastro.getCartaoSus());
        textView = findViewById(R.id.radioGroup20);
        if(fichaCadastro.isEstadoFebril())
            textView.setText("SIM");
        else
            textView.setText("NÃO");
        textView = findViewById(R.id.textView55);
        if(fichaCadastro.isAnsiaVomito())
            textView.setText("SIM");
        else
            textView.setText("NÃO");
        textView = findViewById(R.id.textView57);
        if(fichaCadastro.isDiarreia())
            textView.setText("SIM");
        else
            textView.setText("NÃO");
        textView = findViewById(R.id.textView46);
        if(fichaCadastro.isTemAlergia())
            textView.setText("SIM");
        else
            textView.setText("NÃO");
        textView = findViewById(R.id.textView59);
        if(fichaCadastro.isTemDiabetes())
            textView.setText("SIM");
        else
            textView.setText("NÃO");
        textView = findViewById(R.id.textView48);
        if(fichaCadastro.isTemAlergiaMedicamento())
            textView.setText(fichaCadastro.getAlergicoMedicamentos());
        else
            textView.setText("NÃO");
        textView = findViewById(R.id.textView62);
        if(fichaCadastro.isRemedioControlado())
            textView.setText(fichaCadastro.getRemediosUsados());
        else
            textView.setText("NÃO");
        textView = findViewById(R.id.textView65);
        if(fichaCadastro.isDoencaCronica())
            textView.setText(fichaCadastro.getDoencas());
        else
            textView.setText("NÃO");
        textView = findViewById(R.id.textView61);
        if(fichaCadastro.isTemTosse())
            textView.setText("SIM");
        else
            textView.setText("NÃO");
        textView = findViewById(R.id.textView67);
        if(fichaCadastro.isTemDorCorpo())
            textView.setText("SIM");
        else
            textView.setText("NÃO");
        textView = findViewById(R.id.textView69);
        if(fichaCadastro.isTemCoriza())
            textView.setText("SIM");
        else
            textView.setText("NÃO");
        textView = findViewById(R.id.textView71);
        if(fichaCadastro.isTemDificuldadeRespirar())
            textView.setText("SIM");
        else
            textView.setText("NÃO");
    }
}
