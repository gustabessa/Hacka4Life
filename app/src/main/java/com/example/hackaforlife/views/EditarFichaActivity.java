package com.example.hackaforlife.views;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.hackaforlife.R;
import com.example.hackaforlife.model.FichaCadastro;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class EditarFichaActivity extends AppCompatActivity {
    private TextView textView;
    private String nomeUnidadeSaude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_ficha);

        final int timer = 0;

        final TextView tv1 = (TextView) findViewById(R.id.editTextTextMultiLine4);
        final TextView tv2 = (TextView) findViewById(R.id.editTextTextMultiLine5);
        final TextView tv3 = (TextView) findViewById(R.id.editTextTextMultiLine6);
        final ScrollView sv = (ScrollView) findViewById(R.id.scrollView2);

        sv.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                tv1.getParent().requestDisallowInterceptTouchEvent(false);
                tv2.getParent().requestDisallowInterceptTouchEvent(false);
                tv3.getParent().requestDisallowInterceptTouchEvent(false);

                return false;
            }
        });

        tv1.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                tv1.getParent().requestDisallowInterceptTouchEvent(true);

                return false;
            }
        });

        tv2.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                tv2.getParent().requestDisallowInterceptTouchEvent(true);

                return false;
            }
        });

        tv3.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                tv3.getParent().requestDisallowInterceptTouchEvent(true);

                return false;
            }
        });

        RadioButton rb1 = (RadioButton) findViewById(R.id.radioButton46);
        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv1.setEnabled(true);
                tv1.requestFocus();
            }
        });
        rb1 = (RadioButton) findViewById(R.id.radioButton47);
        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv1.setEnabled(false);
                tv1.setText("");
            }
        });
        rb1 = (RadioButton) findViewById(R.id.radioButton48);
        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv2.setEnabled(true);
                tv2.requestFocus();
            }
        });
        rb1 = (RadioButton) findViewById(R.id.radioButton49);
        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv2.setEnabled(false);
                tv2.setText("");
            }
        });
        rb1 = (RadioButton) findViewById(R.id.radioButton50);
        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv3.setEnabled(true);
                tv3.requestFocus();
            }
        });
        rb1 = (RadioButton) findViewById(R.id.radioButton51);
        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv3.setEnabled(false);
                tv3.setText("");
            }
        });

        final FichaCadastro ficha = new FichaCadastro();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nomeUnidadeSaude = extras.getString("nomeUnidadeSaude");
            ficha.setCpfPaciente(extras.getString("cpfPaciente"));
            ficha.setNomePaciente(extras.getString("nomePaciente"));
            ficha.setFaixaEtaria(extras.getString("faixaEtaria"));
            ficha.setEstadoFebril(extras.getBoolean("estadoFebril"));
            ficha.setEspecialidade(extras.getString("especialidade"));
            ficha.setCartaoSus(extras.getString("cartaoSus"));
            ficha.setAlergicoMedicamentos(extras.getString("alergicoMedicamentos"));
            ficha.setRemediosUsados(extras.getString("remediosUsados"));
            ficha.setDoencas(extras.getString("doencas"));
            ficha.setDataHora(extras.getString("dataHora"));
            ficha.setTemDiabetes(extras.getBoolean("temDiabetes"));
            ficha.setDoencaCronica(extras.getBoolean("doencaCronica"));
            ficha.setRemedioControlado(extras.getBoolean("remedioControlado"));
            ficha.setAnsiaVomito(extras.getBoolean("ansiaVomito"));
            ficha.setDiarreia(extras.getBoolean("diarreia"));
            ficha.setTemAlergia(extras.getBoolean("temAlergia"));
            ficha.setTemAlergiaMedicamento(extras.getBoolean("temAlergiaMedicamento"));
            ficha.setTemCoriza(extras.getBoolean("temCoriza"));
            ficha.setTemTosse(extras.getBoolean("temTosse"));
            ficha.setTemDificuldadeRespirar(extras.getBoolean("temDificuldadeRespirar"));
            ficha.setTemDorCorpo(extras.getBoolean("temDorCorpo"));
            ficha.setPesoForm(extras.getLong("pesoForm"));
            ficha.setPrioritario(extras.getBoolean("prioritario"));
            ficha.setValorFebre(extras.getString("valorFebre"));
            ficha.setValorGlicemia(extras.getString("valorGlicemia"));
            ficha.setValorPressaoArterial(extras.getString("valorPressaoArterial"));

        }
        textView = findViewById(R.id.editTextTextPersonName5);
        textView.setText(ficha.getValorFebre());
        textView = findViewById(R.id.editTextTextPersonName6);
        textView.setText(ficha.getValorGlicemia());
        textView = findViewById(R.id.editTextTextPersonName7);
        textView.setText(ficha.getValorPressaoArterial());
        if (ficha.isTemAlergiaMedicamento())
            tv3.setEnabled(true);
        if (ficha.isDoencaCronica()) ;
        tv2.setEnabled(true);
        if (ficha.isRemedioControlado())
            tv1.setEnabled(true);
        textView = findViewById(R.id.textView37);
        textView.setText(ficha.getNomePaciente());
        textView = findViewById(R.id.textView79);
        if (ficha.isPrioritario())
            textView.setText("SIM");
        else
            textView.setText("NÃO");
        textView = findViewById(R.id.textView41);
        textView.setText(ficha.getFaixaEtaria());
        textView = findViewById(R.id.textView42);
        textView.setText(ficha.getCpfPaciente());
        textView = findViewById(R.id.textView51);
        textView.setText(ficha.getCartaoSus());

        RadioButton rb = findViewById(R.id.radioButton29);
        if (ficha.isEstadoFebril()) {
            rb.setChecked(true);
        } else {
            rb = findViewById(R.id.radioButton28);
            rb.setChecked(true);
        }
        rb = findViewById(R.id.radioButton30);
        if (ficha.isAnsiaVomito()) {
            rb.setChecked(true);
        } else {
            rb = findViewById(R.id.radioButton31);
            rb.setChecked(true);
        }
        rb = findViewById(R.id.radioButton32);
        if (ficha.isDiarreia()) {
            rb.setChecked(true);
        } else {
            rb = findViewById(R.id.radioButton33);
            rb.setChecked(true);
        }
        rb = findViewById(R.id.radioButton34);
        if (ficha.isTemAlergia()) {
            rb.setChecked(true);
        } else {
            rb = findViewById(R.id.radioButton35);
            rb.setChecked(true);
        }
        rb = findViewById(R.id.radioButton36);
        if (ficha.isTemDiabetes()) {
            rb.setChecked(true);
        } else {
            rb = findViewById(R.id.radioButton37);
            rb.setChecked(true);
        }
        rb = findViewById(R.id.radioButton38);
        if (ficha.isTemTosse()) {
            rb.setChecked(true);
        } else {
            rb = findViewById(R.id.radioButton39);
            rb.setChecked(true);
        }
        rb = findViewById(R.id.radioButton40);
        if (ficha.isTemDorCorpo()) {
            rb.setChecked(true);
        } else {
            rb = findViewById(R.id.radioButton41);
            rb.setChecked(true);
        }
        rb = findViewById(R.id.radioButton42);
        if (ficha.isTemCoriza()) {
            rb.setChecked(true);
        } else {
            rb = findViewById(R.id.radioButton43);
            rb.setChecked(true);
        }
        rb = findViewById(R.id.radioButton44);
        if (ficha.isTemDificuldadeRespirar()) {
            rb.setChecked(true);
        } else {
            rb = findViewById(R.id.radioButton45);
            rb.setChecked(true);
        }
        rb = findViewById(R.id.radioButton46);
        textView = findViewById(R.id.editTextTextMultiLine4);
        if (ficha.isRemedioControlado()) {
            rb.setChecked(true);
            textView.setText(ficha.getRemediosUsados());

        } else {
            rb = findViewById(R.id.radioButton47);
            rb.setChecked(true);
        }
        rb = findViewById(R.id.radioButton48);
        textView = findViewById(R.id.editTextTextMultiLine4);
        if (ficha.isDoencaCronica()) {
            rb.setChecked(true);
            textView = findViewById(R.id.editTextTextMultiLine5);
            textView.setText(ficha.getDoencas());
        } else {
            rb = findViewById(R.id.radioButton49);
            rb.setChecked(true);
        }
        rb = findViewById(R.id.radioButton50);
        textView = findViewById(R.id.editTextTextMultiLine4);
        if (ficha.isTemAlergiaMedicamento()) {
            rb.setChecked(true);
            textView = findViewById(R.id.editTextTextMultiLine6);
            textView.setText(ficha.getAlergicoMedicamentos());
        } else {
            rb = findViewById(R.id.radioButton51);
            rb.setChecked(true);
        }

        Button btn = findViewById(R.id.button10);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View loadingView = findViewById(R.id.frame5);
                loadingView.setVisibility(View.VISIBLE);
                sv.setVisibility(View.INVISIBLE);
                new CountDownTimer(1000, 1000) {
                    public void onFinish() {
                        loadingView.setVisibility(View.INVISIBLE);
                        sv.setVisibility(View.VISIBLE);
                    }

                    public void onTick(long millisUntilFinished) {
                    }
                }.start();
                textView = findViewById(R.id.editTextTextPersonName5);
                ficha.setValorFebre(textView.getText().toString());
                textView = findViewById(R.id.editTextTextPersonName6);
                ficha.setValorGlicemia((String) textView.getText().toString());
                textView = findViewById(R.id.editTextTextPersonName7);
                ficha.setValorPressaoArterial((String) textView.getText().toString());

                RadioGroup rg;
                // Febre
                rg = (RadioGroup) findViewById(R.id.radioGroup20);
                if (rg.getCheckedRadioButtonId() == R.id.radioButton29) {
                    ficha.setEstadoFebril(true);
                } else {
                    ficha.setEstadoFebril(false);
                }

                // Vômito
                rg = (RadioGroup) findViewById(R.id.radioGroup21);
                if (rg.getCheckedRadioButtonId() == R.id.radioButton30) {
                    ficha.setAnsiaVomito(true);
                } else {
                    ficha.setAnsiaVomito(false);
                }


                rg = (RadioGroup) findViewById(R.id.radioGroup22);
                if (rg.getCheckedRadioButtonId() == R.id.radioButton32) {
                    ficha.setDiarreia(true);
                } else {
                    ficha.setDiarreia(false);
                }

                rg = (RadioGroup) findViewById(R.id.radioGroup23);
                if (rg.getCheckedRadioButtonId() == R.id.radioButton34) {
                    ficha.setTemAlergia(true);
                } else {
                    ficha.setTemAlergia(false);
                }

                rg = (RadioGroup) findViewById(R.id.radioGroup24);
                if (rg.getCheckedRadioButtonId() == R.id.radioButton36) {
                    ficha.setTemDiabetes(true);
                } else {
                    ficha.setTemDiabetes(false);
                }

                rg = (RadioGroup) findViewById(R.id.radioGroup25);
                if (rg.getCheckedRadioButtonId() == R.id.radioButton38) {
                    ficha.setTemTosse(true);
                } else {
                    ficha.setTemTosse(false);
                }

                rg = (RadioGroup) findViewById(R.id.radioGroup26);
                if (rg.getCheckedRadioButtonId() == R.id.radioButton40) {
                    ficha.setTemDorCorpo(true);
                } else {
                    ficha.setTemDorCorpo(false);
                }
                rg = (RadioGroup) findViewById(R.id.radioGroup27);
                if (rg.getCheckedRadioButtonId() == R.id.radioButton42) {
                    ficha.setTemCoriza(true);
                } else {
                    ficha.setTemCoriza(false);
                }

                rg = (RadioGroup) findViewById(R.id.radioGroup28);
                if (rg.getCheckedRadioButtonId() == R.id.radioButton44) {
                    ficha.setTemDificuldadeRespirar(true);
                } else {
                    ficha.setTemDificuldadeRespirar(false);
                }

                rg = (RadioGroup) findViewById(R.id.radioGroup29);
                if (rg.getCheckedRadioButtonId() == R.id.radioButton46) {
                    ficha.setRemedioControlado(true);
                    textView = (TextView) findViewById(R.id.editTextTextMultiLine4);
                    ficha.setRemediosUsados((String) textView.getText().toString());
                } else {
                    ficha.setRemedioControlado(false);
                    ficha.setRemediosUsados("");
                }

                rg = (RadioGroup) findViewById(R.id.radioGroup30);
                if (rg.getCheckedRadioButtonId() == R.id.radioButton48) {
                    ficha.setTemAlergiaMedicamento(true);
                    textView = (TextView) findViewById(R.id.editTextTextMultiLine5);
                    ficha.setAlergicoMedicamentos((String) textView.getText().toString());
                } else {
                    ficha.setTemAlergiaMedicamento(false);
                    ficha.setAlergicoMedicamentos("");
                }


                rg = (RadioGroup) findViewById(R.id.radioGroup31);
                if (rg.getCheckedRadioButtonId() == R.id.radioButton50) {
                    ficha.setDoencaCronica(true);
                    textView = (TextView) findViewById(R.id.editTextTextMultiLine6);
                    ficha.setDoencas((String) textView.getText().toString());
                } else {
                    ficha.setDoencaCronica(false);
                    ficha.setDoencas("");
                }

                DatabaseReference FormDatabase = FirebaseDatabase.getInstance().getReference();
                DatabaseReference insertForm = FormDatabase.child("PostosSaude/" + nomeUnidadeSaude + "/forms").child(ficha.getCpfPaciente());
                HashMap form = new HashMap();
                form.put("nome", ficha.getNomePaciente());
                form.put("cartaoSus", ficha.getCartaoSus());
                form.put("faixaEtaria", ficha.getFaixaEtaria());
                form.put("especialidade", ficha.getEspecialidade());
                form.put("alergicoMedicamentos", ficha.getAlergicoMedicamentos());
                form.put("remediosUsados", ficha.getRemediosUsados());
                form.put("doencas", ficha.getDoencas());
                form.put("horaAtendimento", ficha.getDataHora());
                form.put("temDiabetes", ficha.isTemDiabetes());
                form.put("doencaCronica", ficha.isDoencaCronica());
                form.put("remedioControlado", ficha.isRemedioControlado());
                form.put("estadoFebril", ficha.isEstadoFebril());
                form.put("ansiaVomito", ficha.isAnsiaVomito());
                form.put("diarreia", ficha.isDiarreia());
                form.put("temAlergia", ficha.isTemAlergia());
                form.put("temAlergiaMedicamento", ficha.isTemAlergiaMedicamento());
                form.put("temTosse", ficha.isTemTosse());
                form.put("temCoriza", ficha.isTemCoriza());
                form.put("temDificuldadeRespirar", ficha.isTemDificuldadeRespirar());
                form.put("temDorCorpo", ficha.isTemDorCorpo());
                form.put("pesoForm", ficha.getPesoForm());
                form.put("valorFebre", ficha.getValorFebre());
                form.put("valorGlicemia", ficha.getValorGlicemia());
                form.put("valorPressaoArterial", ficha.getValorPressaoArterial());
                form.put("pesoForm", ficha.getPesoForm());
                form.put("prioritario", ficha.isPrioritario());

                insertForm.updateChildren(form);
            }

        });
        btn = findViewById(R.id.button9);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View loadingView = findViewById(R.id.frame5);
                loadingView.setVisibility(View.VISIBLE);
                sv.setVisibility(View.INVISIBLE);
                new CountDownTimer(1000, 1000) {
                    public void onFinish() {
                        loadingView.setVisibility(View.INVISIBLE);
                        sv.setVisibility(View.VISIBLE);
                    }

                    public void onTick(long millisUntilFinished) {
                    }
                }.start();
                finish();

                DatabaseReference FormDatabase = FirebaseDatabase.getInstance().getReference();
                DatabaseReference removeForm = FormDatabase.child("PostosSaude/" + nomeUnidadeSaude + "/forms").child(ficha.getCpfPaciente());
                removeForm.removeValue();


            }

        });


    }
}