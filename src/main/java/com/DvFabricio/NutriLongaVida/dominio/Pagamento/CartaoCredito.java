package com.DvFabricio.NutriLongaVida.dominio.Pagamento;

import com.DvFabricio.NutriLongaVida.dominio.paciente.Paciente;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cartoes_credito")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CartaoCredito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private String nomeTitular;
    private String dataValidade;
    private String codigoSeguranca;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
}
