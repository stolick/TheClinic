package mk.ukim.finki.dipl.programmanagement.programmanagement.xport.rest.dto;

import lombok.Data;
import mk.ukim.finki.dipl.programmanagement.programmanagement.service.form.ProgramForm;
import mk.ukim.finki.dipl.programmanagement.programmanagement.service.form.RoomForm;

import java.util.List;

@Data
public class ProgramRoomsRequest {
    ProgramForm program;
    List<RoomForm> rooms;
}
