package mk.ukim.finki.dipl.programmanagement.programmanagement.service.form;

import lombok.Data;

import java.util.List;

@Data
public class ProgramForm {
    private String programName;

    private String programDescription;

    private int seconds;

    private int nanos;

    List<RoomForm> roomFormList;
}
