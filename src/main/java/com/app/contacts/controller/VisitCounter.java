package com.app.contacts.controller;

import java.time.LocalDateTime;

import com.app.contacts.repository.Visit;
import com.app.contacts.repository.VisitsRepository;

public abstract class VisitCounter {
	private final VisitsRepository visitRepository;

	public VisitCounter(VisitsRepository contactsRepository) {
		this.visitRepository = contactsRepository;
	}

	void increment(){
		var visit = new Visit();
		visit.setDescription(String.format("Visited at %s", LocalDateTime.now()));
		visitRepository.save(visit);
	}
}
