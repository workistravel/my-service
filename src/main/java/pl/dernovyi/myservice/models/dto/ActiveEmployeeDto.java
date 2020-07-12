package pl.dernovyi.myservice.models.dto;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ActiveEmployeeDto extends EmployeeDto {
    private Double salary;
    private Date dateOfEmployment;

    public ActiveEmployeeDto(Long id, String name, Double salary, Date dateOfEmployment) {
        super(id, name);
        this.salary = salary;
        this.dateOfEmployment = dateOfEmployment;
    }
}
