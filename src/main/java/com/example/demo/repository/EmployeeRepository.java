package com.example.demo.repository;

import java.io.IOException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.example.demo.data.EmployeeData;
import com.example.demo.data.EmployeeRegist;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class EmployeeRepository {
	
	//一覧取得
	public EmployeeData[] getAnimalList() throws IOException {
		
		String url = "https://wsaz0e6z45.execute-api.ap-northeast-1.amazonaws.com/prod/kintaikanri/employee";

		RestTemplate rest = new RestTemplate();
		
		ResponseEntity<String> response = rest.getForEntity(url, String.class);
		
		String json = response.getBody();

		ObjectMapper mapper = new ObjectMapper();

		EmployeeData[] employeeList = mapper.readValue(json, EmployeeData[].class);

		return employeeList;
	}
	//登録
	public void employeeRegist(HttpEntity<EmployeeRegist> entity) throws IOException{
		
		String url = "https://wsaz0e6z45.execute-api.ap-northeast-1.amazonaws.com/prod/kintaikanri/employee";
		
		RestTemplate rest = new RestTemplate();
		
		System.out.println(entity);
		
		ResponseEntity<String> response = rest.exchange(url,HttpMethod.POST,entity,String.class);
		
		System.out.println(response);//テスト用
		
		String json = response.getBody();
		System.out.println(json);
	} 
}
