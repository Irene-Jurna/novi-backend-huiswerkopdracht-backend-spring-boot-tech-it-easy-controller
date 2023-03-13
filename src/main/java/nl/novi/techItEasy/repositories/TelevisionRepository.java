package nl.novi.techItEasy.repositories;

import nl.novi.techItEasy.models.Television;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TelevisionRepository extends CrudRepository<Television, Long> {

}
