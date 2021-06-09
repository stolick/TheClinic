package mk.ukim.finki.dipl.usermanagement.usermanagement.service;

import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.Patient;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.User;

public interface PatientService {
    Patient createPatient(User user);
}
