package br.com.processador.pagamento.dtos;

import br.com.processador.pagamento.entity.PagamentoEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class PagamentoRequestDTO {

    @NotNull
    private UUID uuidPagamento;
    @NotNull
    private BigDecimal valor;

    public PagamentoEntity toModel() {
        return new PagamentoEntity(uuidPagamento, valor);
    }
}


