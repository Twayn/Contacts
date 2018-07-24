package com.app.contacts.controller;

import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.app.contacts.repository.ContactsRepository;
import com.app.contacts.repository.VisitsRepository;

@Controller
public class MainController extends VisitCounter {

	private final ContactsRepository contactsRepository;

	public MainController(VisitsRepository visitsRepository, ContactsRepository contactsRepository) {
		super(visitsRepository);
		this.contactsRepository = contactsRepository;
	}

	@GetMapping("/")
	public ModelAndView index() {
		increment();

 		var model = new ModelAndView("index");

//		var contacts = new ArrayList<>();
//		contactsRepository.findAll().iterator().forEachRemaining(contacts::add);

		model.addObject("contacts",
						stream(contactsRepository.findAll().spliterator(), false).collect(toList()));

		return model;
	}
}

