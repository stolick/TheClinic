package mk.ukim.finki.dipl.usermanagement.usermanagement.xport.dto.request;

import lombok.Data;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.enums.Gender;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.valueobjects.EMBG;

@Data
public class PatientRequest {
    private String name;

    private Gender gender;

    private EMBG embg;

}
