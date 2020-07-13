package pl.dernovyi.myservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
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
    Optional<EmployeeDao> findById(Long aLong);
}
