package com.example.demo.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

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
		
		EmployeeData[] animalsList = employeeRepository.getAnimalList();

		return Arrays.asList(animalsList);
	}

	/**
	 * 登録処理
	 * @throws IOException 
	 */
	public void employeeRegist(String name, String homeTown, String joiningMonth) throws IOException {
		//Dataのインスタンス生成・初期化
		EmployeeRegist registData = new EmployeeRegist(name, homeTown, joiningMonth);

		//リクエストヘッダーの作成 json形式で送るため、ContentTypeをJSONに指定
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON); 
		//リクエストボディの作成 実際の値のDTOとリクエストヘッダーを格納
		HttpEntity<EmployeeRegist> entity = new HttpEntity<>(registData, headers);
		//操作
		employeeRepository.employeeRegist(entity);
	}
}
