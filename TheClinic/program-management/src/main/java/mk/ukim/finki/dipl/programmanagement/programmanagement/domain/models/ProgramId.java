package mk.ukim.finki.dipl.programmanagement.programmanagement.domain.models;

import lombok.NonNull;
import mk.finki.ukim.dipl.sharedkernel.sharedkernel.domain.base.DomainObjectId;

public class ProgramId extends DomainObjectId {

    private ProgramId() {
        super(ProgramId.randomId(ProgramId.class).getId());
    }

    public ProgramId(@NonNull String uuid) {
        super(uuid);
    }

    public static ProgramId of(String uuid) {
        ProgramId roomId = new ProgramId(uuid);
        return roomId;
    }

}
