package mk.ukim.finki.dipl.usermanagement.usermanagement.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.exceptions.DoctorNotFoundException;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.Doctor;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.DoctorId;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.User;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.repository.DoctorRepository;
import mk.ukim.finki.dipl.usermanagement.usermanagement.service.DoctorService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;

    @Override
    public List<Doctor> getAll() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor createDoctor(User user) {
        Doctor doctor = Doctor.create(user);
        doctorRepository.save(doctor);
        return doctor;
    }

    @Override
    public Doctor findById(DoctorId doctorId) {
        return doctorRepository.findById(doctorId).orElseThrow(DoctorNotFoundException::new);
    }
}
