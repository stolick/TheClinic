package mk.ukim.finki.dipl.appointmentbooking.appointmentbooking.domain.models;

import lombok.NonNull;
import mk.finki.ukim.dipl.sharedkernel.sharedkernel.domain.base.DomainObjectId;

public class AppointmentId extends DomainObjectId {

    private AppointmentId() {
        super(AppointmentId.randomId(AppointmentId.class).getId());
    }

    public AppointmentId(@NonNull String uuid) {
        super(uuid);
    }

    public static AppointmentId of(String uuid) {
        AppointmentId appointmentId = new AppointmentId(uuid);
        return appointmentId;
    }

}
