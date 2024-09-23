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
		//createObjectNodeメソッドの利用の為インスタンス生成
		ObjectMapper mapper = new ObjectMapper();
		//内側の空のjsonオブジェクトを作成
		ObjectNode innerBody = mapper.createObjectNode();
		//外側の空のjsonオブジェクトを作成
		ObjectNode body = mapper.createObjectNode();
		String employeeJson = "";
		
		//内側のjsonオブジェクトにデータ挿入
		innerBody.put("name", name);
		innerBody.put("hometown", homeTown);
		innerBody.put("joining_month", joiningMonth);

		//内側のjsonオブジェクトを文字列に変換し格納(エスケープ処理は勝手にされる) 
		String innerBodyString = mapper.writeValueAsString(innerBody);
		//全体のjsonオブジェクトにデータ挿入
		body.put("body", innerBodyString);
		//全体のjsonオブジェクトを文字列に変換(エスケープ処理は勝手にされる。)
		employeeJson = mapper.writeValueAsString(body);

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
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode innerBody = mapper.createObjectNode();
		ObjectNode body = mapper.createObjectNode();
		String clockJson = "";
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
		//内側を文字列に変換し格納
		String innerBodyString = mapper.writeValueAsString(innerBody);
		System.out.println(innerBodyString);
		//外側を作成
		body.put("body", innerBodyString);
		//外側を文字列に変換し格納
		clockJson = mapper.writeValueAsString(body);
		return clockJson;
	}

}
