package com.springboot.crud.repository;

import org.springframework.data.repository.CrudRepository;

import com.springboot.crud.model.Event;

public interface EventRepository extends CrudRepository<Event, Long>{

}
