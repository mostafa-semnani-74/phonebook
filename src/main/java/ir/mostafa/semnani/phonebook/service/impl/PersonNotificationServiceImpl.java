package ir.mostafa.semnani.phonebook.service.impl;

import ir.mostafa.semnani.phonebook.service.PersonNotificationService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonNotificationServiceImpl implements PersonNotificationService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void publishSavePersonEvent(String savePersonEventMessage) {
        kafkaTemplate.send("person", savePersonEventMessage);
    }

}
