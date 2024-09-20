package ir.mostafa.semnani.phonebook.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PersonDTO {
    private Long id;
    @NotNull(message = "name required")
    private String name;
    @NotNull(message = "age required")
    private Integer age;
}
