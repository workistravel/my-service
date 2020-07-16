package pl.dernovyi.myservice.models.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.dernovyi.myservice.models.dao.UnionDao;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@SuperBuilder

//@AllArgsConstructor(onConstructor=@__(@Builder))
public class ActiveEmployeeDto extends EmployeeDto {
    private Double salary;
    private Date dateOfEmployment;
}
