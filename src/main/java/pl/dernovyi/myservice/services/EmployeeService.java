package pl.dernovyi.myservice.services;

import pl.dernovyi.myservice.models.dao.EmployeeDao;
import pl.dernovyi.myservice.models.dto.ActiveEmployeeDto;
import pl.dernovyi.myservice.models.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto getEmployeeById(Long id);

    List<EmployeeDto> getAllEmployee();

    EmployeeDto deleteEmployeeById(Long id);

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto changeEmployee(EmployeeDto employeeDto , Long id);

    List<EmployeeDto> myEmplStartWith(String s);

    EmployeeDto addUnionForEmployee(Long id_em, Long id_un);

    EmployeeDto removeUnionForEmployee(Long id_em, Long id_un);

    EmployeeDto  employeeToActive(Long id);

    void toXLS();
}
