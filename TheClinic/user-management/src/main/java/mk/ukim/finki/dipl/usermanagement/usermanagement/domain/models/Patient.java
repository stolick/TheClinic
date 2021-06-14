package mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models;

import lombok.Getter;
import mk.finki.ukim.dipl.sharedkernel.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.enums.Gender;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.enums.Language;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.valueobjects.EMBG;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "patients")
@Getter
public class Patient extends AbstractEntity<PatientId> {

    private String name;

    private Gender gender;

    private EMBG embg;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    private Patient() {
        super(PatientId.randomId(PatientId.class));
    }

    public static Patient create(User user) {
        Patient patient = new Patient();
        patient.user = user;
        return patient;
    }

    public static Patient change(Patient patient, String name, Gender gender, EMBG embg) {
        patient.name = name;
        patient.gender = gender;
        patient.embg = embg;
        return patient;
    }
}
