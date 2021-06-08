package mk.ukim.finki.dipl.programmanagement.programmanagement.domain.models;

import lombok.NonNull;
import mk.finki.ukim.dipl.sharedkernel.sharedkernel.domain.base.DomainObjectId;

public class RoomId extends DomainObjectId {

    private RoomId() {
        super(RoomId.randomId(RoomId.class).getId());
    }

    public RoomId(@NonNull String uuid) {
        super(uuid);
    }

    public static RoomId of(String uuid) {
        RoomId roomId = new RoomId(uuid);
        return roomId;
    }

}
