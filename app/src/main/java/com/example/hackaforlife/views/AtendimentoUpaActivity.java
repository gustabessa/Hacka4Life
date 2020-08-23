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

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class AtendimentoUpaActivity extends AppCompatActivity {
    private FirebaseDatabase mDatabase;
    private ArrayAdapter adapter;
    private Spinner unidadesSaude;
    private UnidadeSaude unidadeSaude;
    private String usEscolhida;
    private List<UnidadeSaude> unidades;
    private List<String> stringUnidades;
    private Context self = AtendimentoUpaActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atendimento_upa);
        unidadesSaude = (Spinner) findViewById(R.id.spinner);
        unidadeSaude = new UnidadeSaude();
        unidades = new ArrayList<>();
        stringUnidades = new ArrayList<>();
        // carregar array de unidades medicas aqui
        // usar data binding para recarregar a tela

        DatabaseReference PSDatabase = mDatabase.getInstance().getReference();
        final Query buscaPostosSaude = PSDatabase.child("PostosSaude");
        buscaPostosSaude.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {

                        HashMap mapa = (HashMap) issue.getValue();
                        UnidadeSaude unidadeSaude = new UnidadeSaude();
                        unidadeSaude.setContato((Long) mapa.get("contato"));
                        unidadeSaude.setEndereco((String) mapa.get("endereco"));
                        unidadeSaude.setNome((String) issue.getKey());
                        unidadeSaude.setTipo_unidade((String) mapa.get("tipo_unidade"));
                        unidadeSaude.setNumero((Long) mapa.get("numero"));
                        unidades.add(unidadeSaude);
                    }
                    for (UnidadeSaude us : unidades) {
                        us.setModeloVisto(us.getTipo_unidade() + "/" + us.getNome());
                        stringUnidades.add(us.getModeloVisto());
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
        final Button btn = findViewById(R.id.containedButton);
        btn.setEnabled(false);
        handler.postDelayed(new Runnable() {
            public void run() {
                if (!stringUnidades.isEmpty())//checking if the data is loaded or not
                {
                    Collections.sort(stringUnidades, new Comparator<String>() {
                        @Override
                        public int compare(String s1, String s2) {
                            final Collator instance = Collator.getInstance();
                            instance.setStrength(Collator.NO_DECOMPOSITION);
                            return instance.compare(s1, s2);
                        }
                    });
                    adapter = new ArrayAdapter(self, android.R.layout.simple_spinner_dropdown_item, stringUnidades);
                    unidadesSaude.setAdapter(adapter);
                    loadingView.setVisibility(View.GONE);
                    btn.setEnabled(true);
                } else
                    handler.postDelayed(this, delay);
            }
        }, delay);


        //ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.arrayUpas, android.R.layout.simple_spinner_item);

        unidadesSaude.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                usEscolhida = unidadesSaude.getSelectedItem().toString();
                System.out.println(usEscolhida);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                System.out.println("Nada escolhido ...");
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            UnidadeSaude unidadeEscolhida;
            @Override
            public void onClick(View v) {
                for (UnidadeSaude us : unidades) {
                    if(usEscolhida.equals(us.getModeloVisto())) {
                        unidadeEscolhida = us;
                        break;
                    }

                }
                Intent i = new Intent(AtendimentoUpaActivity.this, AtendimentoMedicoActivity.class);
                i.putExtra("nomeUnidadeSaude", unidadeEscolhida.getNome());
                startActivity(i);
            }
        });
    }
}
