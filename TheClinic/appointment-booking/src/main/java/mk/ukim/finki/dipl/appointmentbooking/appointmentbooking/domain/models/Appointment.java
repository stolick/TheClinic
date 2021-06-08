package mk.ukim.finki.dipl.appointmentbooking.appointmentbooking.domain.models;

import mk.finki.ukim.dipl.sharedkernel.sharedkernel.domain.base.AbstractEntity;

public class Appointment extends AbstractEntity<AppointmentId> {

    private Appointment(){
        super(AppointmentId.randomId(AppointmentId.class));
    }
}
