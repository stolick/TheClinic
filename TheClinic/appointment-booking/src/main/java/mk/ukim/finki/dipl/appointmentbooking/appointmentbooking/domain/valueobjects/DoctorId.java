package mk.ukim.finki.dipl.appointmentbooking.appointmentbooking.domain.valueobjects;

import mk.finki.ukim.dipl.sharedkernel.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class DoctorId extends DomainObjectId {
    private DoctorId() {
        super(DoctorId.randomId(DoctorId.class).getId());
    }

    public DoctorId(String uuid) {
        super(uuid);
    }

}
