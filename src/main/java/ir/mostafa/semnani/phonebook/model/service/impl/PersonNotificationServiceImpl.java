package ir.mostafa.semnani.phonebook.model.service.impl;

import ir.mostafa.semnani.phonebook.model.service.PersonNotificationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonNotificationServiceImpl implements PersonNotificationService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void publishSavePersonEvent(String savePersonEventMessage) {
        kafkaTemplate.send("person", savePersonEventMessage);
    }

}
