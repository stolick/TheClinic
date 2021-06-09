package mk.ukim.finki.dipl.programmanagement.programmanagement.domain.repository;

import mk.ukim.finki.dipl.programmanagement.programmanagement.domain.models.Department;
import mk.ukim.finki.dipl.programmanagement.programmanagement.domain.models.DepartmentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, DepartmentId> {
}
