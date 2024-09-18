package com.example.demo.repository;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.example.demo.data.EmployeeData;
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
		//json配列をEmployeeData型配列に格納
		EmployeeData[] employeeList = mapper.readValue(json, EmployeeData[].class);
		System.out.println(employeeList);

		return employeeList;
	}

	/**
	 * 入力された値をもとに従業員を登録するメソッド
	 * @param registData 登録する従業員情報
	 * @throws IOException
	 */
	public void employeeRegist(String requestBody) throws IOException {

		String url = "https://wsaz0e6z45.execute-api.ap-northeast-1.amazonaws.com/prod/kintaikanri/employee";
		
		//System.out.println(requestBody);テスト用

		//Url、リクエストヘッダー(JSON),Json形式の文字列の格納
		RequestEntity<String> request = RequestEntity
				.post(url)
				.contentType(MediaType.APPLICATION_JSON)
				.body(requestBody);
		RestTemplate rest = new RestTemplate();

		//リクエストの送信
		rest.exchange(request, String.class);

		/*
		 * テスト用
		 * ResponseEntity<String> response = rest.exchange(request, String.class);レスポンス文字列取得用
		 * System.out.println(response);//
		 * String json = response.getBody();
		 * System.out.println(json);
		 */
	}

}
