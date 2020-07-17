package pl.dernovyi.myservice.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.Date;

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
