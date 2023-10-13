package ir.mostafa.semnani.phonebook.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import ir.mostafa.semnani.phonebook.model.dto.PersonDTO;
import ir.mostafa.semnani.phonebook.model.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/persons")
@AllArgsConstructor
@CrossOrigin(value = "http://localhost:5173/")
@Tag(name = "Person", description = "Person APIs")
public class PersonController {
    @Autowired
    private final PersonService personService;

    @Operation(
            summary = "Retrieve all persons",
            description = "response is persons List")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all the persons",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class)))})
    })
    @GetMapping
    public ResponseEntity<List<PersonDTO>> findAll() {
        List<PersonDTO> persons = personService.findAll();
        return ResponseEntity.ok(persons);
    }

    @Operation(summary = "Get a person by its id",
            description = "response is a person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "response is a person object",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PersonDTO.class))})
    })
    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(personService.findById(id));
    }

    @Operation(summary = "save a person",
            description = "response is a saved person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "response is a saved person object",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PersonDTO.class))})
    })
    @PostMapping
    public ResponseEntity<PersonDTO> save(@RequestBody PersonDTO personDTO) {
        personService.save(personDTO);
        return new ResponseEntity<>(personDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "save a person concurrently",
            description = "response is a saved person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "response is a saved person object",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PersonDTO.class))})
    })
    @PostMapping("/concurrent")
    public ResponseEntity<PersonDTO> saveConcurrently(@RequestBody PersonDTO personDTO) {
        personService.saveConcurrently(personDTO);
        return new ResponseEntity<>(personDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "update a person",
            description = "response is a saved person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "response is a saved person object",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PersonDTO.class))})
    })
    @PutMapping
    public ResponseEntity<PersonDTO> update(@RequestBody PersonDTO personDTO) {
        return ResponseEntity.ok(personService.update(personDTO));
    }

    @Operation(summary = "delete a person by its id",
            description = "response is an ok 200 http status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "response is an ok 200 http status")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        personService.delete(id);
        return ResponseEntity.ok().build();
    }

}
