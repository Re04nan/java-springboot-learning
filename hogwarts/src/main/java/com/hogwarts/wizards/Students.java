package com.hogwarts.wizards;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("/list/new")
	public ModelAndView newStudent() {
		ModelAndView modelAndView = new ModelAndView("form");
		
		modelAndView.addObject("students", new DataStudents());
		
		return modelAndView;
	}
	
	@PostMapping("/list")
	public String register(DataStudents student) {
		String id = UUID.randomUUID().toString();
		
		student.setId(id);
		LIST_DATA_STUDENTS.add(student);
		
		return "redirect:/list";
	}
	
	@DeleteMapping("/list/{id}")
	public String removeStudent(@PathVariable String id) {
		DataStudents deleteStudent = searchStudent(id);
		
		LIST_DATA_STUDENTS.remove(deleteStudent);
		
		return "redirect:/list";
	}
	
	@PutMapping("/list/{id}")
	public String update(DataStudents student) {
		Integer indice = searchStudentIndice(student.getId());
		
		DataStudents studentOld = LIST_DATA_STUDENTS.get(indice);
				
		LIST_DATA_STUDENTS.remove(studentOld);
		
		LIST_DATA_STUDENTS.add(indice, student);
		
		return "redirect:/list";
	}
	
	@GetMapping("/list/{id}/edit")
	public ModelAndView edit(@PathVariable String id) {
		ModelAndView modelAndView = new ModelAndView("form");
		
		DataStudents student = searchStudent(id);
		
		modelAndView.addObject("students", student);
		
		return modelAndView;
	}
	
	private DataStudents searchStudent(String id) {
		Integer indice = searchStudentIndice(id);
		
		if(indice != null) {
			DataStudents student = LIST_DATA_STUDENTS.get(indice);
			return student;
		}
		return null;
	}
	
	private Integer searchStudentIndice(String id) {
		for (int i = 0; i < LIST_DATA_STUDENTS.size(); i++) {
			DataStudents student = LIST_DATA_STUDENTS.get(i);
			
			if(student.getId().equals(id)) {
				return i;
			}
		}
		return null;
	}
	

}
