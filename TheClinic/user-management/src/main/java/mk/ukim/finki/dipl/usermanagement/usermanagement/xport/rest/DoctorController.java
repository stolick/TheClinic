package mk.ukim.finki.dipl.usermanagement.usermanagement.xport.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.Doctor;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.DoctorId;
import mk.ukim.finki.dipl.usermanagement.usermanagement.service.DoctorService;
import mk.ukim.finki.dipl.usermanagement.usermanagement.xport.dto.request.DoctorRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/doctor")
@AllArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping
    public List<Doctor> getAll() {
        return doctorService.getAll();
    }

    @GetMapping("/{username}")
    public Doctor getDoctor(@PathVariable String username) {
        return doctorService.findByUser(username);
    }

    @PostMapping("/{doctorId}")
    public Doctor updateDoctor(@PathVariable DoctorId doctorId, @RequestBody DoctorRequest doctorRequest) {
        return doctorService.updateDoctorProfile(doctorId, doctorRequest);
    }


}
