package ir.mostafa.semnani.phonebook.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonCriteriaDTO {
    private String name;
    private Integer age;
    private Boolean isAdult;
}
