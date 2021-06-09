package mk.ukim.finki.dipl.usermanagement.usermanagement.service;

import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.Doctor;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.User;

public interface DoctorService {
    Doctor createDoctor(User user);
}
