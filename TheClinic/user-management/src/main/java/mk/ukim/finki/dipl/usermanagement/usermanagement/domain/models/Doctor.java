package mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import mk.finki.ukim.dipl.sharedkernel.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.enums.Gender;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.enums.Language;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctors")
@Getter
public class Doctor extends AbstractEntity<DoctorId> {

    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Language> languages = new ArrayList<Language>();

    private Gender gender;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    private Doctor() {
        super(DoctorId.randomId(DoctorId.class));
    }

    public static Doctor create(User user) {
        Doctor doctor = new Doctor();
        doctor.user = user;
        return doctor;
    }

    public static Doctor change(Doctor doctor, String name, List<Language> languages, Gender gender) {
        doctor.name = name;
        doctor.languages = languages;
        doctor.gender = gender;
        return doctor;
    }
}
