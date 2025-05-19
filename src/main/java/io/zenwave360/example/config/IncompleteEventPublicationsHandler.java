package io.zenwave360.example.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.IncompleteEventPublications;
import org.springframework.modulith.events.core.TargetEventPublication;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RequiredArgsConstructor
@Service
public class IncompleteEventPublicationsHandler {

    private final IncompleteEventPublications incompleteEventPublications;

    private final Map<UUID, AtomicInteger> failedEvents = new HashMap<>();

    @Scheduled(fixedDelay = 1000)
    // Use @SchedulerLock to prevent multiple instances from executing this method at the same time
    public void retryFailedEvents() {
        incompleteEventPublications.resubmitIncompletePublications((ep) -> {
            // Wait 5 seconds before retrying
            if (ep.getPublicationDate().plusSeconds(5).isAfter(java.time.Instant.now())) {
                return false;
            }
            int count = failedEvents.computeIfAbsent(ep.getIdentifier(), k -> new AtomicInteger(0)).incrementAndGet();
            if (count == 3) {
                log.error("Failed to publish event [{}] after {} attempts: {}", ep.getIdentifier(), count, ep.getEvent());
                return false;
            }
            else if (count > 3) {
                return false;
            }
            log.info("Retrying event publication [{}]: {}", ep.getIdentifier(), ((TargetEventPublication) ep).getTargetIdentifier());
            return true;
        });
    }
}
