package pl.dernovyi.myservice.services;


import pl.dernovyi.myservice.exception.EmployeeNotFoundException;
import pl.dernovyi.myservice.models.dao.EmployeeDao;
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
    public EmployeeDto getEmployeeById(Long id) {

         Optional<EmployeeDao> employee = Optional.ofNullable(employeeRepo.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id)));
         if(!employee.get().isActive()) {
             return new EmployeeDto(
                     employee.get().getId(),
                     employee.get().getName());
         }
        throw new EmployeeNotFoundException(id);

    }


    public List<EmployeeDto> getAllEmployee() {
        List<EmployeeDto> employees;

        employees =  employeeRepo.findAll().stream()
                .filter( e ->!e.isActive())
                .map(employeeDao -> EmployeeDaoToDTO(employeeDao))
                .collect(Collectors.toCollection(() -> new ArrayList<EmployeeDto>()));
        return employees;
    }





    private EmployeeDto EmployeeDaoToDTO(EmployeeDao employeeDao){
        return new EmployeeDto(employeeDao.getId(), employeeDao.getName());
    }
}
