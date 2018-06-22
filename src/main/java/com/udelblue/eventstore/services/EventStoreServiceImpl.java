package com.udelblue.eventstore.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udelblue.eventstore.domain.EventStore;
import com.udelblue.eventstore.repositories.EventStoreRepository;

@Service
@Transactional
public class EventStoreServiceImpl {
	private static final Logger LOG = LoggerFactory.getLogger(EventStoreServiceImpl.class);

	@Autowired
	EventStoreRepository eventStoreRepository;

	public EventStore createEventStore(EventStore eventStore) {
		return eventStoreRepository.save(eventStore);
	}

}
