package ir.mostafa.semnani.phonebook.integrationtest;

import ir.mostafa.semnani.phonebook.dto.PersonDTO;
import ir.mostafa.semnani.phonebook.security.model.dto.AuthenticationRequest;
import ir.mostafa.semnani.phonebook.security.model.dto.AuthenticationResponse;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.domain.Page;
import org.springframework.http.*;

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
    private static final String BASE_AUTHENTICATE_URL = "/api/v1/auth";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @Order(1)
    public void savePersonIntegrationTest() {
        String url = LOCAL_HOST_URL + port + BASE_API_URL;
        String adminToken = getTokenAsAdmin();
        var personDTO = PersonDTO.builder()
                .name("mosi")
                .build();
        var headers = makeRequestHeader(adminToken);
        var httpEntity = new HttpEntity<>(personDTO, headers);
        var savedPersonDTO =
                testRestTemplate.exchange(url, HttpMethod.POST, httpEntity, PersonDTO.class);

        assertEquals(HttpStatus.CREATED, savedPersonDTO.getStatusCode());
        assertNotNull(savedPersonDTO.getBody());
        assertEquals("mosi", savedPersonDTO.getBody().getName());
    }

    @Test
    @Order(2)
    public void findAllPersonsIntegrationTest() {
        String url = LOCAL_HOST_URL + port + BASE_API_URL;
        String adminToken = getTokenAsAdmin();
        var headers = makeRequestHeader(adminToken);
        var httpEntity = new HttpEntity<>(headers);
        var persons = testRestTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

        assertEquals(HttpStatus.OK, persons.getStatusCode());
        assertNotNull(persons.getBody());

//        Page<PersonDTO> personsDTOList = (Page<PersonDTO>) persons.getBody();
//        assertEquals(1, personsDTOList.getTotalElements());
//        assertEquals("mosi", personsDTOList.iterator().next().getName());
    }

    private String getTokenAsAdmin() {
        String authenticateUrl = LOCAL_HOST_URL + port + BASE_AUTHENTICATE_URL + "/authenticate";

        var authenticationRequest = new AuthenticationRequest("admin", "123");
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-api-key", "free");

        var httpEntity = new HttpEntity<>(authenticationRequest, headers);

        var authenticationResponse =
                testRestTemplate.exchange(authenticateUrl, HttpMethod.POST, httpEntity, AuthenticationResponse.class);
        return authenticationResponse.getBody().getToken();
    }

    private HttpHeaders makeRequestHeader(String token) {
        var headers = new HttpHeaders();
        headers.add("X-api-key", "free");
        headers.add("Authorization", "Bearer " + token);
        return headers;
    }

}
