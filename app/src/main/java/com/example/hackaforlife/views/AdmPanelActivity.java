package com.example.hackaforlife.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

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

public class AdmPanelActivity extends AppCompatActivity {

    private ListView listView;
    private List<FichaCadastro> fichas;
    private FirebaseDatabase mDatabase;
    private String unidadeSaude;
    private Context self = AdmPanelActivity.this;
    private boolean temFichas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adm_panel);

        fichas = new ArrayList<>();
        temFichas = false;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            unidadeSaude = extras.getString("unidadeSaude");
        }

        DatabaseReference dbRef = mDatabase.getInstance().getReference();
        Query buscaForms = dbRef.child("PostosSaude/" + unidadeSaude + "/forms");
        buscaForms.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fichas = new ArrayList<>();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        HashMap mapa = (HashMap) snapshot.getValue();
                        FichaCadastro ficha = new FichaCadastro();
                        ficha.setCpfPaciente(snapshot.getKey());
                        ficha.setEspecialidade((String) mapa.get("especialidade"));
                        ficha.setDataHora((String) mapa.get("horaAtendimento"));
                        ficha.setNomePaciente((String) mapa.get("nome"));
                        ficha.setCartaoSus((String) mapa.get("cartaoSus"));
                        ficha.setFaixaEtaria((String) mapa.get("faixaEtaria"));
                        ficha.setAlergicoMedicamentos((String) mapa.get("alergicoMedicamentos"));
                        ficha.setRemediosUsados((String) mapa.get("remediosUsados"));
                        ficha.setDoencas((String) mapa.get("doencas"));
                        ficha.setTemDiabetes((boolean) mapa.get("temDiabetes"));
                        ficha.setDoencaCronica((boolean) mapa.get("doencaCronica"));
                        ficha.setRemedioControlado((boolean) mapa.get("remedioControlado"));
                        ficha.setEstadoFebril((boolean) mapa.get("estadoFebril"));
                        ficha.setAnsiaVomito((boolean) mapa.get("ansiaVomito"));
                        ficha.setDiarreia((boolean) mapa.get("diarreia"));
                        ficha.setTemAlergia((boolean) mapa.get("temAlergia"));
                        ficha.setPesoForm((Long) mapa.get("pesoForm"));
                        ficha.setTemDificuldadeRespirar((boolean) mapa.get("temDificuldadeRespirar"));
                        ficha.setTemDorCorpo((boolean) mapa.get("temDorCorpo"));
                        ficha.setTemTosse((boolean) mapa.get("temTosse"));
                        ficha.setTemCoriza((boolean) mapa.get("temCoriza"));
                        ficha.setTemAlergiaMedicamento((boolean) mapa.get("temAlergiaMedicamento"));
                        ficha.setValorFebre((String) mapa.get("valorFebre"));
                        ficha.setValorGlicemia((String) mapa.get("valorGlicemia"));
                        ficha.setValorPressaoArterial((String) mapa.get("valorPressaoArterial"));
                        ficha.setPrioritario((boolean) mapa.get("prioritario"));
                        fichas.add(ficha);
                    }
                }
                Collections.sort(fichas, new Comparator<FichaCadastro>() {
                    @Override
                    public int compare(FichaCadastro f1, FichaCadastro f2) {
                        return Long.compare(f2.getPesoForm(), f1.getPesoForm());
                    }
                });
                listView = (ListView) findViewById(R.id.list_view1);
                Adapter adapter = new ArrayAdapter<FichaCadastro>(self, R.layout.list_view_custom, R.id.list_item, fichas);
                listView.setAdapter((ListAdapter) adapter);
                temFichas = true;
                final Handler handler = new Handler();
                final int delay = 500; //milliseconds
                final View semFichasView = findViewById(R.id.frame7);
                final View loadingView = findViewById(R.id.frame1);
                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (!fichas.isEmpty() || temFichas == true)//checking if the data is loaded or not
                        {
                            temFichas = false;
                            if (!fichas.isEmpty()) {
                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                        FichaCadastro fichaCadastro = (FichaCadastro) listView.getItemAtPosition(position);
                                        Intent i = new Intent(AdmPanelActivity.this, DetalharFichaActivity.class);
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
                                        i.putExtra("temAlergia", fichaCadastro.isTemAlergia());
                                        i.putExtra("temAlergiaMedicamento", fichaCadastro.isTemAlergiaMedicamento());
                                        i.putExtra("temCoriza", fichaCadastro.isTemCoriza());
                                        i.putExtra("temTosse", fichaCadastro.isTemTosse());
                                        i.putExtra("temDorCorpo", fichaCadastro.isTemDorCorpo());
                                        i.putExtra("temDificuldadeRespirar", fichaCadastro.isTemDificuldadeRespirar());
                                        i.putExtra("unidadeSaude", unidadeSaude);
                                        i.putExtra("pesoForm", fichaCadastro.getPesoForm());
                                        i.putExtra("dataHora", fichaCadastro.getDataHora());
                                        i.putExtra("prioritario", fichaCadastro.isPrioritario());
                                        i.putExtra("valorFebre", fichaCadastro.getValorFebre());
                                        i.putExtra("valorGlicemia", fichaCadastro.getValorGlicemia());
                                        i.putExtra("valorPressaoArterial", fichaCadastro.getValorPressaoArterial());
                                        startActivity(i);
                                        semFichasView.setVisibility(View.INVISIBLE);
                                    }
                                });
                            } else {
                                semFichasView.setVisibility(View.VISIBLE);
                            }
                            loadingView.setVisibility(View.GONE);
                        } else {
                            handler.postDelayed(this, delay);
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
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
