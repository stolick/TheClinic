package mk.ukim.finki.dipl.usermanagement.usermanagement.xport.dto.request;

import lombok.Data;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.enums.Gender;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.valueobjects.EMBG;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PatientRequest {
    private String name;

    private Gender gender;

    private EMBG embg;

    private MultipartFile profilePicture;
}
