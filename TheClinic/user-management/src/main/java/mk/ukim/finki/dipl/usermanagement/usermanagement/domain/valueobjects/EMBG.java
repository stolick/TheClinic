package mk.ukim.finki.dipl.usermanagement.usermanagement.domain.valueobjects;

import lombok.Getter;
import mk.finki.ukim.dipl.sharedkernel.sharedkernel.domain.base.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class EMBG implements ValueObject {
    private final int embgNumber;

    protected EMBG(){
        this.embgNumber = 0;
    }
}
