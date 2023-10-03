package ir.mostafa.semnani.phonebook.model.service;

public interface PersonNotificationService {
    void publishSavePersonEvent(String savePersonEventMessage);
}
