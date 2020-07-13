package pl.dernovyi.myservice.models.dto;

import lombok.*;
import pl.dernovyi.myservice.models.dao.UnionDao;

import java.util.Set;

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
    private Set<UnionDao> unions;
}
