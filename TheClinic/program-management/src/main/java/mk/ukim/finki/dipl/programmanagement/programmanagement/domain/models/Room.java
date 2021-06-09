package mk.ukim.finki.dipl.programmanagement.programmanagement.domain.models;

import lombok.Getter;
import mk.finki.ukim.dipl.sharedkernel.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "rooms")
@Getter
public class Room extends AbstractEntity<RoomId> {

    private int roomNumber;
    private int floor;

    private Room(){
        super(RoomId.randomId(RoomId.class));
    }
}
