

package jp.co.brycen.common.exception;

/**
 * 同時接続数エラー例外クラス
 */
public class ConnectOverException extends Exception {

	protected static final long serialVersionUID = 3393202034645943580L;
	protected boolean bNothingCrtNo = false;
	protected boolean bNotFound = false;

	/**
	 * コンストラクタ
	 */
	public ConnectOverException() {
		super();
	}

	/**
	 * コンストラクタ
	 *
	 * @param e 例外
	 */
	public ConnectOverException(Exception e) {
		super(e);
	}

	/**
	 * コンストラクタ
	 *
	 * @param message 例外メッセージ
	 */
	public ConnectOverException(String message) {
		super(message);
	}

	/**
	 * コンストラクタ
	 *
	 * @param message 例外メッセージ
	 * @param message2 例外判定
	 */
	public ConnectOverException(String message, boolean message2) {
		super(message);

		bNothingCrtNo = message2;
	}

	/**
	 * エラーメッセージを取得します。
	 *
	 * @return
	 */
	public boolean getErrorMessage() {
		return bNothingCrtNo;
	}

	/**
	 * NotFoundを設定します。
	 *
	 * @param bValue フラグ
	 */
	public void setNotFound(boolean bValue) {
		bNotFound = bValue;
	}

	/**
	 * NotFoundを取得します。
	 *
	 * @return
	 */
	public boolean getNotFound() {
		return bNotFound;
	}
}
