package com.example.demo.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Utility.DatailJsonFormat;
import com.example.demo.data.EmployeeClock;
import com.example.demo.data.EmployeeData;
import com.example.demo.data.EmployeeRegist;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	/*
	 * 社員一覧のリスト取得 
	 * @return 社員一覧
	 * @throws IOException
	 */
	public List<EmployeeData> getEmployeeList() throws IOException {

		EmployeeData[] employeeList = employeeRepository.getEmployeeList();

		return Arrays.asList(employeeList);
	}

	/**
	 * 登録処理
	 * @throws IOException 
	 */
	public void employeeRegist(String name, String homeTown, String joiningMonth) throws IOException {
		//Dataのインスタンス生成・初期化
		EmployeeRegist registData = new EmployeeRegist(name, homeTown, joiningMonth);

		//リクエストパラメータを格納したDTOを渡す。
		employeeRepository.employeeRegist(registData);
	}

	/**
	 * 勤怠取得
	 */
	public EmployeeClock[] getClock(String employeeId) throws IOException {

		EmployeeClock clock[] = employeeRepository.getClock(employeeId);

		return clock;
	}

	/**
	 * 勤怠登録
	 */
	public void clockRegist(String employeeId, String clickButton, String currentDateTime) throws IOException {
		
		DatailJsonFormat format = new DatailJsonFormat();
		//リポジトリ用のJSON文字列の作成
		String requestBody = format.jsonFormat(employeeId, clickButton, currentDateTime);
		employeeRepository.clockRegist(requestBody);
	}
}
