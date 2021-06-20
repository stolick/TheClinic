package mk.ukim.finki.dipl.usermanagement.usermanagement.xport.dto.request;

import lombok.Data;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.enums.Gender;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.enums.Language;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class DoctorRequest {
    private String name;
    private List<Language> languages;
    private Gender gender;
    private MultipartFile profilePicture;
}
