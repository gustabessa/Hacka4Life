package com.example.hackaforlife.views;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hackaforlife.R;
import com.example.hackaforlife.model.UnidadeSaudeMedicos;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class QuadroMedicosActivity extends AppCompatActivity {
    private Spinner spinner;
    private ListView listView;
    private FirebaseDatabase mDatabase;
    private List<UnidadeSaudeMedicos> listUsMedicos;
    private List<UnidadeSaudeMedicos> listUsMedicosAux;
    private List<String> especialidades;
    private Context self = QuadroMedicosActivity.this;
    private String tipoUnidadeSaude;
    private ArrayAdapter arrayAdapter;
    private Adapter adapterFiltro;
    private String medEscolhido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quadro_medico);
        listUsMedicos = new ArrayList<>();
        especialidades = new ArrayList<>();



        final DatabaseReference dbRef = mDatabase.getInstance().getReference();
        Query buscaForms = dbRef.child("PostosSaude");
        buscaForms.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Query pegaTipo = dbRef.child("PostosSaude/" + snapshot.getKey() + "/tipo_unidade");
                        final UnidadeSaudeMedicos unidadeSaudeMedicos = new UnidadeSaudeMedicos();
                        unidadeSaudeMedicos.setNomeUnidadeSaude(snapshot.getKey());
                        pegaTipo.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                tipoUnidadeSaude = (String) snapshot.getValue();
                                unidadeSaudeMedicos.setTipoUnidadeSaude(tipoUnidadeSaude);
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                System.out.println("Erro no banco...");
                            }
                        });
                        Query buscaForms = dbRef.child("PostosSaude/" + snapshot.getKey() + "/especialidades").orderByValue();

                        buscaForms.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                        unidadeSaudeMedicos.especialidades.add((String) snapshot.getValue());
                                        if(!especialidades.contains((String) (String) snapshot.getValue()))
                                            especialidades.add((String) snapshot.getValue());
                                    }
                                    listUsMedicos.add(unidadeSaudeMedicos);
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                System.out.println("Erro no banco...");
                            }
                        });

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Erro no banco...");
            }
        });

        final Handler handler = new Handler();
        final int delay = 500; //milliseconds
        final View loadingView = findViewById(R.id.frame1);
        handler.postDelayed(new Runnable() {
            public void run() {
                if (!listUsMedicos.isEmpty())//checking if the data is loaded or not
                {
                    Collections.sort(listUsMedicos, new Comparator<UnidadeSaudeMedicos>() {
                        @Override
                        public int compare(UnidadeSaudeMedicos usm1, UnidadeSaudeMedicos usm2) {
                            final Collator instance = Collator.getInstance();
                            instance.setStrength(Collator.NO_DECOMPOSITION);
                            String usm1Completo = usm1.getTipoUnidadeSaude()+usm1.getNomeUnidadeSaude();
                            String usm2Completo = usm2.getTipoUnidadeSaude()+usm2.getNomeUnidadeSaude();
                            return instance.compare(usm1Completo, usm2Completo);

                        }
                    });
                    listView = (ListView) findViewById(R.id.list_view1);
                    final Adapter adapter = new ArrayAdapter<UnidadeSaudeMedicos>(self, R.layout.list_view_custom, R.id.list_item, listUsMedicos);
                    listView.setAdapter((ListAdapter) adapter);
                    spinner = findViewById(R.id.spinner2);
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if(spinner.getSelectedItemPosition() != 0) {
                                medEscolhido = spinner.getSelectedItem().toString();
                                listUsMedicosAux = new ArrayList<UnidadeSaudeMedicos>();
                                for(UnidadeSaudeMedicos usm : listUsMedicos) {
                                    if(usm.especialidades.contains(medEscolhido)) {
                                        System.out.println(usm.especialidades.contains(medEscolhido));
                                        listUsMedicosAux.add(usm);
                                    }
                                }
                                adapterFiltro = new ArrayAdapter<>(self, R.layout.list_view_custom, R.id.list_item, listUsMedicosAux);
                                listView.setAdapter((ListAdapter) adapterFiltro);
                            } else {
                                listView.setAdapter((ListAdapter) adapter);
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            System.out.println("Nada escolhido ...");
                        }
                    });
                    Collections.sort(especialidades, new Comparator<String>() {
                        @Override
                        public int compare(String s, String t1) {
                            final Collator instance = Collator.getInstance();
                            instance.setStrength(Collator.NO_DECOMPOSITION);
                            return instance.compare(s, t1);
                        }

                    });
                    especialidades.add(0, "Escolha uma Especialidade");
                    arrayAdapter = new ArrayAdapter(self, android.R.layout.simple_spinner_dropdown_item, especialidades);
                    spinner.setAdapter(arrayAdapter);
                    loadingView.setVisibility(View.GONE);
                } else {
                    handler.postDelayed(this, delay);
                }
            }
        }, delay);

    }
}
