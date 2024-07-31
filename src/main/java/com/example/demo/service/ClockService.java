package com.example.demo.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.example.demo.Utility.RequestJsonFormat;
import com.example.demo.data.EmployeeClock;
import com.example.demo.repository.ClockRepository;

@Service
public class ClockService {

	private final ClockRepository clockRepository;

	public ClockService(ClockRepository clockRepository) {
		this.clockRepository = clockRepository;
	}

	/**
	 * 対象従業員の勤怠取得メソッド
	 * @param employeeId 従業員id
	 * @return 勤怠情報
	 * @throws IOException
	 */
	public EmployeeClock[] getClock(String employeeId) throws IOException {

		EmployeeClock employeeClock[] = clockRepository.getClock(employeeId);

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

		//リクエストボディ用のJSON文字列の作成
		String requestBody = RequestJsonFormat.clockJsonFormat(employeeId, clickButton, currentDateTime);
		clockRepository.clockRegist(requestBody);
	}
}
