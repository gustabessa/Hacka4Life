package com.example.hackaforlife.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.hackaforlife.R;



public class Form2Activity extends AppCompatActivity {
    private Long pesoForm;
    private String nomeUnidadeSaude;
    private String medEscolhido;
    private String nomePaciente;
    private String cpfPaciente;
    private String cartaoSus;
    private String faixaEtaria;
    private String alergicoMedicamentos;
    private String remediosUsados;
    private String doencas;
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
    private boolean prioritario;
    private boolean temDorCorpo;
    private TextView textView;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        pesoForm = 0L;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form2);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nomeUnidadeSaude = extras.getString("nomeUnidadeSaude");
            medEscolhido = extras.getString("medEscolhido");
            nomePaciente = extras.getString("nomePaciente");
            cpfPaciente = extras.getString("cpfPaciente");
            cartaoSus = extras.getString("cartaoSus");
        }
        final TextView tv1 = (TextView) findViewById(R.id.editTextTextMultiLine);
        final TextView tv2 = (TextView) findViewById(R.id.editTextTextMultiLine2);
        final TextView tv3 = (TextView) findViewById(R.id.editTextTextMultiLine3);
        final ScrollView sv = (ScrollView) findViewById(R.id.scrollView1);

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

        RadioButton rb14 = (RadioButton) findViewById(R.id.radioButton14);
        rb14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv1.setEnabled(true);
                tv1.requestFocus();
            }
        });
        RadioButton rb15 = (RadioButton) findViewById(R.id.radioButton15);
        rb15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv1.setEnabled(false);
                tv1.setText("");
            }
        });
        RadioButton rb16 = (RadioButton) findViewById(R.id.radioButton16);
        rb16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv2.setEnabled(true);
                tv2.requestFocus();
            }
        });
        RadioButton rb17 = (RadioButton) findViewById(R.id.radioButton17);
        rb17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv2.setEnabled(false);
                tv2.setText("");
            }
        });
        RadioButton rb18 = (RadioButton) findViewById(R.id.radioButton18);
        rb18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv3.setEnabled(true);
                tv3.requestFocus();
            }
        });
        RadioButton rb19 = (RadioButton) findViewById(R.id.radioButton19);
        rb19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv3.setEnabled(false);
                tv3.setText("");
            }
        });
        Button btn = (Button) findViewById(R.id.button4) ;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Idoso
                RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
                if(rg.getCheckedRadioButtonId() == R.id.radioButton) {
                    pesoForm += 30;
                    faixaEtaria = "Idoso";
                    prioritario = true;
                } else if(rg.getCheckedRadioButtonId() == R.id.radioButton3) {
                    pesoForm += 10;
                    faixaEtaria = "Adulto";
                } else {
                    pesoForm += 20;
                    faixaEtaria = "Criança";
                }

                // Febre
                rg = (RadioGroup) findViewById(R.id.radioGroup2);
                if(rg.getCheckedRadioButtonId() == R.id.radioButton5) {
                    pesoForm += 5;
                    estadoFebril = true;
                } else {
                    estadoFebril = false;
                }

                // Vômito
                rg = (RadioGroup) findViewById(R.id.radioGroup3);
                if(rg.getCheckedRadioButtonId() == R.id.radioButton7) {
                    pesoForm += 5;
                    ansiaVomito = true;
                } else {
                    ansiaVomito = false;
                }


                rg = (RadioGroup) findViewById(R.id.radioGroup4);
                if(rg.getCheckedRadioButtonId() == R.id.radioButton9) {
                    pesoForm += 5;
                    diarreia = true;
                } else {
                    diarreia = false;
                }

                rg = (RadioGroup) findViewById(R.id.radioGroup5);
                if(rg.getCheckedRadioButtonId() == R.id.radioButton11) {
                    pesoForm += 5;
                    temAlergia = true;
                } else {
                    temAlergia = false;
                }

                rg = (RadioGroup) findViewById(R.id.radioGroup6);
                if(rg.getCheckedRadioButtonId() == R.id.radioButton12) {
                    pesoForm += 5;
                    temDiabetes = true;
                } else {
                    temDiabetes = false;
                }

                rg = (RadioGroup) findViewById(R.id.radioGroup10);
                if(rg.getCheckedRadioButtonId() == R.id.radioButton20) {
                    pesoForm += 5;
                    temTosse = true;
                } else {
                    temTosse = false;
                }

                rg = (RadioGroup) findViewById(R.id.radioGroup11);
                if(rg.getCheckedRadioButtonId() == R.id.radioButton22) {
                    pesoForm += 5;
                    temCoriza = true;
                } else {
                    temCoriza = false;
                }

                rg = (RadioGroup) findViewById(R.id.radioGroup12);
                if(rg.getCheckedRadioButtonId() == R.id.radioButton24) {
                    pesoForm += 5;
                    temDorCorpo = true;
                } else {
                    temDorCorpo = false;
                }

                rg = (RadioGroup) findViewById(R.id.radioGroup40);
                if(rg.getCheckedRadioButtonId() == R.id.radioButton62) {
                    pesoForm += 20;
                    prioritario = true;
                } else {
                    if(!faixaEtaria.equals("Idoso"))
                    prioritario = false;
                }

                rg = (RadioGroup) findViewById(R.id.radioGroup13);
                if(rg.getCheckedRadioButtonId() == R.id.radioButton26) {
                    pesoForm += 5;
                    temDificuldadeRespirar = true;
                } else {
                    temDificuldadeRespirar = false;
                }

                rg = (RadioGroup) findViewById(R.id.radioGroup7);
                if(rg.getCheckedRadioButtonId() == R.id.radioButton14) {
                    temAlergiaMedicamento = true;
                    textView = (TextView) findViewById(R.id.editTextTextMultiLine);
                    alergicoMedicamentos = (String) textView.getText().toString();
                } else {
                    temAlergiaMedicamento = false;
                    alergicoMedicamentos = "";
                }



                rg = (RadioGroup) findViewById(R.id.radioGroup8);
                if(rg.getCheckedRadioButtonId() == R.id.radioButton16) {
                    remedioControlado = true;
                    textView = (TextView) findViewById(R.id.editTextTextMultiLine2);
                    remediosUsados = (String) textView.getText().toString();
                } else {
                    remedioControlado = false;
                    remediosUsados = "";
                }

                rg = (RadioGroup) findViewById(R.id.radioGroup9);
                if(rg.getCheckedRadioButtonId() == R.id.radioButton18) {
                    pesoForm += 5;
                    doencaCronica = true;
                    textView = (TextView) findViewById(R.id.editTextTextMultiLine3);
                    doencas = (String) textView.getText().toString();
                } else {
                    doencaCronica = false;
                    doencas = "";
                }
                Intent i = new Intent(new Intent(Form2Activity.this, ConfirmaDadosActivity.class));
                i.putExtra("nomeUnidadeSaude", nomeUnidadeSaude);
                i.putExtra("medEscolhido", medEscolhido);
                i.putExtra("nomePaciente", nomePaciente);
                i.putExtra("cpfPaciente", cpfPaciente);
                i.putExtra("cartaoSus", cartaoSus);
                i.putExtra("faixaEtaria", faixaEtaria);
                i.putExtra("alergicoMedicamentos", alergicoMedicamentos);
                i.putExtra("remediosUsados", remediosUsados);
                i.putExtra("doencas", doencas);
                i.putExtra("temDiabetes", temDiabetes);
                i.putExtra("doencaCronica", doencaCronica);
                i.putExtra("remedioControlado", remedioControlado);
                i.putExtra("estadoFebril", estadoFebril);
                i.putExtra("ansiaVomito", ansiaVomito);
                i.putExtra("diarreia", diarreia);
                i.putExtra("temAlergia", temAlergia);
                i.putExtra("temAlergiaMedicamento", temAlergiaMedicamento);
                i.putExtra("temDificuldadeRespirar", temDificuldadeRespirar);
                i.putExtra("temTosse", temTosse);
                i.putExtra("temCoriza", temCoriza);
                i.putExtra("temDorCorpo", temDorCorpo);
                i.putExtra("pesoForm", pesoForm);
                i.putExtra("prioritario", prioritario);
                startActivity(i);
            }
        });
    }
}
