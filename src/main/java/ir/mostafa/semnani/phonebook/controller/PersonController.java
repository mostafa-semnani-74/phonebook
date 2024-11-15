package ir.mostafa.semnani.phonebook.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import ir.mostafa.semnani.phonebook.dto.PageDTO;
import ir.mostafa.semnani.phonebook.dto.PersonCriteriaDTO;
import ir.mostafa.semnani.phonebook.dto.PersonDTO;
import ir.mostafa.semnani.phonebook.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/persons")
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:5173/")
@Tag(name = "Person", description = "Person APIs")
public class PersonController {
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
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Page<PersonDTO>> findAll(@RequestParam(required = false, defaultValue = "10") String pageSize,
                                                   @RequestParam(required = false, defaultValue = "0") String pageNumber,
                                                   @RequestParam(required = false) Boolean isAdult,
                                                   @RequestParam(required = false) String name) {
        PageDTO pageDTO = new PageDTO(Integer.parseInt(pageSize), Integer.parseInt(pageNumber));

        PersonCriteriaDTO personCriteriaDTO = PersonCriteriaDTO.builder()
                .isAdult(isAdult)
                .name(name)
                .build();

        Page<PersonDTO> persons = personService.findAll(pageDTO, personCriteriaDTO);
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
    @PreAuthorize("hasAuthority('person:read')")
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
    @PreAuthorize("hasAuthority('person:write')")
    public ResponseEntity<PersonDTO> save(@RequestBody PersonDTO personDTO) {
        return new ResponseEntity<>(personService.save(personDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "save a person concurrently",
            description = "response is a saved person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "response is a saved person object",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PersonDTO.class))})
    })
    @PostMapping("/concurrent")
    @PreAuthorize("hasAuthority('person:write')")
    public ResponseEntity<PersonDTO> saveConcurrently(@Valid @RequestBody PersonDTO personDTO) {
        PersonDTO savedPersonDto = personService.saveConcurrently(personDTO);
        return new ResponseEntity<>(savedPersonDto, HttpStatus.CREATED);
    }

    @Operation(summary = "update a person",
            description = "response is a saved person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "response is a saved person object",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PersonDTO.class))})
    })
    @PutMapping
    @PreAuthorize("hasAuthority('person:write')")
    public ResponseEntity<PersonDTO> update(@RequestBody PersonDTO personDTO) {
        return ResponseEntity.ok(personService.update(personDTO));
    }

    @Operation(summary = "delete a person by its id",
            description = "response is an ok 200 http status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "response is an ok 200 http status")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('person:write')")
    public ResponseEntity delete(@PathVariable Long id) {
        personService.delete(id);
        return ResponseEntity.ok().build();
    }

}
