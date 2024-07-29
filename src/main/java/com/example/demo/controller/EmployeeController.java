package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.data.EmployeeData;
import com.example.demo.service.EmployeeService;

@Controller
public class EmployeeController {

	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	//------------------------登録蘭連処理--------------------------------------------
	@GetMapping("employeeRegistPage")
	public String employeeRegistPage() {
		return "employee_regist.html";
	}

	@PostMapping("Registration")
	public String employeeRegist(@RequestParam("name") String name, @RequestParam("home_town") String homeTown,
			@RequestParam("joining_month") String joiningMonth,Model model) throws IOException {
			
			employeeService.employeeRegist(name, homeTown, joiningMonth);
			List<EmployeeData> employeeList = employeeService.getEmployeeList();
			model.addAttribute("employeeList", employeeList);
			
		return "employee_regist.html";
	}
	
	//------------------------一覧表示--------------------------------------------
	@GetMapping("employeeList")
	public String employeeList(Model model) throws IOException {

		List<EmployeeData> employeeList = employeeService.getEmployeeList();

		model.addAttribute("employeeList", employeeList);

		return "employee-list.html";

	}

}
