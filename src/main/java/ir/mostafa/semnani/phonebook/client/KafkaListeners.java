package ir.mostafa.semnani.phonebook.client;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "spring.kafka.enabled", havingValue = "true")
public class KafkaListeners {
    @KafkaListener(
            topics = "person",
            groupId = "foo")
    void listener(String message) {
        System.out.println("message received : " + message);
    }
}
