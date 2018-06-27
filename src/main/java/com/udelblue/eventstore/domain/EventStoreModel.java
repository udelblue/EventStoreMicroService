package com.udelblue.eventstore.domain;

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

	public String getPayLoad() {
		return payLoad;
	}

	public void setAggregateType(String aggregateType) {
		this.aggregateType = aggregateType;
	}

	public void setPayLoad(String payLoad) {
		this.payLoad = payLoad;
	}

}
