package com.app.contacts.controller;

import static com.app.contacts.utils.OperType.ADD;
import static com.app.contacts.utils.OperType.DELETE;
import static com.app.contacts.utils.OperType.EDIT;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.contacts.repository.Contact;
import com.app.contacts.repository.ContactsRepository;

@Controller
public class ModificationController {

	private final ContactsRepository contactsRepository;

	public ModificationController(ContactsRepository contactsRepository) {
		this.contactsRepository = contactsRepository;
	}

	@GetMapping("/modification")
	public ModelAndView modification(@RequestParam(name="operType") String operType,
									 @RequestParam(name="id") long id,
									 HttpServletResponse httpServletResponse) throws IOException {

		Optional<Contact> contact;
		ModelAndView modelAndView = new ModelAndView("modification");
		modelAndView.addObject("operName", operType);
		modelAndView.addObject("id", id);

		if (operType.equals(ADD.getType())){
			modelAndView.addObject("contact", new Contact());
		} else if (operType.equals(EDIT.getType())){
			contact = contactsRepository.findById(id);
			contact.ifPresent(contact1 -> modelAndView.addObject("contact", contact1));
		} else if (operType.equals(DELETE.getType())){
			Optional<Contact> byId = contactsRepository.findById(id);
			byId.ifPresent(contactsRepository::delete);
			httpServletResponse.sendRedirect("/");
			return null;
		} else throw new RuntimeException("unexpected oper type: " + operType);

		return modelAndView;
	}

	@PostMapping("/modification")
	public void postModification(@ModelAttribute Contact contact,
								 @RequestParam(name="operType") String operType,
								 @RequestParam(name="id") long id,
								 HttpServletResponse httpServletResponse) throws IOException {

		contact.setId(0L);//TODO why?

		if(operType.equals(ADD.getType())){
			contactsRepository.save(contact);
		} else if (operType.equals(EDIT.getType())){
			Optional<Contact> byId = contactsRepository.findById(id);
			byId.ifPresent(contact1 -> {
				contact1.update(contact);
				contactsRepository.save(contact1);});
		}

		httpServletResponse.sendRedirect("/");
	}

}
