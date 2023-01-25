package com.niit.Soulmateservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

    private String exchangeName="SoulmateUserExchange";
    private String queueName="SoulmateUserQueue";

    @Bean
    public DirectExchange getDirectExchange(){
        return new DirectExchange(exchangeName);
    }
    @Bean
    public Queue getQueue(){
        return new Queue(queueName);
    }
    @Bean
    public Binding getBinding(){
        return BindingBuilder.bind(getQueue()).to(getDirectExchange()).with("user_routing");
    }
    @Bean
    public Jackson2JsonMessageConverter getJackson2JsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public RabbitTemplate getRabbitTemplate(final ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(getJackson2JsonMessageConverter());
        return rabbitTemplate;
    }
}
