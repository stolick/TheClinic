package mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models;

import lombok.NonNull;
import mk.finki.ukim.dipl.sharedkernel.sharedkernel.domain.base.DomainObjectId;

public class DoctorId extends DomainObjectId {

    private DoctorId() {
        super(DoctorId.randomId(DoctorId.class).getId());
    }

    public DoctorId(@NonNull String uuid) {
        super(uuid);
    }

    public static DoctorId of(String uuid) {
        DoctorId doctorId = new DoctorId(uuid);
        return doctorId;
    }
}
