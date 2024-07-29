package com.example.demo.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class EmployeeData {
	public EmployeeData() {
		//引数ナシ処理なしコンストラクタ
	}
	
	public EmployeeData(String name, String homeTown, String joiningMonth) {
		this.name = name;
		this.homeTown = homeTown;
		this.joiningMonth = joiningMonth;
	}

	@JsonProperty("id")
	private int id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("hometown")
	private String homeTown;

	@JsonProperty("joining_month")
	private String joiningMonth;
	
	@JsonProperty("created_at")
	private String createdAt;
	
	@JsonProperty("updated_at")
	private String updatedAt;
}
