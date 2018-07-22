package com.app.contacts.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.contacts.repository.Visit;
import com.app.contacts.repository.VisitsRepository;

@RestController
@RequestMapping("/api")
public class VisitsController {

	private final VisitsRepository visitsRepository;

	public VisitsController(VisitsRepository visitsRepository) {
		this.visitsRepository = visitsRepository;
	}

	@GetMapping("/visits")
	public Iterable<Visit> getVisits() {
		return visitsRepository.findAll();
	}
}
