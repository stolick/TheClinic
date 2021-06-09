package mk.ukim.finki.dipl.usermanagement.usermanagement.xport.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.Doctor;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.DoctorId;
import mk.ukim.finki.dipl.usermanagement.usermanagement.service.DoctorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/doctor")
@AllArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping
    public List<Doctor> getAll(){
        return doctorService.getAll();
    }

    @GetMapping("/{doctorId}")
    public Doctor getDoctor(@PathVariable DoctorId doctorId){
        return doctorService.findById(doctorId);
    }

}
