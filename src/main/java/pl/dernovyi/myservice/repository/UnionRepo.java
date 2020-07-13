package pl.dernovyi.myservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.dernovyi.myservice.models.dao.UnionDao;
@Repository
public interface UnionRepo extends CrudRepository<UnionDao, Long> {
}
