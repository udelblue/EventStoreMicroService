package com.udelblue.eventstore.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.udelblue.eventstore.domain.EventStore;

public interface EvertStoreService {
	public EventStore createEventStore(EventStore eventStore);

	public List<EventStore> findAllEventStore(UUID aggregateId);

	public Optional<EventStore> findByID(UUID Id);
}
