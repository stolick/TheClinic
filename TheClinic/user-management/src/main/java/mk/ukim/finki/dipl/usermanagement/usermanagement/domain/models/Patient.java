package mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models;

import lombok.Getter;
import mk.finki.ukim.dipl.sharedkernel.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.enums.Gender;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.valueobjects.EMBG;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "patients")
@Getter
public class Patient extends AbstractEntity<PatientId> {

    private String name;

    private Gender gender;

    private EMBG embg;

    private Patient(){
        super(PatientId.randomId(PatientId.class));
    }
}
