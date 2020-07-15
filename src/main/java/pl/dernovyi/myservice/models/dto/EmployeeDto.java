package pl.dernovyi.myservice.models.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.dernovyi.myservice.models.dao.UnionDao;

import java.util.List;
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
