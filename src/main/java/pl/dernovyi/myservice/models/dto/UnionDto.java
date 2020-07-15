package pl.dernovyi.myservice.models.dto;


import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@EqualsAndHashCode(exclude = "name")
public class UnionDto {
    private Long id;
    private String name;
    private Set<EmployeeDto> employees;
}
