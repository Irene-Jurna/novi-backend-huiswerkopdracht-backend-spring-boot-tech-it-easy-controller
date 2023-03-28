package nl.novi.techItEasy.repositories;

import nl.novi.techItEasy.models.Television;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TelevisionRepository extends CrudRepository<Television, Long> {
    List<Television> findAllTelevisionsByBrandEqualsIgnoreCase(String brand);
}
