package pl.dernovyi.myservice.controllers;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dernovyi.myservice.models.dao.EmployeeDao;
import pl.dernovyi.myservice.models.dto.EmployeeDto;
import pl.dernovyi.myservice.services.EmployeeServiceImpl;

@RestController
@AllArgsConstructor
public class EmployeeController {

    @Autowired
    private final EmployeeServiceImpl employeeService;
//_________________________________________________________
    @GetMapping("/")
    public String index(){
        return "HELLO MY FRIEND";
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable Long id) {

        return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }

    @GetMapping(path="/employees")
    public ResponseEntity<?> getAllEmployee(){
        return new ResponseEntity<>(employeeService.getAllEmployee(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/employee/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return new ResponseEntity<>(employeeService.deleteEmployeeById (id),HttpStatus.OK);
    }

    @PostMapping(value = "/employee")
    @ResponseBody
    public ResponseEntity<?> putEmployee(EmployeeDto employeeDto){
        return new ResponseEntity<>(employeeService.createEmployee(employeeDto),HttpStatus.CREATED);
    }

    @PutMapping("/employee/{id}")//@RequestBody
    EmployeeDto replaceEmployee(EmployeeDto employeeDto, @PathVariable Long id) {
        employeeService.changeEmployee(employeeDto,id);
        return employeeDto;

    }
}
