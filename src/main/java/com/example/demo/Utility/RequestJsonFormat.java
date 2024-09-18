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
	 */
	public static String employeeJsonFormat(String name, String homeTown, String joiningMonth) {

		String employeeJson = null;

		employeeJson = "{ \"body\": \"{\\\"name\\\":\\\"" + name + "\\\",\\\"hometown\\\":\\\""
				+ homeTown + "\\\",\\\"joining_month\\\":\\\"" + joiningMonth
				+ "\\\"}\" }";

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
		String clockJson = null;
		ObjectMapper mapper =  new ObjectMapper();
	    ObjectNode innerBody = mapper.createObjectNode();
	    ObjectNode json = mapper.createObjectNode();
		//休憩開始が押下されたらclock_inに現在時刻を格納
		if ("出勤".equals(clickButton)) {
			innerBody.put("\\employee_id", employeeId);
	        innerBody.put("clock_in", currentDateTime);
	        innerBody.put("break_start","");
	        innerBody.put("break_end", "");
	        innerBody.put("clock_out", "");
			json.set("body", innerBody);
			clockJson = mapper.writeValueAsString(json);
			System.out.println(clockJson);
			//休憩開始が押下されたらbreak_startに現在時刻を格納
		} else if ("休憩開始".equals(clickButton)) {
			

			//休憩終了が押下されたらbreak_endに現在時刻を格納
		} else if ("休憩終了".equals(clickButton)) {
			
			//退勤が押下されたらclock_outに現在時刻を格納
		} else if ("退勤".equals(clickButton)) {
			

		}
		return clockJson;
	}

}
