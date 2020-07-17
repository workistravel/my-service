package pl.dernovyi.myservice.controllers;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @GetMapping("/toXLS")
    public ResponseEntity<?> toXLS() {
        employeeService.toXLS();
       return new ResponseEntity<> (HttpStatus.OK);
    }

    @GetMapping("/toXML")
    public ResponseEntity<?> toXML() {
        employeeService.toXML();
        return new ResponseEntity<> (HttpStatus.OK);
    }

    @GetMapping(path="/start/{s}")
    public ResponseEntity<?> myEmplStartWith(@PathVariable String s){
       return  new ResponseEntity<>(employeeService.myEmplStartWith(s),HttpStatus.OK);
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

    @PutMapping("/toActive/{id}")//@RequestBody
    EmployeeDto toActive(@PathVariable Long id) {

        return  employeeService.employeeToActive(id);
    }

    //localhost:8080/addUnion/12/1
    @PutMapping("/addUnion/{id_em}/{id_un}")
    EmployeeDto addUnForEmp(@PathVariable Long id_em,@PathVariable Long id_un ){

        return   employeeService.addUnionForEmployee(id_em, id_un);

    }

    //localhost:8080/removeUnion/12/1
    @PutMapping("/removeUnion/{id_em}/{id_un}")
    EmployeeDto removeUnForEmp(@PathVariable Long id_em,@PathVariable Long id_un ){

        return   employeeService.removeUnionForEmployee(id_em, id_un);

    }

}
