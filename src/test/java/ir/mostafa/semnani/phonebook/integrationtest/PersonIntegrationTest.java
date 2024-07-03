package ir.mostafa.semnani.phonebook.integrationtest;

import ir.mostafa.semnani.phonebook.dto.PersonDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonIntegrationTest {
    @LocalServerPort
    private int port;
    private static final String LOCAL_HOST_URL = "http://localhost:";
    private static final String BASE_API_URL = "/api/v1/persons";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @Order(1)
    public void savePersonIntegrationTest() {
        String url = LOCAL_HOST_URL + port + BASE_API_URL;
        PersonDTO personDTO = PersonDTO.builder()
                .name("mosi")
                .build();
        ResponseEntity<PersonDTO> savedPersonDTO = testRestTemplate.postForEntity(url, personDTO, PersonDTO.class);
        assertEquals(HttpStatus.CREATED, savedPersonDTO.getStatusCode());
        assertNotNull(savedPersonDTO.getBody());
        assertEquals("mosi", savedPersonDTO.getBody().getName());
    }

    @Test
    @Order(2)
    public void findAllPersonsIntegrationTest() {
        String url = LOCAL_HOST_URL + port + BASE_API_URL;
        ResponseEntity<PersonDTO[]> persons = testRestTemplate.getForEntity(url, PersonDTO[].class);
        assertEquals(HttpStatus.OK, persons.getStatusCode());
        assertNotNull(persons.getBody());

        List<PersonDTO> personsDTOList = List.of(persons.getBody());
        assertEquals(1, personsDTOList.size());
        assertEquals("mosi", personsDTOList.get(0).getName());
    }

}
