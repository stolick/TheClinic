package mk.ukim.finki.dipl.usermanagement.usermanagement.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.Doctor;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.User;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.repository.DoctorRepository;
import mk.ukim.finki.dipl.usermanagement.usermanagement.service.DoctorService;
import mk.ukim.finki.dipl.usermanagement.usermanagement.service.form.DoctorForm;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;

    @Override
    public Doctor createDoctor(User user) {
        Doctor doctor = Doctor.create(user);
        doctorRepository.save(doctor);
        return doctor;
    }
}
