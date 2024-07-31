package com.example.demo.Utility;

/*
 * APIに送信する値をJson形式に変換するメソッド 
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
	 */
	public static String clockJsonFormat(String employeeId, String clickButton, String currentDateTime) {

		String clockJson = null;

		//休憩開始が押下されたらclock_inに現在時刻を格納
		if ("出勤".equals(clickButton)) {
			System.out.println("出勤が押下されました。");

			clockJson = "{ \"body\": \"{ \\\"employee_id\\\":\\\"" + employeeId + "\\\", \\\"clock_in\\\":\\\""
					+ currentDateTime
					+ "\\\", \\\"break_start\\\":\\\"\\\", \\\"break_end\\\":\\\"\\\", \\\"clock_out\\\":\\\"\\\" }\" }";

			//休憩開始が押下されたらbreak_startに現在時刻を格納
		} else if ("休憩開始".equals(clickButton)) {
			System.out.println("休憩開始が押下されました。");

			clockJson = "{ \"body\": \"{ \\\"employee_id\\\":\\\"" + employeeId
					+ "\\\", \\\"clock_in\\\":\\\"\\\", \\\"break_start\\\":\\\""
					+ currentDateTime
					+ "\\\", \\\"break_end\\\":\\\"\\\", \\\"clock_out\\\":\\\"\\\" }\" }";

			//休憩終了が押下されたらbreak_endに現在時刻を格納
		} else if ("休憩終了".equals(clickButton)) {
			System.out.println("休憩終了が押下されました。");

			clockJson = "{ \"body\": \"{ \\\"employee_id\\\":\\\"" + employeeId
					+ "\\\", \\\"clock_in\\\":\\\"\\\", \\\"break_start\\\":\\\"\\\", \\\"break_end\\\":\\\""
					+ currentDateTime + "\\\", \\\"clock_out\\\":\\\"\\\" }\" }";

			//退勤が押下されたらclock_outに現在時刻を格納
		} else if ("退勤".equals(clickButton)) {
			System.out.println("退勤が押下されました。");

			clockJson = "{ \"body\": \"{ \\\"employee_id\\\":\\\"" + employeeId
					+ "\\\", \\\"clock_in\\\":\\\"\\\", \\\"break_start\\\":\\\"\\\", \\\"break_end\\\":\\\"\\\", \\\"clock_out\\\":\\\""
					+ currentDateTime + "\\\" }\" }";
		}
		return clockJson;
	}

}
