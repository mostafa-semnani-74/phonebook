package ir.mostafa.semnani.phonebook.model.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;

@Data
@Builder
public class AddressDTO {
    private Long Id;
    private String description;
}
