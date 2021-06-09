package mk.ukim.finki.dipl.programmanagement.programmanagement.domain.models;

import lombok.Getter;
import lombok.NonNull;
import mk.finki.ukim.dipl.sharedkernel.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "departments")
@Getter
public class Department extends AbstractEntity<DepartmentId> {

    private String departmentName;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Program> programs = new HashSet<>();

    private Department(){
        super(DepartmentId.randomId(DepartmentId.class));
    }

    public static Department build(String departmentName){
        Department department = new Department();
        department.departmentName = departmentName;
        return department;
    }

    public Program addProgram(@NonNull Program program, @NonNull List<Room> rooms){
        Objects.requireNonNull(program, "Program must not be null");
        Objects.requireNonNull(rooms, "Rooms list must not be null");
        program.assignRooms(rooms);
        programs.add(program);
        return program;
    }

}
