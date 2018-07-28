package com.app.contacts.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "contact")
public class Contact {

	public Contact() {
		setFirstName("First Name");
		setLastName("Last name");
		setAge(0);
		setPhone("0");
	}

	public Contact update(Contact source){
		this.firstName = source.firstName;
		this.lastName = source.lastName;
		this.age = source.age;
		this.phone = source.phone;
		return this;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq")
	@SequenceGenerator(
			name="seq",
			sequenceName="contact_sequence",
			allocationSize=1
	)
	private Long id;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "age")
	private int age;

	@Column(name = "phone")
	private String phone;

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getAge() {
		return age;
	}

	public String getPhone() {
		return phone;
	}

	public Contact setId(Long id) {
		this.id = id;
		return this;
	}

	public Contact setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public Contact setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public Contact setAge(int age) {
		this.age = age;
		return this;
	}

	public Contact setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	@Override
	public String toString() {
		return "Contact{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", age=" + age +
				", phone='" + phone + '\'' +
				'}';
	}
}
