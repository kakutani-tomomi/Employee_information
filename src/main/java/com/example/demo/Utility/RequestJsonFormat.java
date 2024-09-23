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
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode innerBody = objectMapper.createObjectNode();
		ObjectNode outerBody = objectMapper.createObjectNode();
		//休憩開始が押下されたらclock_inに現在時刻を格納
		if ("出勤".equals(clickButton)) {
			
	        innerBody.put("name", "山田太郎");
	        innerBody.put("hometown", "福岡県");
	        innerBody.put("joining_month", "2023-07-07");
	        innerBody.put("joining_month", "2023-07-07");

	        // 内部JSONを文字列化
	        String innerBodyString = objectMapper.writeValueAsString(innerBody);

	        // 外側のJSONを作成
	       
	        outerBody.put("body", innerBodyString);

	        // 最終的なJSON文字列を生成
	        String finalJson = objectMapper.writeValueAsString(outerBody);

	        // 結果を出力
	        System.out.println(finalJson);
			//休憩開始が押下されたらbreak_startに現在時刻を格納
		} else if ("休憩開始".equals(clickButton)) {
			

			//休憩終了が押下されたらbreak_endに現在時刻を格納
		} else if ("休憩終了".equals(clickButton)) {
			
			//退勤が押下されたらclock_outに現在時刻を格納
		} else if ("退勤".equals(clickButton)) {
			

		}
		return null;
	}

}
