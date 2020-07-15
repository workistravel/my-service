package pl.dernovyi.myservice.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.dernovyi.myservice.models.dao.EmployeeDao;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends CrudRepository<EmployeeDao,Long> {


    @Override
    List<EmployeeDao> findAll();

    @Override
    <S extends EmployeeDao> S save(S s);

    @Override
    Optional<EmployeeDao> findById(Long id);

    // my own query
    @Query("Select e from EmployeeDao e WHERE e.name like :s%")
    List<EmployeeDao> employeeStatingWith(String s);
}
