package ir.mostafa.semnani.phonebook.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonDTO {
    private Long id;
    private String name;
}
