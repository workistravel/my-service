package pl.dernovyi.myservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dernovyi.myservice.exception.EmployeeNotFoundException;
import pl.dernovyi.myservice.models.dao.EmployeeDao;
import pl.dernovyi.myservice.models.dao.UnionDao;
import pl.dernovyi.myservice.models.dto.ActiveEmployeeDto;
import pl.dernovyi.myservice.models.dto.EmployeeDto;
import pl.dernovyi.myservice.models.dto.UnionDto;
import pl.dernovyi.myservice.repository.EmployeeRepo;
import pl.dernovyi.myservice.repository.UnionRepo;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActiveEmployeeServiceImpl implements ActiveEmployeeService {
    @Autowired
    private final EmployeeRepo employeeRepo;

    @Autowired
    private final UnionRepo unionRepo;

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
       return    employeeRepo.findAll().stream()
                .filter( e ->e.isActive())
                .map(employeeDao -> getActiveEmployeeDaoToDTO(employeeDao))
                .collect(Collectors.toCollection(() -> new ArrayList<ActiveEmployeeDto>()));

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

    @Override
    public EmployeeDto addActUnionForEmployee( Long id_empl , Long id_union) {

        Optional<UnionDao> union = Optional.ofNullable(unionRepo.findById(id_union)
                .orElseThrow(() -> new EmployeeNotFoundException(id_union , "union")));
        Optional<EmployeeDao> employee= Optional.ofNullable(employeeRepo.findById(id_empl)
                .orElseThrow(() -> new EmployeeNotFoundException(id_empl, "employee")));
        if(employee.get().isActive())
        {
            employee.get().addUnion(union.get());
            employeeRepo.save(employee.get());
            Optional<EmployeeDao> dto=employeeRepo.findById(id_empl);
            return getActiveEmployeeDaoToDTO(dto.get());
        }
        throw new EmployeeNotFoundException(id_empl);


    }

    @Override
    public EmployeeDto removeActUnionForEmployee( Long id_empl , Long id_union) {

        Optional<UnionDao> union = Optional.ofNullable(unionRepo.findById(id_union)
                .orElseThrow(() -> new EmployeeNotFoundException(id_union , "union")));
        Optional<EmployeeDao> employee= Optional.ofNullable(employeeRepo.findById(id_empl)
                .orElseThrow(() -> new EmployeeNotFoundException(id_empl, "employee")));
        if(employee.get().isActive())
        {
        employee.get().removeUnion(union.get());
        employeeRepo.save(employee.get());
        Optional<EmployeeDao> dto=employeeRepo.findById(id_empl);
        return getActiveEmployeeDaoToDTO(dto.get());
        }
        throw new EmployeeNotFoundException(id_empl);

    }

    private ActiveEmployeeDto getActiveEmployeeDaoToDTO(EmployeeDao employeeDao) {
        Set<UnionDto> unionDtos = toDTOs(employeeDao.getUnions());
        return ActiveEmployeeDto.builder()
                .id(employeeDao.getId())
                .name(employeeDao.getName())
                .salary(employeeDao.getSalary())
                .dateOfEmployment(employeeDao.getDateOfEmployment())
                .unions(unionDtos)
                .build();
            }

    private Set<UnionDto> toDTOs(Set<UnionDao> unions) {
        return unions.stream().map(u->toDto(u)).collect(Collectors.toSet());
    }

    private UnionDto toDto(UnionDao u) {
      return   UnionDto.builder()
                .id(u.getId())
                .name(u.getName())
                .build();
    }
}
