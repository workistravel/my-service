package pl.dernovyi.myservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dernovyi.myservice.exception.EmployeeNotFoundException;
import pl.dernovyi.myservice.models.dao.EmployeeDao;
import pl.dernovyi.myservice.models.dto.ActiveEmployeeDto;
import pl.dernovyi.myservice.repository.EmployeeRepo;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class ActiveEmployeeServiceImpl implements ActiveEmployeeService {
    @Autowired
    private final EmployeeRepo employeeRepo;
    @Override
    public ActiveEmployeeDto getActEmployeeById(Long id) {

        Optional<EmployeeDao> employee = Optional.ofNullable(employeeRepo.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id)));
        if(employee.get().isActive()) {
            return new ActiveEmployeeDto(
                    employee.get().getId(),
                    employee.get().getName(),
                    employee.get().getSalary(),
                    employee.get().getDateOfEmployment());

        }
        throw new EmployeeNotFoundException(id);

    }
}
