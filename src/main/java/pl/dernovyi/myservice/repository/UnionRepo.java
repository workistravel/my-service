package pl.dernovyi.myservice.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.dernovyi.myservice.models.dao.EmployeeDao;
import pl.dernovyi.myservice.models.dao.UnionDao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UnionRepo extends CrudRepository<UnionDao,Long> {


    @Override
    Optional<UnionDao> findById(Long id);
}
