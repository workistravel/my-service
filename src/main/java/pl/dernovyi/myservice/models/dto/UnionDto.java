package pl.dernovyi.myservice.models.dto;


import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@EqualsAndHashCode(exclude = "name")
public class UnionDto  {
    private Long id;
    private String name;
    private Set<EmployeeDto> employees;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(id);
        sb.append("-").append(name);
        return sb.toString();
    }
}
