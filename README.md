# Incident-Analyzer
> A Java-based toolkit for analyzing production logs and generating incident summaries, with planned support for ServiceNow incident enrichment.

## Status
Actively under development. This repository is intentionally grown incrementally to reflect realistic production tooling evolution.

## Why this exists
In production environments, incidents are rarely caused by a single error. Logs are noisy, fragmented, and often misleading under pressure.  
This project explores how a production support engineer reconstructs context during an outage by extracting signal from logs and summarizing failure patterns.

The goal is not to build a monitoring platform, but to model **incident investigation thinking** in a clear, testable, and extensible way.

## What this project does
At its core, the analyzer:
- Ingests structured log events from files
- Groups related errors into normalized signatures
- Identifies impacted services and time windows
- Produces a human-readable incident summary

Later iterations will optionally enrich reports with live ServiceNow incident data.

## How it works
High-level flow:
1. Log events are read from a structured source (JSON / JSONL)
2. Events are parsed into domain models
3. Error messages are normalized to reduce noise
4. Similar failures are grouped into signatures
5. A summary report is generated from the aggregated data

The design prioritizes clarity, testability, and separation of concerns so that new data sources (e.g. ServiceNow) can be added without changing core logic.

## Example
**Input (sample log event):**
```json
{
  "timestamp": "2026-02-09T12:01:03Z",
  "service": "billing-api",
  "level": "ERROR",
  "message": "Database timeout while fetching policy",
  "traceId": "abc123"
}
```
Output (summary excerpt):
```json
Incident Summary
----------------
Time window: 12:01:03 – 12:07:42
Total errors: 128

Top error signatures:
1. database timeout while fetching policy (42 occurrences)
2. connection refused to downstream service (31 occurrences)

Likely impacted services:
- billing-api
- policy-service
```
Project structure
The project is organized to keep core analysis logic independent from external integrations:

```json
com.bee.incidentanalyzer
├── cli          # Command-line entry point
├── domain       # Core domain models
├── ingest       # Log readers and parsers
├── analyze      # Grouping and summarization logic
├── report       # Report formatting
└── integrate
    └── servicenow   # ServiceNow integration (planned)
```
## Tech stack
Language: Java 17+
Build: Maven
Parsing: Jackson
Testing: JUnit 5

## Design decisions
Core analysis logic is kept free of external dependencies
Error grouping favors signal over perfect accuracy
Domain models are immutable where possible
Integrations (e.g. ServiceNow) are isolated behind interfaces
These decisions reflect real production constraints, where maintainability and debuggability matter more than clever abstractions.

## Failure modes & tradeoffs
Over-aggressive normalization may hide distinct root causes
Timestamp skew between services can distort timelines
The analyzer assumes structured logs; unstructured logs are only partially supported
In production systems, these issues are typically mitigated with distributed tracing and stronger schema enforcement.

## Roadmap
v0.1: File-based log ingestion and incident summary generation
v0.2: ServiceNow REST API integration for incident enrichment
v0.3: Timeline reconstruction and trace correlation
v0.4: Pluggable data sources and report formats

## What I’m learning
This project reinforces that effective incident response is as much about judgement and clarity as it is about tooling.
Designing for failure, observability, and calm investigation is a skill that transfers directly to real production systems.

