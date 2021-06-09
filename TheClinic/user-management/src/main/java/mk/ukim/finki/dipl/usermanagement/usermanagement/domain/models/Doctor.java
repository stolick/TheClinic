package mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models;

import lombok.Getter;
import mk.finki.ukim.dipl.sharedkernel.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.enums.Gender;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.enums.Language;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "doctors")
@Getter
public class Doctor extends AbstractEntity<DoctorId> {

    private String name;

    private List<Language> languages;

    private Gender gender;

    private Doctor(){
        super(DoctorId.randomId(DoctorId.class));
    }

    public static Doctor create(String name, List<Language> languages, Gender gender){
        Doctor doctor = new Doctor();
        doctor.name = name;
        doctor.languages = languages;
        doctor.gender = gender;
        return doctor;
    }

}
