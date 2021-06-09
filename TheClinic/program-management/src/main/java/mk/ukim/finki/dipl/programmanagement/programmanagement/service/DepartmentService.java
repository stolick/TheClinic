package mk.ukim.finki.dipl.programmanagement.programmanagement.service;

import mk.ukim.finki.dipl.programmanagement.programmanagement.domain.models.Department;
import mk.ukim.finki.dipl.programmanagement.programmanagement.domain.models.DepartmentId;
import mk.ukim.finki.dipl.programmanagement.programmanagement.domain.models.Program;
import mk.ukim.finki.dipl.programmanagement.programmanagement.domain.models.Room;
import mk.ukim.finki.dipl.programmanagement.programmanagement.service.form.DepartmentForm;

import java.util.List;

public interface DepartmentService {
    Department findById(DepartmentId id);
    Department createDepartment(DepartmentForm form);
    List<Department> getAll();
    Department addProgramToDepartment(DepartmentId id, Program program, List<Room> roomsList);
}
