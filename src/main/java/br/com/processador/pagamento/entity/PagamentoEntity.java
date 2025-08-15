package br.com.processador.pagamento.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

//@NoArgsConstructor
@Entity
//@Setter
@Table(name = "Pagamento")
public class PagamentoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "uuid")
    private UUID uuidPagamento;
    @Column(name = "valor")
    private BigDecimal valor;
    @Column(name = "status")
    private String status;
    @Column(name = "quem_processou")
    private String quemProcessou;
    @Column(name = "hora_que_processou")
    private Instant horaQueProcessou;

    public PagamentoEntity(UUID uuidPagamento, BigDecimal valor) {
        this.uuidPagamento = uuidPagamento;
        this.valor = valor;
        this.status = "PENDENTE";
        this.horaQueProcessou = Instant.now();
    }

    public PagamentoEntity() {
    }

    public UUID getId() {
        return uuidPagamento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Instant getHoraQueProcessou() {
        return horaQueProcessou;
    }
}
