package jp.co.brycen.common.dto.response;

public class AbstractResponse {

	/**
	 * エラーコード
	 */
	protected int errorCode = 0;

	/**
	 * エラーメッセージ
	 */
	protected String errorMessage = "";

	/**
	 * エラーコードを取得する。
	 *
	 * @return errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * エラーコードを設定する。
	 *
	 * @param errorCode セットする errorCode
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * エラーメッセージを取得する。
	 *
	 * @return errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * エラーメッセージを設定する。
	 *
	 * @param errorMessage セットする errorMessage
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(">" + this.getClass().getSimpleName());
		sb.append("\n>errorCode-" + errorCode);
		sb.append("\n>errorMessage-" + errorMessage);

		return sb.toString();
	}

}
