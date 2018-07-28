package com.app.contacts.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "visit")
public class Visit {

	public Visit(String description){
		this.description = description;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seqa")
	@SequenceGenerator(
			name="seqa",
			sequenceName="visit_sequence",
			allocationSize=1
	)
	private Long id;

	@Column(name = "description")
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
