package br.com.processador.pagamento.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@Entity
@Setter
public class PagamentoEntity {

    @Id
    private UUID uuidPagamento;
    private BigDecimal valor;
    private String status;
    private String quemProcessou;

    public PagamentoEntity(UUID uuidPagamento, BigDecimal valor) {
        this.uuidPagamento = uuidPagamento;
        this.valor = valor;
        this.status = "PENDENTE";
    }

}
