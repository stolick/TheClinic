package mk.ukim.finki.dipl.programmanagement.programmanagement.domain.models;

import lombok.Getter;
import mk.finki.ukim.dipl.sharedkernel.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.dipl.programmanagement.programmanagement.domain.valueobjects.ProgramDuration;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "rooms")
@Getter
public class Room extends AbstractEntity<RoomId> {

    private int roomNumber;
    private int floorNumber;

    private Room() {
        super(RoomId.randomId(RoomId.class));
    }

    public static Room build(int roomNumber, int floorNumber) {
        Room room = new Room();
        room.roomNumber = roomNumber;
        room.floorNumber = floorNumber;
        return room;
    }
}
