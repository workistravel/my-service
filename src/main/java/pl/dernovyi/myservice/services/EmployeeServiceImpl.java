package pl.dernovyi.myservice.services;

import pl.dernovyi.myservice.converters.ConvertToXLS;
import pl.dernovyi.myservice.exception.EmployeeNotFoundException;
import pl.dernovyi.myservice.models.dao.EmployeeDao;
import pl.dernovyi.myservice.models.dao.UnionDao;
import pl.dernovyi.myservice.models.dto.EmployeeDto;
import pl.dernovyi.myservice.models.dto.UnionDto;
import pl.dernovyi.myservice.repository.EmployeeRepo;

import java.util.*;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dernovyi.myservice.repository.UnionRepo;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private final EmployeeRepo employeeRepo;
    @Autowired
    private final UnionRepo unionRepo;


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
        Optional<EmployeeDao> employee = employeeRepo.findById(id);
         if(!employee.get().isActive() ) {
             if( !isNull(employee.get())){
                 return employeeDaoToDTO(employee.get());
             } throw new EmployeeNotFoundException(id);
         }
        throw new EmployeeNotFoundException(id);

    }

    public void toXLS(){
        new ConvertToXLS(
                employeeRepo.findAll().stream()
                .map(employeeDao -> employeeDao)
                .collect(Collectors.toCollection(ArrayList::new))).createXLS();

    }


    public List<EmployeeDto> getAllEmployee() {
        List<EmployeeDto> employees;

        employees =  employeeRepo.findAll().stream()
                .filter( e ->!e.isActive())
                .map(employeeDao -> employeeDaoToDTO(employeeDao))
                .collect(Collectors.toCollection(() -> new ArrayList<EmployeeDto>()));

        return employees;
    }

    private boolean isNull(EmployeeDao e) {
      return  e.getName()==null;
    }

    @Override
    public EmployeeDto deleteEmployeeById(Long id) {
         EmployeeDto employeeDto = getEmployeeById(id);
         employeeRepo.deleteById (id);
         return employeeDto;
    }
    //--------------------------------------------
    @Override
    public EmployeeDto changeEmployee(EmployeeDto employeeDto, Long id) {
        return employeeRepo.findById(id)
                .map(employee -> {
                    employee.setName(employeeDto.getName());
                    employeeRepo.save(employee);
                    return employeeDaoToDTO(employeeRepo.findById(id).get());
                }).orElseThrow(() -> new EmployeeNotFoundException(id));

    }

    public EmployeeDto employeeToActive(Long id){
        return employeeRepo.findById(id)
                .map(employee -> {
                    employee.setActive(true);
                      employeeRepo.save(employee);
                  return   employeeDaoToDTO(employeeRepo.findById(id).get());
                }).orElseThrow(() -> new EmployeeNotFoundException(id));
    }


    @Override
    public EmployeeDto addUnionForEmployee( Long id_empl , Long id_union) {

        Optional<UnionDao> union = Optional.ofNullable(unionRepo.findById(id_union)
                .orElseThrow(() -> new EmployeeNotFoundException(id_union , "union")));
        Optional<EmployeeDao> employee= Optional.ofNullable(employeeRepo.findById(id_empl)
                .orElseThrow(() -> new EmployeeNotFoundException(id_empl, "employee")));
        if(!employee.get().isActive()){
            employee.get().addUnion(union.get());
            employeeRepo.save(employee.get());
            Optional<EmployeeDao> dto=employeeRepo.findById(id_empl);
            return employeeDaoToDTO(dto.get());
        }
        throw new EmployeeNotFoundException(id_empl);


    }

    @Override
    public EmployeeDto removeUnionForEmployee( Long id_empl , Long id_union) {

        Optional<UnionDao> union = Optional.ofNullable(unionRepo.findById(id_union)
                .orElseThrow(() -> new EmployeeNotFoundException(id_union, "union")));
        Optional<EmployeeDao> employee= Optional.ofNullable(employeeRepo.findById(id_empl)
                .orElseThrow(() -> new EmployeeNotFoundException(id_empl, "employee")));
        if(!employee.get().isActive()) {
            employee.get().removeUnion(union.get());
            employeeRepo.save(employee.get());
            Optional<EmployeeDao> dto = employeeRepo.findById(id_empl);
            return employeeDaoToDTO(dto.get());
        }throw new EmployeeNotFoundException(id_empl);
    }
    @Override
    public List<EmployeeDto> myEmplStartWith(String s) {
        List<EmployeeDao> employees  = employeeRepo.employeeStatingWith(s);
      return   employees.stream()
                .map(employeeDao -> employeeDaoToDTO(employeeDao))

                .collect(Collectors.toCollection(() -> new ArrayList<EmployeeDto>()));

    }



    private EmployeeDto employeeDaoToDTO(EmployeeDao employeeDao){

            return EmployeeDto.builder()
                    .id(employeeDao.getId())
                    .name(employeeDao.getName())
                    .unions(toDTOs(employeeDao.getUnions()))
                    .build();


    }

    private Set<UnionDto> toDTOs(Set<UnionDao> union){
      return union.stream().map(u->toDto(u)).collect(Collectors.toSet());
    }

    private UnionDto toDto(UnionDao unionDao){
        return UnionDto.builder()
                .id(unionDao.getId())
                .name(unionDao.getName())
                .build();
    }
}
