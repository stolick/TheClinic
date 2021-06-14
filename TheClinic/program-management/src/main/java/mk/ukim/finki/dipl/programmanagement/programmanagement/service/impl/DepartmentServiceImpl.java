package mk.ukim.finki.dipl.programmanagement.programmanagement.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.dipl.programmanagement.programmanagement.domain.exceptions.DepartmentNotFoundException;
import mk.ukim.finki.dipl.programmanagement.programmanagement.domain.models.Department;
import mk.ukim.finki.dipl.programmanagement.programmanagement.domain.models.DepartmentId;
import mk.ukim.finki.dipl.programmanagement.programmanagement.domain.models.Program;
import mk.ukim.finki.dipl.programmanagement.programmanagement.domain.models.Room;
import mk.ukim.finki.dipl.programmanagement.programmanagement.domain.repository.DepartmentRepository;
import mk.ukim.finki.dipl.programmanagement.programmanagement.domain.valueobjects.ProgramDuration;
import mk.ukim.finki.dipl.programmanagement.programmanagement.service.DepartmentService;
import mk.ukim.finki.dipl.programmanagement.programmanagement.service.form.DepartmentForm;
import mk.ukim.finki.dipl.programmanagement.programmanagement.service.form.ProgramForm;
import mk.ukim.finki.dipl.programmanagement.programmanagement.service.form.RoomForm;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

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
    public Department addProgramToDepartment(DepartmentId id, ProgramForm programForm, List<RoomForm> roomsList) {
        Department department = departmentRepository.findById(id).orElseThrow(DepartmentNotFoundException::new);
        ProgramDuration programDuration = new ProgramDuration(Duration.ofSeconds(programForm.getSeconds(), programForm.getNanos()));
        Program program = Program.build(programForm.getProgramName(), programForm.getProgramDescription(), programDuration);
        List<Room> rooms = roomsList.stream().map(roomForm -> Room.build(roomForm.getRoomNumber(), roomForm.getFloorNumber())).collect(Collectors.toList());
        department.addProgram(program, rooms);
        departmentRepository.save(department);
        return department;
    }
}
