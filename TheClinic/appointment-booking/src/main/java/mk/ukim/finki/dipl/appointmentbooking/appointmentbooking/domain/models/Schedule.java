package mk.ukim.finki.dipl.appointmentbooking.appointmentbooking.domain.models;

import mk.finki.ukim.dipl.sharedkernel.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.dipl.appointmentbooking.appointmentbooking.domain.valueobjects.DoctorId;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;

public class Schedule extends AbstractEntity<ScheduleId> {

    @AttributeOverride(name = "id", column = @Column(name = "doctor_id", nullable = false))
    private DoctorId doctorId;


    private Schedule(){
        super(ScheduleId.randomId(ScheduleId.class));
    }


}
