package mk.ukim.finki.dipl.programmanagement.programmanagement.domain.models;

import lombok.Getter;
import mk.finki.ukim.dipl.sharedkernel.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.dipl.programmanagement.programmanagement.domain.valueobjects.ProgramDuration;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "program")
@Getter
public class Program extends AbstractEntity<ProgramId> {

    private String programName;

    private ProgramDuration duration;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Room> roomList = new HashSet<>();

    //TODO In the same context, Program is aggregate root, check this
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    private Program(){
        super(ProgramId.randomId(ProgramId.class));
    }
}
