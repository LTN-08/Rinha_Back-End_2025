package br.com.processador.pagamento.repository;

import br.com.processador.pagamento.entity.PagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface PagamentoRepository extends JpaRepository<PagamentoEntity, UUID> {

    @Query(value = "SELECT count(*) , quem_processou, sum(valor)" +
            " FROM PAGAMENTO where quem_processou in ('DEFAULT','FALLBACK') and hora_que_processou" +
            " between ?1 and ?2 \n" +
            "group by quem_processou", nativeQuery = true)
    List<Object[]> buscaResumoDePagamentosEntre(LocalDateTime from, LocalDateTime to);
}
