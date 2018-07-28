package com.app.contacts.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.contacts.repository.Contact;
import com.app.contacts.repository.ContactsRepository;

@RestController
@RequestMapping("/api")
public class EditContractsController {
	private final ContactsRepository contactsRepository;
	private final String successMsg = "success";

	public EditContractsController(ContactsRepository contactsRepository) {
		this.contactsRepository = contactsRepository;
	}

	@GetMapping("/addcontact")
	public String addContact(@RequestParam(name="first_name") String firstName,
							 @RequestParam(name="last_name") String lastName,
							 @RequestParam(name="age") int age,
							 @RequestParam(name="phone", required=false, defaultValue="") String phone
						     ) {
		var contact = new Contact()
		.setFirstName(firstName)
		.setLastName(lastName)
		.setAge(age)
		.setPhone(phone);

		contactsRepository.save(contact);
		return successMsg;
	}

	@GetMapping("/deletecontact")
	public String addContact(@RequestParam(name="id") long id) {
		contactsRepository.findById(id).ifPresent(contactsRepository::delete);
		return successMsg;
	}
}