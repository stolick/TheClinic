package mk.finki.ukim.dipl.sharedkernel.sharedkernel.infra;

import mk.finki.ukim.dipl.sharedkernel.sharedkernel.domain.events.DomainEvent;

public interface DomainEventPublisher {
    void publish(DomainEvent event);
}

