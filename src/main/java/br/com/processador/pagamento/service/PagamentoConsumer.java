package br.com.processador.pagamento.service;

import br.com.processador.pagamento.config.RabbitMQConfig;
import br.com.processador.pagamento.dtos.PagamentoDTO;
import br.com.processador.pagamento.repository.PagamentoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class PagamentoConsumer {

    private final PagamentoRepository repository;

    @Autowired
    private ObjectMapper mapper;

    @PersistenceContext
    private EntityManager manager;

    private final HttpClient client = HttpClient.newHttpClient();

    public PagamentoConsumer(PagamentoRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = RabbitMQConfig.PAGAMENTO_QUEUE)
    @Transactional
    public void processarPagamento(String pagamentoPendente) {

        try {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8001/payments"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(pagamentoPendente))
                    .timeout(Duration.of(11, ChronoUnit.MILLIS))
                    .build();

            var resposta = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (resposta.statusCode() == 200 || resposta.statusCode() == 422) {

                var correlationId = mapper.readValue(pagamentoPendente, PagamentoDTO.class).getCorrelationId();

                atualizaPagamento(correlationId);

                System.out.println("pagamento processado com sucesso!");

            }

        } catch (Exception e) {

            throw new RuntimeException("Falha ao processar pagamento", e);
        }
    }

    public void atualizaPagamento(UUID correlationId) {
        manager.createNativeQuery("update pagamento set status = 'PAGO', quem_processou = 'DEFAULT' where uuid = :id")
                .setParameter("id", correlationId).executeUpdate();
    }
}


