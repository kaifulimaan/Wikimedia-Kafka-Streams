# Wikimedia–Kafka–Streams

🚀 **Project Overview**

This project implements a real-time data pipeline that captures live updates from **Wikipedia**—such as article edits, new page creations, and user activity—and processes them for downstream analytics and storage.

The system is designed to be **scalable, fault-tolerant, and event-driven**, making it a strong example of modern stream-processing architecture.

---

## 🎯 Key Objectives

* **⚡ Real-Time Ingestion**
  Connects to the public **Wikimedia EventStreams** (Server-Sent Events) API to continuously ingest live Wikipedia activity.

* **🔄 Stream Processing**
  Uses the **Kafka Streams API** to perform filtering, transformation, aggregation, and analysis in real time.

* **🧩 Data Enrichment**
  Converts raw, semi-structured JSON payloads into structured formats suitable for storage, querying, or analytics.

---

## 🛠 Technology Stack

The project is built on a modern distributed systems stack:

* **Apache Kafka**
  Acts as the message broker, enabling high-throughput, durable, and fault-tolerant event streaming.

* **Kafka Streams**
  A Java-based stream-processing library used to build scalable and stateful processing topologies.

* **Java / Spring Boot**
  Used to develop and manage microservices with clean configuration and lifecycle management.

* **JSON**
  The primary data format for Wikimedia event payloads.

---

## 🏗 System Architecture

The application follows a **multi-component, event-driven architecture**:

### 1️⃣ Wikimedia Producer

* Uses an **SSE (Server-Sent Events) client** to subscribe to Wikimedia’s `recentchange` stream.
* Continuously listens for real-time events such as edits, page creations, and deletions.
* Publishes incoming events to a Kafka topic (e.g., `wikimedia-events`).

```
Wikimedia EventStreams  →  Kafka Producer  →  Kafka Topic
```

---

### 2️⃣ Kafka Streams Processor

* Consumes events from the input Kafka topic.
* Applies a **processing topology** consisting of:

  * Filtering (e.g., removing bot edits)
  * Mapping and transformation
  * Aggregation (e.g., edits per user or domain)
* Maintains local state stores for stateful operations.

```
Kafka Topic  →  Kafka Streams Topology  →  Processed Stream
```

---

### 3️⃣ Kafka Consumer / Sink

* Consumes the processed output.
* Writes results to:

  * A database (e.g., **MySQL**, **MongoDB**), or
  * A downstream Kafka topic for visualization or further processing.

```
Processed Topic  →  Database / Analytics / Dashboard
```

---

## 📝 Implementation Details

### 🔀 Parallelism

* Kafka Streams scales horizontally by dividing processing into **tasks**.
* Tasks are distributed based on Kafka **topic partitions**, enabling parallel execution.

### 🛡 Fault Tolerance

* Local state stores are backed up using **Kafka changelog topics**.
* In case of failure, the application can **rebuild state automatically** from Kafka.

### 🔐 Serialization

* Uses **custom SerDes (Serializer / Deserializer)** to:

  * Convert raw JSON bytes into Java objects
  * Enable type-safe and efficient stream processing

---


