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
}
