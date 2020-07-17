package pl.dernovyi.myservice.models.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder
//@EqualsAndHashCode(exclude = "unions")
public class EmployeeDto {
    private Long id;
    private String name;
    private Set<UnionDto> unions;
}
