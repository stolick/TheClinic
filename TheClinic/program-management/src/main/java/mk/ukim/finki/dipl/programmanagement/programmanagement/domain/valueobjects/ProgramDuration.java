package mk.ukim.finki.dipl.programmanagement.programmanagement.domain.valueobjects;

import lombok.Getter;
import mk.finki.ukim.dipl.sharedkernel.sharedkernel.domain.base.ValueObject;

import javax.persistence.Embeddable;
import java.time.Duration;

@Embeddable
@Getter
public class ProgramDuration implements ValueObject {

    private Duration duration;

    protected ProgramDuration() {
        this.duration = Duration.ZERO;
    }

    public ProgramDuration(Duration duration) {
        this.duration = duration;
    }
}
