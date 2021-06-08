package mk.ukim.finki.dipl.appointmentbooking.appointmentbooking.domain.models;

import lombok.NonNull;
import mk.finki.ukim.dipl.sharedkernel.sharedkernel.domain.base.DomainObjectId;

public class SlotId extends DomainObjectId {

    private SlotId() {
        super(SlotId.randomId(SlotId.class).getId());
    }

    public SlotId(@NonNull String uuid) {
        super(uuid);
    }

    public static SlotId of(String uuid) {
        SlotId slotId = new SlotId(uuid);
        return slotId;
    }
}
