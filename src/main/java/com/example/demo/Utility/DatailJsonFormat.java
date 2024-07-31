package com.example.demo.Utility;

public class DatailJsonFormat {

	/**
	 * 押下されたbuttonによって作成するJson文字列を変更する。
	 * @param employeeId 
	 * @param clickButton
	 * @param currentDateTime
	 * @return json形式の配列。
	 */
	public static String clockJsonFormat(String employeeId, String clickButton, String currentDateTime) {
		String json = null;

		if ("出勤".equals(clickButton)) {
			System.out.println("出勤が押下されました。");

			json = "{ \"body\": \"{ \\\"employee_id\\\":\\\"" + employeeId + "\\\", \\\"clock_in\\\":\\\""
					+ currentDateTime
					+ "\\\", \\\"break_start\\\":\\\"\\\", \\\"break_end\\\":\\\"\\\", \\\"clock_out\\\":\\\"\\\" }\" }";

		} else if ("休憩開始".equals(clickButton)) {
			System.out.println("休憩開始が押下されました。");

			json = "{ \"body\": \"{ \\\"employee_id\\\":\\\"" + employeeId
					+ "\\\", \\\"clock_in\\\":\\\"\\\", \\\"break_start\\\":\\\""
					+ currentDateTime
					+ "\\\", \\\"break_end\\\":\\\"\\\", \\\"clock_out\\\":\\\"\\\" }\" }";

		} else if ("休憩終了".equals(clickButton)) {
			System.out.println("休憩終了が押下されました。");

			json = "{ \"body\": \"{ \\\"employee_id\\\":\\\"" + employeeId
					+ "\\\", \\\"clock_in\\\":\\\"\\\", \\\"break_start\\\":\\\"\\\", \\\"break_end\\\":\\\""
					+ currentDateTime + "\\\", \\\"clock_out\\\":\\\"\\\" }\" }";

		} else if ("退勤".equals(clickButton)) {
			System.out.println("退勤が押下されました。");

			json = "{ \"body\": \"{ \\\"employee_id\\\":\\\"" + employeeId
					+ "\\\", \\\"clock_in\\\":\\\"\\\", \\\"break_start\\\":\\\"\\\", \\\"break_end\\\":\\\"\\\", \\\"clock_out\\\":\\\""
					+ currentDateTime + "\\\" }\" }";
		}
		return json;
	}
}
