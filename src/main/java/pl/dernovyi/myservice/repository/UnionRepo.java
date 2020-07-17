package pl.dernovyi.myservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.dernovyi.myservice.models.dao.UnionDao;

import java.util.Optional;

@Repository
public interface UnionRepo extends CrudRepository<UnionDao,Long> {


    @Override
    Optional<UnionDao> findById(Long id);
}
