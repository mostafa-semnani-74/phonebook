package ir.mostafa.semnani.phonebook.client;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {
    @KafkaListener(
            topics = "person",
            groupId = "foo")
    void listener(String message) {
        System.out.println("message received : " + message);
    }
}
