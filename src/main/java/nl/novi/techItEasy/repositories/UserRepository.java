package nl.novi.techItEasy.repositories;

import nl.novi.techItEasy.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
