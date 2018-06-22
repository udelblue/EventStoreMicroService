package com.udelblue.eventstore.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Entity
public class EventStore {
	@Id
	protected UUID id;
	protected UUID aggregateId;
	protected String aggregateType;
	protected String payLoad;
	protected int version;

	@Basic
	protected Timestamp createdAt;

	public EventStore() {
		this.id = UUID.randomUUID();
		this.version = 1;
	}

	public EventStore(UUID id, UUID aggregateId, String aggregateType, String payLoad, int version) {
		this.id = id;
		this.aggregateId = aggregateId;
		this.aggregateType = aggregateType;
		this.payLoad = payLoad;
		this.version = version;
	}

	public UUID getId() {
		return id;
	}

	public UUID getAggregateId() {
		return aggregateId;
	}

	public String getAggregateType() {
		return aggregateType;
	}

	public String getPayLoad() {
		return payLoad;
	}

	public String getCreatedAt() {
		if (createdAt != null) {
			return createdAt.toString();
		}

		return createdAt.toString();
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@PrePersist
	public void prePersist() {
		this.createdAt = Timestamp.from(Instant.now());
	}
}