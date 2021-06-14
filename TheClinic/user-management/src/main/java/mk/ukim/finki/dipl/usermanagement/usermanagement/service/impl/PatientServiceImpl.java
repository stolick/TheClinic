package mk.ukim.finki.dipl.usermanagement.usermanagement.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.exceptions.PatientNotFoundException;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.Patient;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.PatientId;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.User;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.repository.PatientRepository;
import mk.ukim.finki.dipl.usermanagement.usermanagement.service.PatientService;
import mk.ukim.finki.dipl.usermanagement.usermanagement.xport.dto.request.PatientRequest;
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

    @Override
    public Patient updatePatient(PatientId patientId, PatientRequest patientRequest) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(PatientNotFoundException::new);
        patient = Patient.change(patient, patientRequest.getName(), patientRequest.getGender(), patientRequest.getEmbg());
        patientRepository.save(patient);
        return patient;
    }

}
