package com.udelblue.eventstore.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udelblue.eventstore.domain.EventStore;
import com.udelblue.eventstore.repositories.EventStoreRepository;

@Service
@Transactional
public class EventStoreServiceImpl implements EvertStoreService {
	private static final Logger LOG = LoggerFactory.getLogger(EventStoreServiceImpl.class);

	EventStoreRepository eventStoreRepository;

	/**
	 * @param eventStoreRepository
	 */
	@Autowired
	public EventStoreServiceImpl(EventStoreRepository eventStoreRepository) {
		super();
		this.eventStoreRepository = eventStoreRepository;
	}

	public EventStore createEventStore(EventStore eventStore) {
		return eventStoreRepository.save(eventStore);
	}

	@Override
	public List<EventStore> findAllEventStore(UUID aggregateId) {
		return eventStoreRepository.findAllByAggregateId(aggregateId.toString());
	}

	@Override
	public Optional<EventStore> findByID(UUID Id) {
		return eventStoreRepository.findById(Id.toString());
	}

}
