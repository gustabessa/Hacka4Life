package com.example.hackaforlife.model;

import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FichaCadastro {

    private String especialidade;
    private String dataHora;
    private String nomePaciente;
    private String cpfPaciente;
    private String cartaoSus;
    private String faixaEtaria;
    private String alergicoMedicamentos;
    private String remediosUsados;
    private String doencas;
    private String valorFebre;
    private String valorGlicemia;
    private String valorPressaoArterial;
    private boolean temDiabetes;
    private boolean doencaCronica;
    private boolean remedioControlado;
    private boolean estadoFebril;
    private boolean ansiaVomito;
    private boolean diarreia;
    private boolean temAlergia;
    private boolean temAlergiaMedicamento;
    private boolean temTosse;
    private boolean temCoriza;
    private boolean temDorCorpo;
    private boolean temDificuldadeRespirar;
    private boolean prioritario;
    private Long pesoForm;

    @Override
    public String toString() {
        int qtSint = 0;
        if(this.estadoFebril)
            qtSint++;
        if(this.diarreia)
            qtSint++;
        if(this.temDificuldadeRespirar)
            qtSint++;
        if(this.temDorCorpo)
            qtSint++;
        if(this.temCoriza)
            qtSint++;
        if(this.temTosse)
            qtSint++;
        return "\nNome: " + this.nomePaciente + '\n' +
                "CPF: " + this.cpfPaciente + '\n' +
                "Busca Atendimento: "  + this.especialidade + '\n' +
                "Covid: "  + qtSint +" sintomas iniciais" + '\n' +
                "Data/Hora Envio: " + this.dataHora + '\n';
    }

    public boolean isPrioritario() {
        return prioritario;
    }

    public void setPrioritario(boolean prioritario) {
        this.prioritario = prioritario;
    }

    public String getValorFebre() {
        return valorFebre;
    }

    public void setValorFebre(String valorFebre) {
        this.valorFebre = valorFebre;
    }

    public String getValorGlicemia() {
        return valorGlicemia;
    }

    public void setValorGlicemia(String valorGlicemia) {
        this.valorGlicemia = valorGlicemia;
    }

    public String getValorPressaoArterial() {
        return valorPressaoArterial;
    }

    public void setValorPressaoArterial(String valorPressaoArterial) {
        this.valorPressaoArterial = valorPressaoArterial;
    }

    public Long getPesoForm() {
        return pesoForm;
    }

    public void setPesoForm(Long pesoForm) {
        this.pesoForm = pesoForm;
    }

    public boolean isTemTosse() {
        return temTosse;
    }

    public void setTemTosse(boolean temTosse) {
        this.temTosse = temTosse;
    }

    public boolean isTemCoriza() {
        return temCoriza;
    }

    public void setTemCoriza(boolean temCoriza) {
        this.temCoriza = temCoriza;
    }

    public boolean isTemDorCorpo() {
        return temDorCorpo;
    }

    public void setTemDorCorpo(boolean temDorCorpo) {
        this.temDorCorpo = temDorCorpo;
    }

    public boolean isTemDificuldadeRespirar() {
        return temDificuldadeRespirar;
    }

    public void setTemDificuldadeRespirar(boolean temDificuldadeRespirar) {
        this.temDificuldadeRespirar = temDificuldadeRespirar;
    }



    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getCpfPaciente() {
        return cpfPaciente;
    }

    public void setCpfPaciente(String cpfPaciente) {
        this.cpfPaciente = cpfPaciente;
    }

    public String getCartaoSus() {
        return cartaoSus;
    }

    public void setCartaoSus(String cartaoSus) {
        this.cartaoSus = cartaoSus;
    }

    public String getFaixaEtaria() {
        return faixaEtaria;
    }

    public void setFaixaEtaria(String faixaEtaria) {
        this.faixaEtaria = faixaEtaria;
    }

    public String getAlergicoMedicamentos() {
        return alergicoMedicamentos;
    }

    public void setAlergicoMedicamentos(String alergicoMedicamentos) {
        this.alergicoMedicamentos = alergicoMedicamentos;
    }

    public String getRemediosUsados() {
        return remediosUsados;
    }

    public void setRemediosUsados(String remediosUsados) {
        this.remediosUsados = remediosUsados;
    }

    public String getDoencas() {
        return doencas;
    }

    public void setDoencas(String doencas) {
        this.doencas = doencas;
    }

    public boolean isTemDiabetes() {
        return temDiabetes;
    }

    public void setTemDiabetes(boolean temDiabetes) {
        this.temDiabetes = temDiabetes;
    }

    public boolean isDoencaCronica() {
        return doencaCronica;
    }

    public void setDoencaCronica(boolean doencaCronica) {
        this.doencaCronica = doencaCronica;
    }

    public boolean isRemedioControlado() {
        return remedioControlado;
    }

    public void setRemedioControlado(boolean remedioControlado) {
        this.remedioControlado = remedioControlado;
    }

    public boolean isEstadoFebril() {
        return estadoFebril;
    }

    public void setEstadoFebril(boolean estadoFebril) {
        this.estadoFebril = estadoFebril;
    }

    public boolean isAnsiaVomito() {
        return ansiaVomito;
    }

    public void setAnsiaVomito(boolean ansiaVomito) {
        this.ansiaVomito = ansiaVomito;
    }

    public boolean isDiarreia() {
        return diarreia;
    }

    public void setDiarreia(boolean diarreia) {
        this.diarreia = diarreia;
    }

    public boolean isTemAlergia() {
        return temAlergia;
    }

    public void setTemAlergia(boolean temAlergia) {
        this.temAlergia = temAlergia;
    }

    public boolean isTemAlergiaMedicamento() {
        return temAlergiaMedicamento;
    }

    public void setTemAlergiaMedicamento(boolean temAlergiaMedicamento) {
        this.temAlergiaMedicamento = temAlergiaMedicamento;
    }
}
