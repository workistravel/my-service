package pl.dernovyi.myservice.models.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@EqualsAndHashCode(exclude = "name")
public class EmployeeDto {
    private Long id;
    private String name;
}
