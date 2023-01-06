package com.amqp.server1.component;

import com.amqp.server1.Server1Application;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class Runner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;

    public Runner(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message from server1...");
        for (int i=0; i<50; i++){
            rabbitTemplate.convertAndSend(Server1Application.topicExchangeName, "foo.bar.baz", "Hello from server1 " + LocalDateTime.now());
        }
    }

}
