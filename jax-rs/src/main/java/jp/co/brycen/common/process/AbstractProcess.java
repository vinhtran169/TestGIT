package jp.co.brycen.common.process;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.MessagingException;

import jp.co.brycen.common.ILogSender;
import jp.co.brycen.common.database.DBAccessor;
import jp.co.brycen.common.dto.request.AbstractRequest;
import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.exception.DBException;

public class AbstractProcess {
	/**
	 * コンストラクタ
	 *
	 * @param logSender ログ出力用クラス
	 */
	public AbstractProcess(ILogSender logSender) {
		this.logSender = logSender;
	}

	/**
	 * ログ出力用
	 */
	protected ILogSender logSender = null;

	/**
	 * ログ出力用クラスを取得します。
	 *
	 * @return
	 */
	public ILogSender getLogSender() {
		return logSender;
	}


	/**
	 * 処理
	 *
	 * @param dba DBAccessor
	 * @param inParam リクエスト
	 * @return レスポンス
	 * @throws DBException
	 * @throws SQLException
	 * @throws MessagingException
	 * @throws IOException
	 * @throws Exception
	 */
	public AbstractResponse process(DBAccessor dba)
			throws DBException, SQLException, IOException, MessagingException, Exception {

		return null;
	}

	public AbstractResponse process(DBAccessor dba,AbstractRequest inParam)
			throws DBException, SQLException, IOException, MessagingException, Exception {

		return null;
	}

	/**
	 * ログを出力します。
	 *
	 * @param level レベル
	 * @param e エラーオブジェクト
	 */
	public void logSend(String level, Throwable e) {
		if (logSender != null) {
			logSender.logSend(level, e);
		}

	}

	/**
	 * ログを出力します。
	 *
	 * @param level レベル
	 * @param message メッセージ
	 */
	public void logSend(String level, String message) {
		if (logSender != null) {
			logSender.logSend(level, message);
		}
	}




}
