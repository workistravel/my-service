package pl.dernovyi.myservice.controllers;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
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
}
