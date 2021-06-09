package mk.ukim.finki.dipl.usermanagement.usermanagement.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.exceptions.PatientNotFoundException;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.Patient;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.PatientId;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.User;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.repository.PatientRepository;
import mk.ukim.finki.dipl.usermanagement.usermanagement.service.PatientService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public Patient createPatient(User user) {
        Patient patient = Patient.create(user);
        patientRepository.save(patient);
        return patient;
    }

    @Override
    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient findById(PatientId patientId) {
        return patientRepository.findById(patientId).orElseThrow(PatientNotFoundException::new);
    }

}
