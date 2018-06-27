package com.udelblue.eventstore.domain;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;

@Entity
public class EventStore {
	protected String aggregateId;
	protected String aggregateType;
	@Basic
	protected Timestamp createdAt;
	@Id
	protected String id;
	protected String payLoad;

	protected int version;

	public EventStore() {
		UUID uuid = UUID.randomUUID();
		this.id = uuid.toString();
		this.version = 1;
	}

	public EventStore(UUID id, UUID aggregateId, String aggregateType, String payLoad, int version) {
		this.id = id.toString();
		this.aggregateId = aggregateId.toString();
		this.aggregateType = aggregateType;
		this.payLoad = payLoad;
		this.version = version;
	}

	public UUID getAggregateId() {
		return UUID.fromString(aggregateId);
	}

	public String getAggregateType() {
		return aggregateType;
	}

	public String getCreatedAt() {
		if (createdAt != null) {
			return createdAt.toString();
		}

		return createdAt.toString();
	}

	public UUID getId() {
		return UUID.fromString(id);

	}

	public String getPayLoad() {
		return payLoad;
	}

	public int getVersion() {
		return version;
	}

	@PrePersist
	public void prePersist() {
		this.createdAt = Timestamp.from(Instant.now());
	}

	public void setVersion(int version) {
		this.version = version;
	}
}