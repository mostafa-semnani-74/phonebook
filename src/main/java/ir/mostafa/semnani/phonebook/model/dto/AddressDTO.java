package ir.mostafa.semnani.phonebook.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDTO {
    private Long Id;
    private String description;
    private PersonDTO personDTO;
}
