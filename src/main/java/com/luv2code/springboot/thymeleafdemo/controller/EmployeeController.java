package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService)
	{
		this.employeeService=employeeService;
	}

	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		List<Employee> theEmployees = employeeService.findAll();
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);

		return "employees/list-employees";
	}

	//show form
	@GetMapping("/showFormForAdd")
	public String showForm(Model model)
	{
		Employee theEmployee = new Employee();
		model.addAttribute("employee",theEmployee);
		return "employees/employee-form";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute("employee") Employee theEmployee)
	{
		employeeService.save(theEmployee);
		return "redirect:/employees/list";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int id, Model model)
	{
		Employee theEmployee = employeeService.findById(id);

		model.addAttribute("employee",theEmployee);

		return "employees/employee-form";
	}

	@GetMapping("/delete")
	public String deleteById(@RequestParam("employeeId") int id)
	{
		employeeService.deleteById(id);
		return "redirect:/employees/list";
	}
}









