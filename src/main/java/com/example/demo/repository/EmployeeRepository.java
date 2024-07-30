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

	//従業員一覧取得
	public EmployeeData[] getEmployeeList() throws IOException {

		String url = "https://wsaz0e6z45.execute-api.ap-northeast-1.amazonaws.com/prod/kintaikanri/employee";

		RestTemplate rest = new RestTemplate();

		ResponseEntity<String> response = rest.getForEntity(url, String.class);

		String json = response.getBody();

		ObjectMapper mapper = new ObjectMapper();

		EmployeeData[] employeeList = mapper.readValue(json, EmployeeData[].class);

		return employeeList;
	}

	//従業員登録
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

	//勤怠取得

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
