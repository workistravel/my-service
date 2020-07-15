package pl.dernovyi.myservice.services;

import pl.dernovyi.myservice.models.dto.ActiveEmployeeDto;
import pl.dernovyi.myservice.models.dto.EmployeeDto;

import java.util.List;

public interface ActiveEmployeeService {
    ActiveEmployeeDto getActEmployeeById(Long id);

    List<ActiveEmployeeDto> getAllActEmployee();

    ActiveEmployeeDto deleteActEmployeeById(Long id);

    ActiveEmployeeDto createActEmployee(ActiveEmployeeDto employeeDto);

    ActiveEmployeeDto changeActEmployee(ActiveEmployeeDto employeeDto ,Long id);

    EmployeeDto addActUnionForEmployee(Long id_em, Long id_un);

    EmployeeDto removeActUnionForEmployee(Long id_em, Long id_un);

}
