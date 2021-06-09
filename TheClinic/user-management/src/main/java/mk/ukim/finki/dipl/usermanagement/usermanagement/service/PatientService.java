package mk.ukim.finki.dipl.usermanagement.usermanagement.service;

import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.Patient;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.PatientId;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.User;

import java.util.List;

public interface PatientService {
    Patient createPatient(User user);
    List<Patient> getAll();
    Patient findById(PatientId patientId);
}
