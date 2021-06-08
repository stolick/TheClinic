package mk.ukim.finki.dipl.appointmentbooking.appointmentbooking.domain.models;

import mk.finki.ukim.dipl.sharedkernel.sharedkernel.domain.base.AbstractEntity;

public class Slot extends AbstractEntity<SlotId> {
    private Slot(){
        super(SlotId.randomId(SlotId.class));
    }
}
