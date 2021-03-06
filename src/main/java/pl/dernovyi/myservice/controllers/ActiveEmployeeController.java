package pl.dernovyi.myservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dernovyi.myservice.models.dto.ActiveEmployeeDto;
import pl.dernovyi.myservice.models.dto.EmployeeDto;
import pl.dernovyi.myservice.services.ActiveEmployeeServiceImpl;


@RestController
@AllArgsConstructor
public class ActiveEmployeeController {
    @Autowired
    private final ActiveEmployeeServiceImpl employeeService;

    @GetMapping("/actemployee/{id}")
    public ResponseEntity<?> getActEmployee(@PathVariable Long id) {
        return new ResponseEntity<>(employeeService.getActEmployeeById(id), HttpStatus.OK);
    }

    @GetMapping(path="/actemployees")
    public ResponseEntity<?> getAllActEmployee(){
        return new ResponseEntity<>(employeeService.getAllActEmployee(), HttpStatus.OK);
    }


    @DeleteMapping(path = "/actemployee/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return new ResponseEntity<>(employeeService.deleteActEmployeeById (id),HttpStatus.OK);
    }

    @PostMapping(value = "/actemployee")
    @ResponseBody
    public ResponseEntity<?> putActEmployee(ActiveEmployeeDto employeeDto){
        return new ResponseEntity<>(employeeService.createActEmployee(employeeDto),HttpStatus.CREATED);
    }

    @PutMapping("/actemployee/{id}")
    ResponseEntity<?> replaceEmployee(ActiveEmployeeDto employeeDto, @PathVariable Long id) {
        employeeService.changeActEmployee(employeeDto,id);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);

    }

    //localhost:8080/actAddUnion/12/1
    @PutMapping("/actAddUnion/{id_em}/{id_un}")
    EmployeeDto addUnForEmp(@PathVariable Long id_em,@PathVariable Long id_un ){

        return   employeeService.addActUnionForEmployee(id_em, id_un);

    }

    //localhost:8080/actRemoveUnion/12/1
    @PutMapping("/actRemoveUnion/{id_em}/{id_un}")
    EmployeeDto removeUnForEmp(@PathVariable Long id_em,@PathVariable Long id_un ){

        return   employeeService.removeActUnionForEmployee(id_em, id_un);

    }
}
