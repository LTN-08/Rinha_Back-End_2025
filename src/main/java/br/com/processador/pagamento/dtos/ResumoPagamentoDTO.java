package br.com.processador.pagamento.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ResumoPagamentoDTO {
    private Long totalRequests;
    private BigDecimal totalAmount;
}
