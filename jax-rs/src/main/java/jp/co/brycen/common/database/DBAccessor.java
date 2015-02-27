
package jp.co.brycen.common.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import jp.co.brycen.common.ILogSender;
import jp.co.brycen.common.exception.ConnectOverException;
import jp.co.brycen.common.exception.DBException;

/**
 * DB接続
 */
public final class DBAccessor {

	// コネクション
	protected Connection conn;
	// プロパティ
	private  Properties conf = null;

	/**
	 * コネクション取得
	 * @return
	 */
	public Connection getConnection() {
		return conn;
	}

	/**
	 * コンストラクタ
	 *
	 */
	public DBAccessor() {
		conn = null;

	}

	@Override
	protected void finalize() {
		try {
			disconnect();
		} catch (Exception e) {
		}
	}

	/**
	 * 接続します。
	 *
	 * @throws DBException
	 * @throws ConnectOverException
	 */
	public void connect() throws DBException, ConnectOverException {

		try {

			// JNDIを使用しないで接続
/*			String address = "jdbc:linter:linapid:192.9.200.27:1070:local";
			String user = "SYSTEM";
			String password = "MANAGER";

			Class.forName("com.relx.jdbc.LinterDriver").newInstance();

			conn = DriverManager.getConnection(address, user, password);
*/
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/LinterDB");

			conn = ds.getConnection();

		} catch (SQLException e) {
			if (e.getErrorCode() == 20) {
				throw new ConnectOverException(e);
			} else {
				throw new DBException(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 接続します。
	 *
	 * @throws DBException
	 * @throws ConnectOverException
	 */
	public void connect(String strTNS,String strUserNM,String strPas) throws DBException, ConnectOverException {

		try {

			conf = new Properties();
			//プロパティファイル取得
			conf.load(this.getClass().getResourceAsStream("/brycen.properties"));

			System.setProperty("oracle.net.tns_admin", conf.getProperty("TNSPATH"));

			/* ドライバクラスのロード */
			Class.forName ("oracle.jdbc.driver.OracleDriver");

			/* Connectionの作成 */
			conn = DriverManager.getConnection
			("jdbc:oracle:thin:@" + strTNS,strUserNM,strPas);

			conn.setAutoCommit(false);

		} catch (SQLException e) {
			if (e.getErrorCode() == 20) {
				throw new ConnectOverException(e);
			} else {
				throw new DBException(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 切断します。
	 *
	 * @throws DBException
	 */
	public void disconnect() throws DBException {

		try {
			if (conn != null) {
				if (conn.isClosed() == true) {
					return;
				}

				conn.close();
				conn = null;
			}
			return;
		} catch (Exception e) {
			throw new DBException(e);
		}

	}

	/**
	 * コミットします。
	 *
	 * @throws DBException
	 */
	public void commit() throws DBException {

		try {
			if (conn != null) {
				conn.commit();
			}
			return;
		} catch (Exception e) {
			throw new DBException(e);
		}

	}

	/**
	 * ロールバックします。
	 *
	 * @throws DBException
	 */
	public void rollback() throws DBException {

		try {
			if (conn != null) {
				conn.rollback();
			}
			return;
		} catch (Exception e) {
			throw new DBException(e);
		}

	}

	/**
	 * ＤＢステートメントを準備します。
	 *
	 * @param sql SQL
	 * @return  Dステートメント
	 * @throws DBException
	 */
	public DBStatement prepareStatement(String sql) throws DBException {
		return new DBStatement(this, sql);
	}

	/**
	 * ＤＢステートメントを準備します。
	 *
	 * @param sql SQL
	 * @return  DBステートメント
	 * @throws DBException
	 */
	public DBStatement prepareStatement(StringBuilder sql) throws DBException {
		return new DBStatement(this, sql.toString());
	}

	/**
	 * Callステートメントを準備します。
	 *
	 * @param sql SQL
	 * @return Callステートメント
	 * @throws DBException
	 */
	public DBCallableStatement prepareCall(String sql) throws DBException {
		return new DBCallableStatement(this, sql);
	}

	/**
	 * Callステートメントを準備します。
	 *
	 * @param sql SQL
	 * @return Callステートメント
	 * @throws DBException
	 */
	public DBCallableStatement prepareCall(StringBuilder sql) throws DBException {
		return new DBCallableStatement(this, sql.toString());
	}

	/**
	 * ログ出力クラス
	 */
	protected ILogSender logSender = null;

	/**
	 * ログ出力クラスを取得します。
	 *
	 * @return ログ出力クラス
	 */
	public ILogSender getLogSender() {
		return logSender;
	}

	/**
	 * ログ出力クラスを設定します。
	 *
	 * @param logSender ログ
	 */
	public void setLogSender(ILogSender logSender) {
		this.logSender = logSender;
	}

	/**
	 * ログを出力します。
	 *
	 * @param level レベル
	 * @param e 例外オブジェクト
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
