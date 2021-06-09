package mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models;

import lombok.NonNull;
import mk.finki.ukim.dipl.sharedkernel.sharedkernel.domain.base.DomainObjectId;

public class PatientId extends DomainObjectId {

    private PatientId() {
        super(PatientId.randomId(PatientId.class).getId());
    }

    public PatientId(@NonNull String uuid) {
        super(uuid);
    }

    public static PatientId of(String uuid) {
        PatientId patientId = new PatientId(uuid);
        return patientId;
    }
}
