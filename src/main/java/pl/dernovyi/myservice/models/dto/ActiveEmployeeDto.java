package pl.dernovyi.myservice.models.dto;

import lombok.*;
import pl.dernovyi.myservice.models.dao.UnionDao;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ActiveEmployeeDto extends EmployeeDto {
    private Double salary;
    private Date dateOfEmployment;

    public ActiveEmployeeDto(Double salary, Date dateOfEmployment) {
        this.salary = salary;
        this.dateOfEmployment = dateOfEmployment;
    }

    public ActiveEmployeeDto(Long id, String name, Set<UnionDao> unions, Double salary, Date dateOfEmployment) {
        super(id, name, unions);
        this.salary = salary;
        this.dateOfEmployment = dateOfEmployment;
    }
}
