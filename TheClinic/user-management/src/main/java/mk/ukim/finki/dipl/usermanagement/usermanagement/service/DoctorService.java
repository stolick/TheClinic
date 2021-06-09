package mk.ukim.finki.dipl.usermanagement.usermanagement.service;

import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.Doctor;
import mk.ukim.finki.dipl.usermanagement.usermanagement.service.form.DoctorForm;

public interface DoctorService {
    public Doctor createDoctor(DoctorForm doctorForm);

}
