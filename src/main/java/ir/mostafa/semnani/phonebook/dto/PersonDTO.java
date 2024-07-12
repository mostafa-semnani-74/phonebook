package ir.mostafa.semnani.phonebook.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PersonDTO {
    private Long id;
    private String name;
    private Integer age;
}
