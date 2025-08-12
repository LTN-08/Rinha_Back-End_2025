package br.com.processador.pagamento.controllers;

import br.com.processador.pagamento.dtos.PagamentoRequestDTO;
import br.com.processador.pagamento.entity.PagamentoEntity;
import br.com.processador.pagamento.repository.PagamentoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalvaPagamentoController {

    @Autowired
    private PagamentoRepository repository;

    @PostMapping("payments")
    public void salva(@RequestBody @Valid PagamentoRequestDTO requestDTO){
        PagamentoEntity pagamentoEntity = requestDTO.toModel();
        repository.save(pagamentoEntity);
    }
}
