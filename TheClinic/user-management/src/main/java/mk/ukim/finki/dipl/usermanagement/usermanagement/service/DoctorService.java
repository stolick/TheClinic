package mk.ukim.finki.dipl.usermanagement.usermanagement.service;

import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.Doctor;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.DoctorId;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.User;
import mk.ukim.finki.dipl.usermanagement.usermanagement.xport.dto.request.DoctorRequest;

import java.util.List;

public interface DoctorService {
    List<Doctor> getAll();
    Doctor createDoctor(User user);

    Doctor findById(DoctorId doctorId);

    Doctor updateDoctor(DoctorId doctorId, DoctorRequest doctorRequest);
}
