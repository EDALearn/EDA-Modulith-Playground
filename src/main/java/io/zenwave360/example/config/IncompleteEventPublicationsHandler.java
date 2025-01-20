package io.zenwave360.example.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.IncompleteEventPublications;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class IncompleteEventPublicationsHandler {

    private final IncompleteEventPublications incompleteEventPublications;

    @Scheduled(fixedDelay = 1000)
    public void retryFailedEvents() {
        incompleteEventPublications.resubmitIncompletePublications((ep) -> {
            log.info("Retrying event publication [{}]: {}", ep.getIdentifier(), ep.getEvent());
            return true;
        });
    }
}
