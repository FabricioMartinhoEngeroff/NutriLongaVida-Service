package com.DvFabricio.NutriLongaVida.dominio.nutricionista;

public record DadosListagemNutricionista(Long id,String nome, String email, String registroProfissionalCrm, Especialidade especialidade) {

    public DadosListagemNutricionista(Nutricionista nutricionista){
        this(nutricionista.getId(), nutricionista.getNome(), nutricionista.getEmail(), nutricionista.getRegistroProfissionalCrm(), nutricionista.getEspecialidade());
    }
}
