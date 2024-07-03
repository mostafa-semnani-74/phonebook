package ir.mostafa.semnani.phonebook.service;

public interface PersonNotificationService {
    void publishSavePersonEvent(String savePersonEventMessage);
}
