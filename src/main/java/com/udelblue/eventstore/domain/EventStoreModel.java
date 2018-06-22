package com.udelblue.eventstore.domain;

import java.util.UUID;

public class EventStoreModel {

	protected String aggregateType;
	protected String payLoad;

	public EventStoreModel() {
		super();
	}

	public EventStoreModel(String aggregateType, String payLoad) {
		super();

		this.aggregateType = aggregateType;
		this.payLoad = payLoad;
	}

	public String getAggregateType() {
		return aggregateType;
	}

	public void setAggregateType(String aggregateType) {
		this.aggregateType = aggregateType;
	}

	public String getPayLoad() {
		return payLoad;
	}

	public void setPayLoad(String payLoad) {
		this.payLoad = payLoad;
	}

}
