package br.com.processador.pagamento.service;

import br.com.processador.pagamento.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class PagamentoProducer {
    private final RabbitTemplate rabbitTemplate;

    public PagamentoProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarPagamentoPendente(String pagamentoPendente) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.PAGAMENTO_EXCHANGE,
                RabbitMQConfig.PAGAMENTO_ROUTING_KEY,
                pagamentoPendente
        );
    }
}

