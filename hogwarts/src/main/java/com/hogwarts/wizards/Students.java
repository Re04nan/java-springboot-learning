package com.hogwarts.wizards;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Students {
	
	private static final ArrayList<DataStudents> LIST_DATA_STUDENTS = new ArrayList<>();
	
	static {
		LIST_DATA_STUDENTS.add(new DataStudents("1", "Harry Potter", "+10101-10101"));
		LIST_DATA_STUDENTS.add(new DataStudents("2", "Hermione Granger", "+20202-20202"));
		LIST_DATA_STUDENTS.add(new DataStudents("3", "Ronald Weasley", "+30303-30303"));
		LIST_DATA_STUDENTS.add(new DataStudents("4", "Gina Weasley", "+40404-40404"));
	}
	
	@GetMapping("/")
	public String pageInitial() {
		return "index";
	}
	
	@GetMapping("/list")
	public ModelAndView pageListStudents() {
		ModelAndView modelAndView = new ModelAndView("list");
		
		modelAndView.addObject("DataStudents", LIST_DATA_STUDENTS);
		
		return modelAndView;
	}

}
