package br.com.processador.pagamento.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


public class ResumoPagamentoResponseDTO {

    @JsonProperty("default")
    private ResumoPagamentoDTO defaultt;
    private ResumoPagamentoDTO fallback;

    public void setDefaultt(ResumoPagamentoDTO defaultt) {
        this.defaultt = defaultt;
    }

    public void setFallback(ResumoPagamentoDTO fallback) {
        this.fallback = fallback;
    }

    public ResumoPagamentoDTO getDefaultt() {
        return defaultt;
    }

    public ResumoPagamentoDTO getFallback() {
        return fallback;
    }
}
