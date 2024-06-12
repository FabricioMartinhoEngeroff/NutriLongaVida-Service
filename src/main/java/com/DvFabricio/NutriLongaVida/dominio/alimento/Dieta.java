package com.DvFabricio.NutriLongaVida.dominio.alimento;

import com.DvFabricio.NutriLongaVida.dominio.alimento.AlimentosDaDieta;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "dietas")
@Entity(name = "Dieta")
public class Dieta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String observacoes;

    @OneToMany(mappedBy = "dieta", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AlimentosDaDieta> itensDieta = new ArrayList<>();

    public void adicionarItem(AlimentosDaDieta item) {
        itensDieta.add(item);
        item.setDieta(this);
    }

    public boolean removerItem(AlimentosDaDieta item) {
        if (itensDieta.contains(item)) {
            itensDieta.remove(item);
            item.setDieta(null);
            return true;
        }
        return false;
    }

    public void atualizarObservacoes(String novasObservacoes) {
        this.observacoes = novasObservacoes;
    }

    public boolean isAtiva() {
        LocalDate hoje = LocalDate.now();
        return (dataInicio != null && !dataInicio.isAfter(hoje)) && (dataFim == null || !dataFim.isBefore(hoje));
    }

    public int calcularTotalCalorias() {
        return itensDieta.stream()
                .mapToInt(AlimentosDaDieta::getCalorias)
                .sum();
    }

    public void atualizarDatas(LocalDate novaDataInicio, LocalDate novaDataFim) {
        this.dataInicio = novaDataInicio;
        this.dataFim = novaDataFim;
    }
}
