package com.example.demo.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.data.EmployeeClockData;
import com.example.demo.data.EmployeeData;
import com.example.demo.service.ClockService;
import com.example.demo.service.EmployeeService;

@Controller
public class EmployeeController {

	private final EmployeeService employeeService;
	private final ClockService clockService;

	public EmployeeController(EmployeeService employeeService,ClockService clockService) {
		this.employeeService = employeeService;
		this.clockService = clockService;
	}

	//------------------------一覧表示----------------------------------------------------
	@GetMapping("employeeList")
	public String employeeList(Model model) throws IOException {

		List<EmployeeData> employeeList = employeeService.getEmployeeList();

		model.addAttribute("employeeList", employeeList);

		return "employee-list.html";

	}

	//------------------------社員登録関連処理--------------------------------------------
	//登録画面表示
	@GetMapping("employeeRegistPage")
	public String employeeRegistPage() {
		return "employee_regist.html";
	}

	//新入社員登録処理
	@PostMapping("Registration")
	public String employeeRegist(@RequestParam("name") String name, @RequestParam("home_town") String homeTown,
			@RequestParam("joining_month") String joiningMonth, Model model) throws IOException {

		employeeService.employeeRegist(name, homeTown, joiningMonth);

		return "employee_regist.html";
	}

	//------------------------勤怠関連処理------------------------------------------------
	//詳細画面表示
	@PostMapping("employeeDetailPage")
	public String employeeDetailPage(@RequestParam("employeeId") String employeeId, @RequestParam("name") String name,
			Model model) throws IOException {

		EmployeeClockData[] employeeClock = clockService.getClock(employeeId);

		model.addAttribute("employeeId", employeeId);
		model.addAttribute("name", name);
		model.addAttribute("employeeClock", employeeClock);

		return "employee-detail.html";
	}

	//勤怠登録処理
	@PostMapping("ClockRegist")
	public String employeeClockRegist(
			@RequestParam("name") String name,
			@RequestParam("employeeId") String employeeId,
			@RequestParam("click_button") String clickButton, Model model)
			throws IOException {
		//現在日時を取得
		Date nowDate = new Date();
		//右辺の形式にFormat
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//String型の変数に格納
		String currentDate = dateFormat.format(nowDate);

		//登録処理
		clockService.clockRegist(employeeId, clickButton, currentDate);

		//ボタンクリック後の再取得の為、勤怠の取得
		EmployeeClockData[] employeeClock = clockService.getClock(employeeId);

		//データの格納 nameは登録に使わない。idとemoloyeeクリックは再度登録処理に必要になるため利用。
		model.addAttribute("employeeId", employeeId);
		model.addAttribute("employeeClock", employeeClock);
		model.addAttribute("name", name);
		return "employee-detail.html";
	}

}
