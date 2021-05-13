package com.hogwarts.wizards;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Students {
	
	@GetMapping("/")
	public String pageInitial() {
		return "index";
	}

}
