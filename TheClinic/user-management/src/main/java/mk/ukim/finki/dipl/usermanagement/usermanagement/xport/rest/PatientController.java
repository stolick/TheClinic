package mk.ukim.finki.dipl.usermanagement.usermanagement.xport.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.Patient;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.PatientId;
import mk.ukim.finki.dipl.usermanagement.usermanagement.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/patient")
@AllArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public List<Patient> getAll(){
        return patientService.getAll();
    }

    @GetMapping("/{patientId}")
    public Patient getPatient(@PathVariable PatientId patientId){
        return patientService.findById(patientId);
    }
}
