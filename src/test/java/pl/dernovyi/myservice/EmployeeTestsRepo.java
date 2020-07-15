package pl.dernovyi.myservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.dernovyi.myservice.models.dao.EmployeeDao;
import pl.dernovyi.myservice.repository.EmployeeRepo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeTestsRepo {
    @Autowired
    private  EmployeeRepo employeeRepository;
    static Date date = new Date();
    EmployeeDao savedEmployee = new EmployeeDao();
    @BeforeEach
    public void SetUp(){
       date.toInstant();

       savedEmployee= employeeRepository.save(EmployeeDao.builder()
                .id(1L)
                .name("David")
                .isActive(false)
                .salary(1299.0)
                .dateOfEmployment(date)
                .build());
    }
    @Test
    public void testCreateEmployee(){
        assertNotNull(savedEmployee);
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    @Test
    public void testFindProductById() {
        Optional<EmployeeDao> savedEmployee = employeeRepository.findById(1L);
        assertThat(savedEmployee.get().getName()).isEqualTo("David");


    }

    @Test
    public void testFindProductByIService() {
        employeeRepository.findById(1L);



    }


    @Test
    public void testGetAllEmployee(){
        List<EmployeeDao> listEmployees = employeeRepository.findAll();
        assertThat(listEmployees).hasSize(1).contains(savedEmployee);

    }

    @Test
    public void testDeleteEmployee(){
        employeeRepository.save(EmployeeDao.builder()
        .name("Dima")
        .build());
        employeeRepository.deleteById(2L);
        assertThat(employeeRepository.findAll().size()).isOne();
    }

}
