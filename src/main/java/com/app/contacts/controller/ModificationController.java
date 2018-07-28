package com.app.contacts.controller;

import java.io.IOException;

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
	private final static String ADD = "add";
	private final static String EDIT = "edit";
	private final static String DELETE = "delete";

	public ModificationController(ContactsRepository contactsRepository) {
		this.contactsRepository = contactsRepository;
	}

	@GetMapping("/modification")
	public ModelAndView modification(@RequestParam(name="operType") String operType,
									 @RequestParam(name="id") long id,
									 HttpServletResponse httpServletResponse) throws IOException {

		ModelAndView modelAndView = new ModelAndView("modification");
		modelAndView.addObject("operName", operType);
		modelAndView.addObject("id", id);

		switch (operType) {
			case ADD:
				modelAndView.addObject("contact", new Contact());
				break;
			case EDIT:
				contactsRepository.findById(id).ifPresent(
						contact -> modelAndView.addObject("contact", contact));
				break;
			case DELETE:
				contactsRepository.findById(id).ifPresent(contactsRepository::delete);
				httpServletResponse.sendRedirect("/");
				return null;
			default:
				throw new RuntimeException("Unexpected oper type: " + operType);
		}

		return modelAndView;
	}

	@PostMapping("/modification")
	public void postModification(@ModelAttribute Contact contact,
								 @RequestParam(name="operType") String operType,
								 @RequestParam(name="id") long id,
								 HttpServletResponse httpServletResponse) throws IOException {
		switch (operType) {
			case ADD:
				contactsRepository.save(new Contact().update(contact));
				break;
			case EDIT:
				contactsRepository.findById(id).ifPresent(
						toEdit -> {
							toEdit.update(contact);
							contactsRepository.save(toEdit);
						});
				break;
			default:
				throw new RuntimeException("Unexpected oper type: " + operType);
		}

		httpServletResponse.sendRedirect("/");
	}

}
