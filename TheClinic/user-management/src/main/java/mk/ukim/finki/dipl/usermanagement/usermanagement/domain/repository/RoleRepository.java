package mk.ukim.finki.dipl.usermanagement.usermanagement.domain.repository;

import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.Role;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
