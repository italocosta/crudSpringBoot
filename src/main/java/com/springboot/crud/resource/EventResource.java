package com.springboot.crud.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.crud.model.Event;
import com.springboot.crud.repository.EventRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="API REST Events")
@RestController
@RequestMapping("/event")
public class EventResource {
	
	@Autowired
	private EventRepository eventRepository;
	
	@ApiOperation(value="Get all events")
	@GetMapping(produces="application/json")
	public @ResponseBody Iterable<Event> listEvents() {
		Iterable<Event> events =  eventRepository.findAll();
		events.forEach(event -> event.add(linkTo(methodOn(EventResource.class).event(event.getIdEvent())).withSelfRel()));
		return events;
	}
	
	@ApiOperation(value="Get an event")
	@GetMapping(value = "/{idEvent}", produces="application/json")
	public @ResponseBody Event event(@PathVariable(value="idEvent") Long id) {
		Event event = eventRepository.findById(id).get();
		event.add(linkTo(methodOn(EventResource.class).listEvents()).withRel("List of Events"));
		return event;
	}
	
	@ApiOperation(value="Add an event")
	@PostMapping()
	public Event addEvent(@RequestBody @Valid Event event) {
		eventRepository.save(event);
		event.add(linkTo(methodOn(EventResource.class).event(event.getIdEvent())).withSelfRel());
		return event;
	}
	
	@ApiOperation(value="Delete an event")
	@DeleteMapping()
	public Event deleteEvent(@RequestBody Event event) {
		eventRepository.delete(event);
		return event;
	}
}
