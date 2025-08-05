package br.com.processador.pagamento.repository;

import br.com.processador.pagamento.entity.PagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PagamentoRepository extends JpaRepository<PagamentoEntity, UUID> {
}
