package com.app.contacts.controller;

import java.util.ArrayList;

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

		var contacts = new ArrayList<>();
		contactsRepository.findAll().forEach(contacts::add);

		var model = new ModelAndView("index");
		model.addObject("contacts", contacts);
		return model;
	}
}