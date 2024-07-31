package com.example.demo.repository;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.example.demo.data.EmployeeClock;
import com.example.demo.data.EmployeeData;
import com.example.demo.data.EmployeeRegist;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class EmployeeRepository {

	/**
	 * 従業員一覧を取得するメソッド
	 * @return 従業員一覧
	 * @throws IOException
	 */
	public EmployeeData[] getEmployeeList() throws IOException {

		String url = "https://wsaz0e6z45.execute-api.ap-northeast-1.amazonaws.com/prod/kintaikanri/employee";

		RestTemplate rest = new RestTemplate();

		//レスポンスの取得
		ResponseEntity<String> response = rest.getForEntity(url, String.class);
		//レスポンスボディの取得
		String json = response.getBody();

		ObjectMapper mapper = new ObjectMapper();
		//json配列をDataがた配列に格納
		EmployeeData[] employeeList = mapper.readValue(json, EmployeeData[].class);

		return employeeList;
	}

	/**
	 * 入力された値をもとに従業員を登録するメソッド
	 * @param registData 登録する従業員情報
	 * @throws IOException
	 */
	public void employeeRegist(EmployeeRegist registData) throws IOException {

		String url = "https://wsaz0e6z45.execute-api.ap-northeast-1.amazonaws.com/prod/kintaikanri/employee";

		//json形式のリクエストボディを作成
		String requestBody = "{ \"body\": \"{\\\"name\\\":\\\"" + registData.getName() + "\\\",\\\"hometown\\\":\\\""
				+ registData.getHomeTown() + "\\\",\\\"joining_month\\\":\\\"" + registData.getJoiningMonth()
				+ "\\\"}\" }";
		//System.out.println(requestBody);テスト用

		//Url、リクエストヘッダー(JSON),Json形式の文字列の格納
		RequestEntity<String> request = RequestEntity
				.post(url)
				.contentType(MediaType.APPLICATION_JSON)
				.body(requestBody);
		RestTemplate rest = new RestTemplate();

		//リクエストの送信
		ResponseEntity<String> response = rest.exchange(request, String.class);

		//System.out.println(response);//テスト用

		String json = response.getBody();
		System.out.println(json);
	}

	//-------------------------------------------------詳細画面処理----------------------------------------------------

	/**
	 * idをもとに対象従業員の勤怠を返すメソッド
	 * @param employeeId 従業員id
	 * @return employeeClock 稼働時刻
	 * @throws IOException
	 */
	public EmployeeClock[] getClock(String employeeId) throws IOException {
		String url = "https://wsaz0e6z45.execute-api.ap-northeast-1.amazonaws.com/prod/kintaikanri/clock?employeeId="
				+ employeeId;

		//System.out.println(id);テスト用
		RestTemplate rest = new RestTemplate();
		//リクエストの送信、エンティティの取得
		ResponseEntity<String> response = rest.getForEntity(url, String.class);
		//文字列に格納
		String json = response.getBody();

		ObjectMapper mapper = new ObjectMapper();
		//jsonを配列に格納
		EmployeeClock[] employeeClock = mapper.readValue(json, EmployeeClock[].class);

		return employeeClock;
	}

	/**
	 * 出退勤ボタンが押されたときに、時刻を登録するメソッド
	 * @param requestBody 従業員idと稼働時刻をもとに作られたリクエストボディ
	 * @throws IOException
	 */
	public void clockRegist(String requestBody) throws IOException {

		String url = "https://wsaz0e6z45.execute-api.ap-northeast-1.amazonaws.com/prod/kintaikanri/clock";

		//json形式のリクエストボディを作成
		System.out.println(requestBody);//テスト用

		//Url、リクエストヘッダー(JSON),Json形式の文字列の格納
		RequestEntity<String> request = RequestEntity
				.post(url)
				.contentType(MediaType.APPLICATION_JSON)
				.body(requestBody);
		RestTemplate rest = new RestTemplate();

		//リクエストの送信
		ResponseEntity<String> response = rest.exchange(request, String.class);

		//System.out.println(response);//テスト用

		String json = response.getBody();
		System.out.println(json);
	}

}
