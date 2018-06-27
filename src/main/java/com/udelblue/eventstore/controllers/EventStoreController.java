package com.udelblue.eventstore.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
public class EventStoreController {

	private static final Logger LOG = LoggerFactory.getLogger(EventStoreController.class);

	EvertStoreService evertStoreService;

	/**
	 * @param evertStoreService
	 */
	@Autowired
	public EventStoreController(EvertStoreService evertStoreService) {
		super();
		this.evertStoreService = evertStoreService;
	}

	// @ApiOperation(value = "Get Facility Type by ID", authorizations = {
	// @Authorization(value = "Authorization") })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "aggregateId", dataType = "UUID", paramType = "path", value = "ID of aggregate events."), })
	@ApiOperation(value = "Get event aggregation by ID")
	@RequestMapping(value = { "/api/eventstore/aggregate/{aggregateId}" }, method = RequestMethod.GET)
	public List<EventStore> getEventStoreByAggregateID(@PathVariable(value = "aggregateId") UUID aggregateId) {
		return evertStoreService.findAllEventStore(aggregateId);

	}

	@ApiImplicitParams({
			@ApiImplicitParam(name = "Id", dataType = "UUID", paramType = "path", value = "ID of Event."), })
	@ApiOperation(value = "Get event by ID")
	@RequestMapping(value = { "/api/eventstore/{Id}" }, method = RequestMethod.GET)
	public ResponseEntity<EventStore> getEventStoreByID(@PathVariable(value = "Id") UUID Id) {
		Optional<EventStore> op = evertStoreService.findByID(Id);
		if (op.isPresent()) {
			return new ResponseEntity<EventStore>(op.get(), HttpStatus.OK);
		} else {

			return new ResponseEntity<EventStore>(HttpStatus.NOT_FOUND);
		}

	}

	@ApiImplicitParams({
			@ApiImplicitParam(name = "aggregateId", dataType = "UUID", paramType = "path", value = "ID of aggregate."), })
	@ApiOperation(value = "Add new event aggregation")
	@RequestMapping(value = { "/api/eventstore/aggregate/{aggregateId}" }, method = RequestMethod.POST)
	public EventStore postEventStoreByAggregateID(@PathVariable(value = "aggregateId") UUID aggregateId,
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
