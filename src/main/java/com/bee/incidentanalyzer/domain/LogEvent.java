package com.bee.incidentanalyzer.domain;

import java.time.Instant;

public class LogEvent {

    private final Instant timestamp;
    private final String service;
    private final String level;
    private final String message;

    public LogEvent(Instant timestamp, String service, String level, String message) {
        this.timestamp = timestamp;
        this.service = service;
        this.level = level;
        this.message = message;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getService() {
        return service;
    }

    public String getLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }
}
