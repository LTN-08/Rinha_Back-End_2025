package br.com.processador.pagamento.dtos;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class PagamentoDTO {

    private UUID correlationId;
    private BigDecimal amount;
    private Instant requestedAt;

    public PagamentoDTO(UUID correlationId, BigDecimal amount, Instant requestedAt) {
        this.correlationId = correlationId;
        this.amount = amount;
        this.requestedAt = requestedAt;
    }

    public UUID getCorrelationId() {
        return correlationId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Instant getRequestedAt() {
        return requestedAt;
    }
}
