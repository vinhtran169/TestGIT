package jp.co.brycen.common.batch;

import jp.co.brycen.common.ConstantValue;
import jp.co.brycen.common.ILogSender;
import jp.co.brycen.common.database.DBAccessor;
import jp.co.brycen.common.dto.request.AbstractRequest;
import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.exception.ConnectOverException;
import jp.co.brycen.common.exception.DBException;
import jp.co.brycen.common.exception.FatalException;
import jp.co.brycen.common.process.AbstractProcess;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
	/**
	 * バッチ基本クラス
	 */
	public abstract class AbstractBatch implements ILogSender {



		/**
		 * ログ出力レベル
		 */
		protected String OUTPUT_LEVEL = ConstantValue.LOG_OUTPUT_LEVEL;

		/**
		 * ログ出力レベルを取得します。
		 *
		 * @return ログ出力レベル
		 */
		public String getOutputLevel() {
			return OUTPUT_LEVEL;
		}

		/**
		 * ログ出力レベルを設定します。
		 *
		 * @param outputLevel ログ出力レベル
		 */
		public void setOutputLevel(String outputLevel) {
			OUTPUT_LEVEL = outputLevel;
		}

		/**
		 * ログ出力用
		 */
		protected Log log = LogFactory.getLog(this.getClass());

		/**
		 * バッチの処理を実行します。
		 *
		 * @param inParam リクエスト
		 * @return レスポンス
		 */
		public final AbstractResponse execute(AbstractRequest inParam) {

			AbstractResponse outParam = null;
			DBAccessor dbaMD = null;

			// 開始ログ
			logSend("I", "処理開始");
			logSend("D", "引数：\n" + inParam.toString());

			try {
				// データベースへの接続 開始
				// トランザクションの開始
				dbaMD = new DBAccessor();
				dbaMD.setLogSender(this);
				dbaMD.connect();

				// 機能個別の処理
				//if(inParam.IdList == null){
				//	outParam = getProcess().process(dbaMD);
				//}
				//else{
					outParam = getProcess().process(dbaMD, inParam);
				//}
				if (outParam == null) {
					throw new FatalException("戻り値が設定されていません。");
				}

				// コミット
				dbaMD.commit();

				logSend("D", "コミット");

			} catch (ConnectOverException e) {

				logSend("E", "同時接続数を超えました。");

				// BCC:接続数制限オーバー
				//outParam.setErrorCode(-2);
				//outParam.setErrorMessage("同時接続数を超えました。");

			} catch (Exception e) {

				logSend("F", e);

				// ロールバック
				try {
					logSend("D", "ロールバック");
					if (dbaMD != null) {
						dbaMD.rollback();
					}

				} catch (DBException de) {
				}

				// BCC:例外発生
				if (outParam != null) {
					outParam.setErrorCode(-3);
					outParam.setErrorMessage("例外が発生しました。");
				}

			} finally {

				// データベースへの接続 終了
				try {
					if (dbaMD != null) {
						dbaMD.disconnect();
					}

				} catch (DBException de) {
					// BCC:データベースへの接続終了例外
				}

				logSend("D", "戻値：\n" + outParam.toString());
				// 終了ログ
				logSend("I", "処理終了");

			}

			return outParam;

		}

		/**
		 * 処理を取得します。<BR/>
		 * 必ずオーバーライドしてください。
		 *
		 * @return 処理
		 */
		protected abstract AbstractProcess getProcess();

		/**
		 * ログを出力します。
		 *
		 * @param level レベル
		 * @param e 例外オブジェクト
		 * @return
		 */
		public void logSend(String level, Throwable e) {

			if (OUTPUT_LEVEL.indexOf(level) == -1) {
				return;
			}

			if ("T".equals(level)) {
				log.debug("例外発生", e);
			} else if ("D".equals(level)) {
				log.debug("例外発生", e);
			} else if ("I".equals(level)) {
				log.info("例外発生", e);
			} else if ("W".equals(level)) {
				log.warn("例外発生", e);
			} else if ("E".equals(level)) {
				log.error("例外発生", e);
			} else if ("F".equals(level)) {
				log.fatal("例外発生", e);
			}

		}

		/**
		 * ログを出力します。
		 *
		 * @param level レベル
		 * @param message メッセージ
		 * @return
		 */
		public void logSend(String level, String message) {

			if (OUTPUT_LEVEL.indexOf(level) == -1) {
				return;
			}

			if ("T".equals(level)) {
				log.debug(message);
			} else if ("D".equals(level)) {
				log.debug(message);
			} else if ("I".equals(level)) {
				log.info(message);
			} else if ("W".equals(level)) {
				log.warn(message);
			} else if ("E".equals(level)) {
				log.error(message);
			} else if ("F".equals(level)) {
				log.fatal(message);
			}

		}
}
