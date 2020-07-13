package pl.dernovyi.myservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dernovyi.myservice.exception.EmployeeNotFoundException;
import pl.dernovyi.myservice.models.dao.EmployeeDao;
import pl.dernovyi.myservice.models.dto.ActiveEmployeeDto;
import pl.dernovyi.myservice.models.dto.EmployeeDto;
import pl.dernovyi.myservice.repository.EmployeeRepo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActiveEmployeeServiceImpl implements ActiveEmployeeService {
    @Autowired
    private final EmployeeRepo employeeRepo;

    @Override
    public ActiveEmployeeDto createActEmployee(ActiveEmployeeDto employeeDto) {
        Date date = new Date();
        date.toInstant();
        EmployeeDao employee =EmployeeDao.builder()
                .id(employeeDto.getId())
                .name(employeeDto.getName())
                .salary(employeeDto.getSalary())
                .dateOfEmployment(date)
                .isActive(true)
                .build();
        employeeRepo.save(employee);
        return employeeDto;
    }
    @Override
    public ActiveEmployeeDto getActEmployeeById(Long id) {
        EmployeeDao employeeDto = employeeRepo.findById(id).orElseGet(() -> new EmployeeDao());

        if(employeeDto.isActive()) {
            return getActiveEmployeeDaoToDTO(employeeDto);
        }
        throw new EmployeeNotFoundException(id);

    }

    @Override
    public List<ActiveEmployeeDto> getAllActEmployee() {
        List<ActiveEmployeeDto> employees;

        employees =  employeeRepo.findAll().stream()
                .filter( e ->e.isActive())
                .map(employeeDao -> getActiveEmployeeDaoToDTO(employeeDao))
                .collect(Collectors.toCollection(() -> new ArrayList<ActiveEmployeeDto>()));
        return employees;
    }

    @Override
    public ActiveEmployeeDto deleteActEmployeeById(Long id) {
        ActiveEmployeeDto employeeDto = getActEmployeeById(id);
        employeeRepo.deleteById(id);
        return employeeDto;
    }

    @Override
    public ActiveEmployeeDto changeActEmployee(ActiveEmployeeDto employeeDto, Long id) {
        Date date = new Date();
        date.toInstant();
        return employeeRepo.findById(id)
                .map(employee -> {
                    employee.setName(employeeDto.getName());
                    employee.setSalary(employeeDto.getSalary());
                    employee.setDateOfEmployment(date);
                    employeeRepo.save(employee);
                    return employeeDto;
                }).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    private ActiveEmployeeDto getActiveEmployeeDaoToDTO(EmployeeDao employeeDao) {
        return new ActiveEmployeeDto(employeeDao.getId(),
                employeeDao.getName(),
                employeeDao.getSalary(),
                employeeDao.getDateOfEmployment());
    }
}
