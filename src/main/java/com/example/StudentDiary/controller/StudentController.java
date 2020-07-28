package com.example.StudentDiary.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.example.StudentDiary.dao.StudentRepository;
import com.example.StudentDiary.entity.Student;
import com.example.StudentDiary.service.StudentExcelExporter;
import com.example.StudentDiary.service.StudentPdfExporter;
import com.lowagie.text.DocumentException;

@Controller
@RequestMapping("/student")
public class StudentController {

	public StudentRepository studentRepository;
	
	public StudentController(StudentRepository theStudentRepository) {
		studentRepository = theStudentRepository;
	}
	
	@GetMapping("/list")
	public String list(Model theModel) {
		List<Student> theStudent = studentRepository.findAll();
		theModel.addAttribute("students", theStudent);
		return "/student/studentList";
	}
	
	@GetMapping("/add")
	public String addStudent(Model theModel) {
		Student theStudent = new Student();
		theModel.addAttribute("student", theStudent);
		return "/student/addForm";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String saveStudent(@Valid @ModelAttribute("student") Student theStudent, BindingResult result) {
		if(result.hasErrors()) {
			return "/student/addForm";
		}
		else {
			studentRepository.save(theStudent);
		}
		return "redirect:/student/list";
	}
	
	@GetMapping("/edit/{id}")
	public String editStudent(@PathVariable("id") int theId, Model theModel) {
		Optional<Student> result = studentRepository.findById(theId);
		Student theStudent = null;
		if(result.isPresent()) {
			theStudent = result.get();
			theModel.addAttribute("student", theStudent);
		}
		return "/student/update-student";
	}
	
	@PostMapping("/update/{id}")
	public String updateStudent(@Valid @ModelAttribute("student") Student theStudent, BindingResult result) {
		if(result.hasErrors()) {
			return "/student/update-student";
		}
		studentRepository.save(theStudent);
		return "redirect:/student/list";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteStudent(@PathVariable("id") int theId, Model theModel) {
		Optional<Student> result = studentRepository.findById(theId);
		if(result.isPresent()) {
			studentRepository.deleteById(theId);
		}
		return "redirect:/student/list";
	}
	
	@GetMapping("/pdf")
	public void exportToPdf(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		
		DateFormat theDateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = theDateFormatter.format(new Date());
		
		String headerKey = "Content-Desposition";
		String headerValue = "attachment; filename=students_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);
		
		List<Student> theStudent = studentRepository.findAll();
		
		StudentPdfExporter exporter = new StudentPdfExporter(theStudent);
		exporter.exportPdf(response);
	}
	
	@GetMapping("/csv")
	public void exportToCsv(HttpServletResponse response) throws IOException {
		response.setContentType("text/csv");
		
		DateFormat theDateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateTime = theDateFormatter.format(new Date());
		
		String fileName = "students" + currentDateTime + ".csv";
		String headerKey = "Content-Desposition";
		String headerValue = "attachment; filename=" + fileName;
		response.setHeader(headerKey, headerValue);
		
		List<Student> theStudent = studentRepository.findAll(Sort.by("lastName"));
		
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		
		String[] csvHeader = {"First Name", "Last Name", "Email", "ID"};
		String[] csvData = {"firstName", "lastName", "email", "roll"};
		
		csvWriter.writeHeader(csvHeader);
		
		for(Student student : theStudent) {
			csvWriter.write(student, csvData);
		}
		csvWriter.close();
	}
	
	/*
	 * @GetMapping("/xls") public void exportToExcel(HttpServletResponse response)
	 * throws IOException { response.setContentType("application/octet-stream");
	 * 
	 * DateFormat theDateFormatter = new SimpleDateFormat("yyyy-MM-dd"); String
	 * currentDateTime = theDateFormatter.format(new Date());
	 * 
	 * String fileName = "students" + currentDateTime + ".xlsx";
	 * 
	 * String headerKey = "Content-Desposition"; String headerValue =
	 * "attachment; filename=" + fileName; response.setHeader(headerKey,
	 * headerValue);
	 * 
	 * List<Student> theStudent = studentRepository.findAll(Sort.by("lastName"));
	 * 
	 * StudentExcelExporter excepExporter = new StudentExcelExporter(theStudent);
	 * excepExporter.export(response); }
	 */
	
}
