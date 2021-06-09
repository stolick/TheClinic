package mk.ukim.finki.dipl.programmanagement.programmanagement.xport.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.dipl.programmanagement.programmanagement.domain.models.Department;
import mk.ukim.finki.dipl.programmanagement.programmanagement.domain.models.DepartmentId;
import mk.ukim.finki.dipl.programmanagement.programmanagement.domain.models.Program;
import mk.ukim.finki.dipl.programmanagement.programmanagement.domain.models.Room;
import mk.ukim.finki.dipl.programmanagement.programmanagement.service.DepartmentService;
import mk.ukim.finki.dipl.programmanagement.programmanagement.service.form.DepartmentForm;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/department")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public List<Department> getAll(){
        return departmentService.getAll();
    }

    @GetMapping("/{departmentId}")
    public Department getDepartment(@PathVariable DepartmentId departmentId){
        return departmentService.findById(departmentId);
    }

    @PostMapping
    public Department createDepartment(@RequestBody DepartmentForm departmentForm){
        return departmentService.createDepartment(departmentForm);
    }

    //TODO request body
    @PostMapping("/{departmentId}/program")
    public Department addProgramToDepartment(@PathVariable DepartmentId departmentId, Program program, List<Room> roomsList){
        return departmentService.addProgramToDepartment(departmentId, program, roomsList);
    }
}
