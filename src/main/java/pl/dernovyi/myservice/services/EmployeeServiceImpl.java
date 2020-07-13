package pl.dernovyi.myservice.services;


import pl.dernovyi.myservice.exception.EmployeeNotFoundException;
import pl.dernovyi.myservice.models.dao.EmployeeDao;
import pl.dernovyi.myservice.models.dao.UnionDao;
import pl.dernovyi.myservice.models.dto.ActiveEmployeeDto;
import pl.dernovyi.myservice.models.dto.EmployeeDto;

import org.springframework.beans.factory.annotation.Autowired;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dernovyi.myservice.repository.EmployeeRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private final EmployeeRepo employeeRepo;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        EmployeeDao employee =EmployeeDao.builder()
                .id(employeeDto.getId())
                .name(employeeDto.getName())
                .build();
        employeeRepo.save(employee);
        return employeeDto;
    }



    @Override
    public EmployeeDto getEmployeeById(Long id) {

        EmployeeDao employee = employeeRepo.findById(id).orElseGet(() -> new EmployeeDao());
         if(!employee.isActive()) {
            return employeeDaoToDTO(employee);
         }
        throw new EmployeeNotFoundException(id);

    }


    public List<EmployeeDto> getAllEmployee() {
        List<EmployeeDto> employees;

        employees =  employeeRepo.findAll().stream()
                .filter( e ->!e.isActive())
                .map(employeeDao -> employeeDaoToDTO(employeeDao))
                .collect(Collectors.toCollection(() -> new ArrayList<EmployeeDto>()));
        return employees;
    }

    @Override
    public EmployeeDto deleteEmployeeById(Long id) {
         EmployeeDto employeeDto = getEmployeeById(id);
         employeeRepo.deleteById (id);
         return employeeDto;
    }

    @Override
    public EmployeeDto changeEmployee(EmployeeDto employeeDto, Long id) {
       return employeeRepo.findById(id)
                .map(employee -> {
                    employee.setName(employeeDto.getName());
                    employeeRepo.save(employee);
                    return employeeDto;
                }).orElseThrow(() -> new EmployeeNotFoundException(id));
//                .orElseGet(() -> {
//                    employeeDto.setId(id);
//                    employeeRepo.save( EmployeeDao.builder()
//                            .id(employeeDto.getId())
//                            .name(employeeDto.getName())
//                            .build());
//                    return employeeDto;
//                });

    }

    private EmployeeDto employeeDaoToDTO(EmployeeDao employeeDao){
        return new EmployeeDto(employeeDao.getId(), employeeDao.getName());
    }
}
