package com.app.contacts.controller;

import static java.lang.String.format;
import static java.time.LocalDateTime.now;

import com.app.contacts.repository.Visit;
import com.app.contacts.repository.VisitsRepository;

public abstract class VisitCounter {
	private final VisitsRepository visitRepository;

	public VisitCounter(VisitsRepository contactsRepository) {
		this.visitRepository = contactsRepository;
	}

	void increment(){
		visitRepository.save(new Visit(format("Visited at %s", now())));
	}
}
