package com.DvFabricio.NutriLongaVida.dominio.objetosValor;

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
    private String logradouro;
    private String bairro;
    private String cep;
    private String numeroCasa;
    private String complemento;
    private String cidade;
    private String pais;

    public Endereco(DadosEndereco dadosEndereco) {
        this.logradouro = dadosEndereco.logradouro();
        this.bairro = dadosEndereco.bairro();
        this.cep = dadosEndereco.cep();
        this.numeroCasa = dadosEndereco.numeroCasa();
        this.complemento = dadosEndereco.complemento();
        this.cidade = dadosEndereco.cidade();
        this.pais = dadosEndereco.pais();
    }

    public void atualizarInformacaoEndereco(DadosEndereco dados) {
        if (dados != null) {
            if (dados.logradouro() != null) {
                this.logradouro = dados.logradouro();
            }
            if (dados.bairro() != null) {
                this.bairro = dados.bairro();
            }
            if (dados.cep() != null) {
                this.cep = dados.cep();
            }
            if (dados.cidade() != null) {
                this.cidade = dados.cidade();
            }
            if (dados.numeroCasa() != null) {
                this.numeroCasa = dados.numeroCasa();
            }
            if (dados.complemento() != null) {
                this.complemento = dados.complemento();
            }
            if (dados.pais() != null) {
                this.pais = dados.pais();
            }

        }
    }
}

