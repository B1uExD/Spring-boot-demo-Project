package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRespository;

@Service
public class StudentServeiceImpl implements StudentService{

	@Autowired
	private StudentRespository studentRepository;
	
	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return studentRepository.findAll();
	}

	@Override
	public void saveStudent(Student student) {
		// TODO Auto-generated method stub
		this.studentRepository.save(student);
		
	}

	@Override
	public Student getStudentByID(long sid) {
		// TODO Auto-generated method stub
		Optional<Student> optional = studentRepository.findById(sid);
		Student student = null;
		if(optional.isPresent()) {
			student = optional.get();
		}
		else{
			throw new RuntimeException("Studnet not Found for id = " + sid);
		}
		return student;
	}

	@Override
	public void deleteStduentByID(long sid) {
		// TODO Auto-generated method stub
		this.studentRepository.deleteById(sid);
		
	}
	

}
