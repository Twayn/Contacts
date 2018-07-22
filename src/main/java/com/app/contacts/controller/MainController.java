package com.app.contacts.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.app.contacts.repository.Contact;
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

		List<Contact> contacts = StreamSupport.stream(contactsRepository.findAll().spliterator(), false).collect(Collectors.toList());

		model.addObject("contacts", contacts);

		return model;
	}
}

