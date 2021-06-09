package mk.ukim.finki.dipl.programmanagement.programmanagement.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.dipl.programmanagement.programmanagement.domain.exceptions.DepartmentNotFoundException;
import mk.ukim.finki.dipl.programmanagement.programmanagement.domain.models.Department;
import mk.ukim.finki.dipl.programmanagement.programmanagement.domain.models.DepartmentId;
import mk.ukim.finki.dipl.programmanagement.programmanagement.domain.models.Program;
import mk.ukim.finki.dipl.programmanagement.programmanagement.domain.models.Room;
import mk.ukim.finki.dipl.programmanagement.programmanagement.domain.repository.DepartmentRepository;
import mk.ukim.finki.dipl.programmanagement.programmanagement.service.DepartmentService;
import mk.ukim.finki.dipl.programmanagement.programmanagement.service.form.DepartmentForm;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public Department findById(DepartmentId id) {
        return departmentRepository.findById(id).orElseThrow(DepartmentNotFoundException::new);
    }

    @Override
    public Department createDepartment(DepartmentForm form) {
        Department department = Department.build(form.getDepartmentName());
        departmentRepository.save(department);
        return department;
    }

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department addProgramToDepartment(DepartmentId id, Program program, List<Room> roomsList) {
        Department department = departmentRepository.findById(id).orElseThrow(DepartmentNotFoundException::new);
        department.addProgram(program, roomsList);
        departmentRepository.save(department);
        return department;
    }
}
