package com.servicesilo.one.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbit innit
 */
@Configuration
public class TopicRabbitConfig {
    /*
        exchange - name
     */
    private final static String exchange_name = "topicExchange";
    /*
        routingKey - name
     */
    private final static String routingKey1 = "topic.A";
    private final static String routingKey2 = "topic.#";

    /*
       Queue - name
     */
    private static String QueueName_A = "QueueA";
    private static String QueueName_B = "QueueB";

    /*
        Queue - matchRule
     */
    @Bean
    public Queue firstQueue() {
        return new Queue(TopicRabbitConfig.QueueName_A);
    }

    @Bean
    public Queue secondQueue() {
        return new Queue(TopicRabbitConfig.QueueName_B);
    }

    /*
        exchange - set
     */
    @Bean
    TopicExchange exchange() {
        return new TopicExchange(exchange_name);
    }


    /*
        bind - queue->exchange->routingKey
     */
    @Bean
    Binding bindingExchangeMessage() {
        return BindingBuilder.bind(firstQueue()).to(exchange()).with(routingKey1);
    }

    @Bean
    Binding bindingExchangeMessage2() {
        return BindingBuilder.bind(secondQueue()).to(exchange()).with(routingKey2);
    }

}
