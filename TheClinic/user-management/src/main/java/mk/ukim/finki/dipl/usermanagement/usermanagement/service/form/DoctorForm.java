package mk.ukim.finki.dipl.usermanagement.usermanagement.service.form;

import lombok.Data;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.enums.Gender;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.enums.Language;

import java.util.List;

@Data
public class DoctorForm {
    private String name;

    private List<Language> languages;

    private Gender gender;
}
