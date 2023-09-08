package ir.mostafa.semnani.phonebook.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class PersonDTO {
    private Long id;
    private String name;
}
