package br.com.processador.pagamento.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String PAGAMENTO_EXCHANGE = "pagamento.exchange";
    public static final String PAGAMENTO_QUEUE = "pagamento.queue";
    public static final String PAGAMENTO_ROUTING_KEY = "pagamento.pendente";

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(PAGAMENTO_EXCHANGE);
    }

    @Bean
    public Queue queue() {
        return QueueBuilder.durable(PAGAMENTO_QUEUE)
                .withArgument("x-dead-letter-exchange", "dlx.exchange") // Dead Letter
                .withArgument("x-dead-letter-routing-key", "dlx.pagamento")
                .build();
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(PAGAMENTO_ROUTING_KEY);
    }

    @Bean
    public TopicExchange deadLetterExchange() {
        return new TopicExchange("dlx.exchange");
    }

    @Bean
    public Queue deadLetterQueue() {
        return QueueBuilder.durable("dlx.pagamento.queue").build();
    }

    @Bean
    public Binding deadLetterBinding(Queue deadLetterQueue, TopicExchange deadLetterExchange) {
        return BindingBuilder.bind(deadLetterQueue).to(deadLetterExchange).with("dlx.pagamento");
    }
}

