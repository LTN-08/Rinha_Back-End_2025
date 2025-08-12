package br.com.processador.pagamento.controllers;

import br.com.processador.pagamento.dtos.ResumoPagamentoDTO;
import br.com.processador.pagamento.dtos.ResumoPagamentoResponseDTO;
import br.com.processador.pagamento.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
public class ResumoPagamentoController {

    @Autowired
    private PagamentoRepository repository;

    @GetMapping("payments-summary")
    public ResumoPagamentoResponseDTO resume(@RequestParam String from, @RequestParam String to){

        var fromInstant  = Instant.parse(from);
        var toInstant = Instant.parse(to);

        var fromParsed = LocalDateTime.ofInstant(fromInstant, ZoneId.systemDefault());
        var toParsed = LocalDateTime.ofInstant(toInstant, ZoneId.systemDefault());

        List<Object[]> objects = repository.buscaResumoDePagamentosEntre(fromParsed, toParsed);

        var pagamentoResponseDTO = new ResumoPagamentoResponseDTO();

        for (Object[] obj : objects){

            var quantidade = (Long) obj[0];
            var quemProcessou = obj[1].toString();
            var valor = (BigDecimal) obj[2];

            if (quemProcessou.equals("DEFAULT")){
                pagamentoResponseDTO.setDefaultt(new ResumoPagamentoDTO(quantidade, valor));
            }
            else if (quemProcessou.equals("FALLBACK")) {
                pagamentoResponseDTO.setFallback(new ResumoPagamentoDTO(quantidade, valor));
            }
        }

        if (pagamentoResponseDTO.getDefaultt()== null){
            pagamentoResponseDTO.setDefaultt(new ResumoPagamentoDTO(0L, BigDecimal.ZERO));
        }
        if (pagamentoResponseDTO.getFallback()== null) {
            pagamentoResponseDTO.setFallback(new ResumoPagamentoDTO(0L, BigDecimal.ZERO));
        }
        return pagamentoResponseDTO;
    }
}
