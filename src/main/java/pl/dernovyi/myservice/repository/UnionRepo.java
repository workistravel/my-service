package pl.dernovyi.myservice.repository;

import org.springframework.data.repository.CrudRepository;
import pl.dernovyi.myservice.models.dao.UnionDao;

public interface UnionRepo extends CrudRepository<UnionDao, Long> {
}
