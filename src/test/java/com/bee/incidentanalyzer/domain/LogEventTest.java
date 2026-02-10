package com.bee.incidentanalyzer.domain;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LogEventTest {

    @Test
    void constructor_sets_all_fields() {
        Instant now = Instant.now();

        LogEvent event = new LogEvent(
                now,
                "billing-api",
                "ERROR",
                "Database timeout"
        );

        assertEquals(now, event.getTimestamp());
        assertEquals("billing-api", event.getService());
        assertEquals("ERROR", event.getLevel());
        assertEquals("Database timeout", event.getMessage());
    }
}
