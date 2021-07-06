package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	//display list of Students
	@GetMapping("/")
	public String viewStudentPage(Model studentDetails) {
		studentDetails.addAttribute("listStudents",studentService.getAllStudents());
		return "studentdetails";
		
	}
	
	@GetMapping("/addNewStudentForm")
	public String addNewStudent(Model saveStudent) {
		Student student = new Student();
		saveStudent.addAttribute("student", student);
		return "addStudent";
	}
	
	@RequestMapping(value = "/saveStudent", method=RequestMethod.POST)
	public String SaveStudent(@ModelAttribute("student") Student student) {
		//save Student to database
		studentService.saveStudent(student);
		return "redirect:/";
		
	}
	
	@RequestMapping(value = "/updateStudent/{sid}", method=RequestMethod.GET)
	public String UpdateStudent(@PathVariable(value ="sid") long sid, Model updateStudent) {
		// get Student details using Student id from service
		Student student = studentService.getStudentByID(sid);
		
		//set student as model attribute to pre-populate the form
		updateStudent.addAttribute("student", student);
		return "updateStudent";
		
	}
	
	@GetMapping("/deleteStudent/{sid}")
	public String DeleteStudent(@PathVariable(value ="sid") long sid) {
		//delete student method call
		this.studentService.deleteStduentByID(sid);
		return "redirect:/";
	}
}
