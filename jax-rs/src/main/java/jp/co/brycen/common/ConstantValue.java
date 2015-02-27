package jp.co.brycen.common;


public class ConstantValue {

	/**
	 * インスタンス
	 */
	private static ConstantValue _instance = new ConstantValue();

	/**
	 * ログ出力レベル
	 */
	private String _LOG_OUTPUT_LEVEL = "FEWITD";

	/**
	 * ログ出力レベル
	 */
	public static String LOG_OUTPUT_LEVEL = _instance._LOG_OUTPUT_LEVEL;
}
