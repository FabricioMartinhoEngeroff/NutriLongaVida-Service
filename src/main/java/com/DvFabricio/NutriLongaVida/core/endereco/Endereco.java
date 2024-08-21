package com.DvFabricio.NutriLongaVida.core.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private String cep;
    private Integer numeroCasa;
    private String complemento;


    public Endereco(DadosEndereco dadosEndereco) {
        this.estado = dadosEndereco.estado();
        this.cidade = dadosEndereco.cidade();
        this.bairro = dadosEndereco.bairro();
        this.rua = dadosEndereco.rua();
        this.cep = dadosEndereco.cep();
        this.numeroCasa = dadosEndereco.numeroCasa();
        this.complemento = dadosEndereco.complemento();
    }

    public void atualizarInformacaoEndereco(DadosEndereco dados) {
        if (dados != null) {
            if (dados.estado() != null) {
                this.estado = dados.estado();
            }
            if (dados.cidade() != null) {
                this.cidade = dados.cidade();
            }
            if (dados.bairro() != null) {
                this.bairro = dados.bairro();
            }
            if (dados.rua() != null) {
                this.rua = dados.rua();
            }
            if (dados.cep() != null) {
                this.cep = dados.cep();
            }
            if (dados.numeroCasa() != null) {
                this.numeroCasa = dados.numeroCasa();
            }
            if (dados.complemento() != null) {
                this.complemento = dados.complemento();
            }
        }
    }

}

