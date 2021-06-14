package mk.ukim.finki.dipl.programmanagement.programmanagement.domain.models;

import lombok.Getter;
import mk.finki.ukim.dipl.sharedkernel.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.dipl.programmanagement.programmanagement.domain.valueobjects.ProgramDuration;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "programs")
@Getter
public class Program extends AbstractEntity<ProgramId> {

    private String programName;

    private String programDescription;

    private ProgramDuration duration;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Room> rooms = new HashSet<>();

    private Program() {
        super(ProgramId.randomId(ProgramId.class));
    }

    public static Program build(String programName, String programDescription, ProgramDuration duration) {
        Program program = new Program();
        program.programName = programName;
        program.programDescription = programDescription;
        program.duration = duration;
        return program;
    }

    public List<Room> assignRooms(List<Room> roomsList) {
        Set<Room> roomsToAssign = new HashSet<>(roomsList);
        rooms = roomsToAssign;
        return roomsList;
    }
}
