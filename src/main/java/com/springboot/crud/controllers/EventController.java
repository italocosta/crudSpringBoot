package com.springboot.crud.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.crud.model.Event;
import com.springboot.crud.repository.EventRepository;

@Controller
public class EventController {
	
	@Autowired
	private EventRepository eventRepository;
	
	@RequestMapping(value = "/newEvent", method=RequestMethod.GET)
	public String form() {
		return "event/formEvent";
	}
	
	@RequestMapping(value = "/newEvent", method=RequestMethod.POST)
	public String form(Event event) {
		eventRepository.save(event);
		return "redirect:/events";
	}
	
	@RequestMapping("events")
	public ModelAndView listEvents() {
		ModelAndView modelAndView = new ModelAndView("index");
		Iterable<Event> events = eventRepository.findAll();
		modelAndView.addObject("events",events);
		
		return modelAndView;
	}
	
	@RequestMapping("event/{id}")
	public ModelAndView eventDetail(@PathVariable("id") Long id) {
		Optional<Event> event = eventRepository.findById(id);
		ModelAndView modelAndView = new ModelAndView("event/eventDetails");
		modelAndView.addObject("event", event.isPresent() ? event.get() : null);
		
		return modelAndView;
	}
	
}
