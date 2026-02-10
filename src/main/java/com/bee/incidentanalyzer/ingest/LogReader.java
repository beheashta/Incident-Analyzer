package com.bee.incidentanalyzer.ingest;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import com.bee.incidentanalyzer.domain.LogEvent;

public interface LogReader {
    List<LogEvent> read(Path path) throws IOException;
}
