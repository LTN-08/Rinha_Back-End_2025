package br.com.processador.pagamento.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResumoPagamentoResponseDTO {

    private ResumoPagamentoDTO defaultt;
    private ResumoPagamentoDTO fallback;
}
