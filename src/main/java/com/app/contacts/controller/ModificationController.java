package com.app.contacts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ModificationController {
	@GetMapping("/modification")
	public ModelAndView index(@RequestParam(name="operType") String operType,
							  @RequestParam(name="id") int id) {

		ModelAndView modelAndView = new ModelAndView("modification");
		if (operType != null)
			modelAndView.addObject("operName", operType);
			modelAndView.addObject("id", id);

		return modelAndView;
	}
}
