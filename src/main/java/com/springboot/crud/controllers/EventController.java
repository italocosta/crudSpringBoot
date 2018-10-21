package com.springboot.crud.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.crud.model.Event;
import com.springboot.crud.model.Invited;
import com.springboot.crud.repository.EventRepository;
import com.springboot.crud.repository.InvitedRepository;

@Controller
public class EventController {
	
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private InvitedRepository invitedRepository;
	
	@RequestMapping(value = "/newEvent", method=RequestMethod.GET)
	public String form() {
		return "event/formEvent";
	}
	
	@RequestMapping(value = "/newEvent", method=RequestMethod.POST)
	public String form(@Valid Event event, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("msgError", "Fields invlaid");
			return "redirect:/newEvent";
		}
		eventRepository.save(event);
		redirectAttributes.addFlashAttribute("msgSuccess", "Event save success !");
		return "redirect:/events";
	}
	
	@RequestMapping("events")
	public ModelAndView listEvents() {
		ModelAndView modelAndView = new ModelAndView("index");
		Iterable<Event> events = eventRepository.findAll();
		modelAndView.addObject("events",events);
		
		return modelAndView;
	}
	
	@RequestMapping(value="event/{id}",method=RequestMethod.GET)
	public ModelAndView eventDetail(@PathVariable("id") Long id) {
		Optional<Event> event = eventRepository.findById(id);
		ModelAndView modelAndView = new ModelAndView("event/eventDetails");
		modelAndView.addObject("event", event.isPresent() ? event.get() : null);
		
		return modelAndView;
	}
	
	@RequestMapping(value="event/{id}",method=RequestMethod.POST)
	public String eventDetail(@PathVariable("id") Long id, @Valid Invited invited, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("msgError", "Fields invalid !");
			return "redirect:/event/{id}";
		}
		Optional<Event> event = eventRepository.findById(id);
		if(event.isPresent()) {
			invited.setEvent(event.get());
			invitedRepository.save(invited);
			event.get().getInviteds().add(invited);
			eventRepository.save(event.get());
		}
		redirectAttributes.addFlashAttribute("msgSuccess", "Invited add success !");
		return "redirect:/event/{id}";
	}
	
}
