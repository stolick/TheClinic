package mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models;

import lombok.Getter;
import mk.finki.ukim.dipl.sharedkernel.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.enums.Gender;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.enums.Language;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "doctors")
@Getter
public class Doctor extends AbstractEntity<DoctorId> {

    private String name;

    @ElementCollection
    private List<Language> languages;

    private Gender gender;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    private Doctor(){
        super(DoctorId.randomId(DoctorId.class));
    }

    public static Doctor create(User user){
        Doctor doctor = new Doctor();
        doctor.user = user;
        return doctor;
    }
}
