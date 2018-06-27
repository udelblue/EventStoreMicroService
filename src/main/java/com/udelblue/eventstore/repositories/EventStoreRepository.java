package com.udelblue.eventstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udelblue.eventstore.domain.EventStore;

@Repository
public interface EventStoreRepository extends JpaRepository<EventStore, String> {
	List<EventStore> findAllByAggregateId(String aggregateId);
}
