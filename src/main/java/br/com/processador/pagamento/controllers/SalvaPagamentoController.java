package br.com.processador.pagamento.controllers;

import br.com.processador.pagamento.dtos.PagamentoDTO;
import br.com.processador.pagamento.dtos.PagamentoRequestDTO;
import br.com.processador.pagamento.entity.PagamentoEntity;
import br.com.processador.pagamento.repository.PagamentoRepository;
import br.com.processador.pagamento.service.PagamentoProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class SalvaPagamentoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private PagamentoProducer pagamentoProducer;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("payments")
    @Transactional
    public void salva(@RequestBody @Valid PagamentoRequestDTO requestDTO) throws JsonProcessingException {

        var pagamentoEntity = requestDTO.toModel();

        manager.persist(pagamentoEntity);

        var pagamentoDTO = new PagamentoDTO(
                requestDTO.getCorrelationId(),
                requestDTO.getAmount(),
                Instant.now());

        var pagamentoPendente = objectMapper.writeValueAsString(pagamentoDTO);

        pagamentoProducer.enviarPagamentoPendente(pagamentoPendente);
    }
}
