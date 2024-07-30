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
		//Dataのインスタンス生成・初期化
		EmployeeRegist registData = new EmployeeRegist(name, homeTown, joiningMonth);

		//リクエストパラメータを格納したDTOを渡す。
		employeeRepository.employeeRegist(registData);
	}

	/**
	 * 対象従業員の勤怠取得メソッド
	 * @param employeeId 従業員id
	 * @return 勤怠情報
	 * @throws IOException
	 */
	public EmployeeClock[] getClock(String employeeId) throws IOException {

		EmployeeClock employeeClock[] = employeeRepository.getClock(employeeId);

		return employeeClock;
	}

	/**
	 * 対象従業員の出退勤を登録するメソッド
	 * @param employeeId 従業員id
	 * @param clickButton 押下されたボタンの種類(出勤、休憩開始、休憩終了、退勤)
	 * @param currentDateTime 出退勤ボタンが押された時間
	 * @throws IOException
	 */
	public void clockRegist(String employeeId, String clickButton, String currentDateTime) throws IOException {
		
		//JSON形式へのFormatの為、変換クラスをnew
		DatailJsonFormat format = new DatailJsonFormat();
		//リクエストボディ用のJSON文字列の作成
		String requestBody = format.jsonFormat(employeeId, clickButton, currentDateTime);
		employeeRepository.clockRegist(requestBody);
	}
}
