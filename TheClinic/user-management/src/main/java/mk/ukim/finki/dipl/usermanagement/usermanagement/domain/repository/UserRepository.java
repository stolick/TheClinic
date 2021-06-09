package mk.ukim.finki.dipl.usermanagement.usermanagement.domain.repository;

import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);
}

