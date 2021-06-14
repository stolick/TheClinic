package mk.ukim.finki.dipl.usermanagement.usermanagement.service;

import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.*;
import mk.ukim.finki.dipl.usermanagement.usermanagement.xport.dto.request.DoctorRequest;
import mk.ukim.finki.dipl.usermanagement.usermanagement.xport.dto.request.PatientRequest;

import java.util.List;

public interface PatientService {
    Patient createPatient(User user);

    List<Patient> getAll();

    Patient findById(PatientId patientId);

    Patient updatePatient(PatientId patientId, PatientRequest patientRequest);

}
