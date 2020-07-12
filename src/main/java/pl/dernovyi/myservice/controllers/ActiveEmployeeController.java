package pl.dernovyi.myservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
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
}
