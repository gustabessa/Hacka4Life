package com.example.hackaforlife.model;


import java.util.ArrayList;
import java.util.List;

public class UnidadeSaudeMedicos {
    private String tipoUnidadeSaude;
    private String nomeUnidadeSaude;
    public List<String> especialidades = new ArrayList<>();

    @Override
    public String toString() {
        String specList = "";
        for(String especialidade : especialidades) {
            specList += especialidade + '\n';
        }
        return "\nUnidade de Saude: " + tipoUnidadeSaude + "/" +  nomeUnidadeSaude + '\n' + '\n' +
                "Especialidades:" + '\n' + specList;
    }

    public String getTipoUnidadeSaude() {
        return tipoUnidadeSaude;
    }

    public void setTipoUnidadeSaude(String tipoUnidadeSaude) {
        this.tipoUnidadeSaude = tipoUnidadeSaude;
    }

    public String getNomeUnidadeSaude() {
        return nomeUnidadeSaude;
    }

    public void setNomeUnidadeSaude(String nomeUnidadeSaude) {
        this.nomeUnidadeSaude = nomeUnidadeSaude;
    }


    //    public class teste {
//        public ArrayList<UnidadeSaudeMedicos> arrayList = new ArrayList<>();
//        teste() {
//            for(){
//                arrayList.add(new UnidadeSaudeMedicos(nomeUnidade, especialidade));
//                arrayList2(deNomesUnidade);
//                for(String unidade : arrayList2)
//                    if(arrayList.contains(unidade, especialidade))
//                        arrayList3.add(unidade);
//            }
//        }
//    }
}


