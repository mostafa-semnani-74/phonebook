package ir.mostafa.semnani.phonebook.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import ir.mostafa.semnani.phonebook.dto.AddressDTO;
import ir.mostafa.semnani.phonebook.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/addresses")
@RequiredArgsConstructor
@Tag(name = "Address", description = "Address APIs")
public class AddressController {
    private final AddressService addressService;

    @Operation(
            summary = "Retrieve all addresses",
            description = "response is addresses List")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all the addresses",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = AddressDTO.class)))})
    })
    @GetMapping
    public ResponseEntity<List<AddressDTO>> findAll() {
        List<AddressDTO> addressDTOS = addressService.findAll();
        return ResponseEntity.ok(addressDTOS);
    }

    @Operation(summary = "Get a address by its id",
            description = "response is a address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "response is a address object",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AddressDTO.class))})
    })
    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(addressService.findById(id));
    }

    @Operation(summary = "save a address",
            description = "response is a saved address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "response is a saved address object",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AddressDTO.class))})
    })
    @PostMapping
    public ResponseEntity<AddressDTO> save(@RequestBody AddressDTO addressDTO) {
        addressService.save(addressDTO);
        return new ResponseEntity<>(addressDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "update a address",
            description = "response is a saved address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "response is a saved address object",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AddressDTO.class))})
    })
    @PutMapping
    public ResponseEntity<AddressDTO> update(@RequestBody AddressDTO addressDTO) {
        return ResponseEntity.ok(addressService.update(addressDTO));
    }

    @Operation(summary = "delete a address by its id",
            description = "response is an ok 200 http status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "response is an ok 200 http status")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        addressService.delete(id);
        return ResponseEntity.ok().build();
    }
}
