package com.example.demo.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class EmployeeRegist {
	public EmployeeRegist(){
		//引数ナシ処理なしコンストラクタ
	}
	
	public EmployeeRegist(String name, String homeTown, String joiningMonth) {
		this.name = name;
		this.homeTown = homeTown;
		this.joiningMonth = joiningMonth;
	}

	@JsonProperty("name")
	private String name;

	@JsonProperty("hometown")
	private String homeTown;

	@JsonProperty("joining_month")
	private String joiningMonth;
	
}
