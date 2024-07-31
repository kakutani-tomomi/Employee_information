package com.example.demo.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Utility.RequestJsonFormat;
import com.example.demo.data.EmployeeData;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	/**
	 * 社員一覧のリスト取得 
	 * @return 社員一覧
	 * @throws IOException
	 */
	public List<EmployeeData> getEmployeeList() throws IOException {

		EmployeeData[] employeeList = employeeRepository.getEmployeeList();

		return Arrays.asList(employeeList);
	}

	/**
	 * 従業員を登録するメソッド
	 * @param name 氏名
	 * @param homeTown 出身地
	 * @param joiningMonth 入社月
	 * @throws IOException
	 */
	public void employeeRegist(String name, String homeTown, String joiningMonth) throws IOException {
		
		//Json形式のリクエストボディを作成
		String riquestBody = RequestJsonFormat.employeeJsonFormat(name, homeTown, joiningMonth);
		
		//リクエストパラメータを格納したDTOを渡す。
		employeeRepository.employeeRegist(riquestBody);
	}
}
