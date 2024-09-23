package com.example.demo.Utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/*
 * APIに送信する値をJson形式に変換するクラス
 */

public class RequestJsonFormat {

	//インスタンス生成を防ぐため、privateコンストラクタ
	private RequestJsonFormat() {
		throw new IllegalStateException("Util instance construction is not allowed.");
	}

	/**
	 * 新規登録画面で入力された値に基づいてjson文字列を生成
	 * @param name 氏名
	 * @param homeTown 出身地
	 * @param joiningMonth 入社月
	 * @return　@return json形式の文字列。
	 * @throws JsonProcessingException 
	 */
	public static String employeeJsonFormat(String name, String homeTown, String joiningMonth) throws JsonProcessingException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode innerBody = objectMapper.createObjectNode();
		ObjectNode outerBody = objectMapper.createObjectNode();
		String employeeJson = null;
		
		innerBody.put("name", name);
		innerBody.put("hometown", homeTown);
		innerBody.put("joining_month", joiningMonth);

		String innerBodyString = objectMapper.writeValueAsString(innerBody);
		outerBody.put("body", innerBodyString);
		employeeJson = objectMapper.writeValueAsString(outerBody);
		System.out.println(employeeJson);

		return employeeJson;

	}

	/**
	 * 押下されたbuttonによって作成するJson文字列を変更。
	 * @param employeeId 
	 * @param clickButton
	 * @param currentDateTime
	 * @return json形式の文字列。
	 * @throws JsonProcessingException 
	 */
	public static String clockJsonFormat(String employeeId, String clickButton, String currentDateTime)
			throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode innerBody = objectMapper.createObjectNode();
		ObjectNode outerBody = objectMapper.createObjectNode();
		String clockJson = null;
		if ("出勤".equals(clickButton)) {

			innerBody.put("employee_id", employeeId);
			innerBody.put("clock_in", currentDateTime);
			innerBody.put("break_start", "");
			innerBody.put("break_end", "");
			innerBody.put("clock_out", "");

		} else if ("休憩開始".equals(clickButton)) {

			innerBody.put("employee_id", employeeId);
			innerBody.put("clock_in", "");
			innerBody.put("break_start", currentDateTime);
			innerBody.put("break_end", "");
			innerBody.put("clock_out", "");

			//休憩終了が押下されたらbreak_endに現在時刻を格納
		} else if ("休憩終了".equals(clickButton)) {

			innerBody.put("employee_id", employeeId);
			innerBody.put("clock_in", "");
			innerBody.put("break_start", "");
			innerBody.put("break_end", currentDateTime);
			innerBody.put("clock_out", "");

			//退勤が押下されたらclock_outに現在時刻を格納
		} else if ("退勤".equals(clickButton)) {
			
			innerBody.put("employee_id", employeeId);
			innerBody.put("clock_in", "");
			innerBody.put("break_start", "");
			innerBody.put("break_end", "");
			innerBody.put("clock_out", currentDateTime);
		}
		String innerBodyString = objectMapper.writeValueAsString(innerBody);
		outerBody.put("body", innerBodyString);
		clockJson = objectMapper.writeValueAsString(outerBody);
		System.out.println(clockJson);
		return clockJson;
	}

}
