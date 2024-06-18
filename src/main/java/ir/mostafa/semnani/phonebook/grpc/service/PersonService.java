package ir.mostafa.semnani.phonebook.grpc.service;

import grpc.proto.Empty;
import grpc.proto.Person;
import grpc.proto.PersonServiceGrpc;
import io.grpc.stub.StreamObserver;
import ir.mostafa.semnani.phonebook.model.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class PersonService extends PersonServiceGrpc.PersonServiceImplBase {
    private final PersonRepository personRepository;

    @Override
    public void findAll(Empty request, StreamObserver<Person> responseObserver) {
        personRepository.findAll().forEach(person ->
                responseObserver.onNext(
                        Person.newBuilder()
                                .setId(person.getId())
                                .setName(person.getName())
                                .build()
                ));

        responseObserver.onCompleted();
    }
}
