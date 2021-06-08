package mk.ukim.finki.dipl.appointmentbooking.appointmentbooking.domain.models;

import lombok.NonNull;
import mk.finki.ukim.dipl.sharedkernel.sharedkernel.domain.base.DomainObjectId;

public class ScheduleId extends DomainObjectId {

    private ScheduleId() {
        super(ScheduleId.randomId(ScheduleId.class).getId());
    }

    public ScheduleId(@NonNull String uuid) {
        super(uuid);
    }

    public static ScheduleId of(String uuid) {
        ScheduleId scheduleId = new ScheduleId(uuid);
        return scheduleId;
    }
}
