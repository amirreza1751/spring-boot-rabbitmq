package com.amqp.server2.component;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import com.amqp.server2.Server2Application;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    public Runner(Receiver receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        for (int i=0; i<50; i++){
            rabbitTemplate.convertAndSend(Server2Application.topicExchangeName, "foo.bar.baz", "server 2 to server 2");
        }
    }

}
