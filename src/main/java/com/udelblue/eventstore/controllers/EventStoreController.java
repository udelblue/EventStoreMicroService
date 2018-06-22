package com.udelblue.eventstore.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.udelblue.eventstore.domain.EventStore;
import com.udelblue.eventstore.domain.EventStoreModel;
import com.udelblue.eventstore.services.EvertStoreService;

@CrossOrigin(origins = "*")
@RestController
public class EventStoreController {

	@Autowired
	EvertStoreService evertStoreService;

	@RequestMapping(value = { "/api/eventstore/aggregate/{aggregateId}" }, method = RequestMethod.GET)
	public List<EventStore> getEventStoreByID(@PathVariable(value = "aggregateId") UUID aggregateId) {
		return null;
	}

	@RequestMapping(value = { "/api/eventstore/aggregate/{aggregateId}" }, method = RequestMethod.POST)
	public EventStore postEventStoreByID(@PathVariable(value = "aggregateId") UUID aggregateId,
			@RequestBody EventStoreModel eventStoreModel,
			@RequestParam(name = "version", required = false) Integer version) {
		UUID id = UUID.randomUUID();
		if (version == null) {
			version = 1;
		}

		EventStore es = new EventStore(id, aggregateId, eventStoreModel.getAggregateType(),
				eventStoreModel.getPayLoad(), version);
		return evertStoreService.createEventStore(es);

	}

}
