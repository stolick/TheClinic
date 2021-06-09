package mk.ukim.finki.dipl.usermanagement.usermanagement.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.Patient;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.User;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.repository.PatientRepository;
import mk.ukim.finki.dipl.usermanagement.usermanagement.service.PatientService;
import mk.ukim.finki.dipl.usermanagement.usermanagement.service.form.PatientForm;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

}
