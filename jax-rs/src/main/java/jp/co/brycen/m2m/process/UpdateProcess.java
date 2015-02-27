package jp.co.brycen.m2m.process;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.MessagingException;

import jp.co.brycen.common.ILogSender;
import jp.co.brycen.common.database.DBAccessor;
import jp.co.brycen.common.database.DBStatement;
import jp.co.brycen.common.dto.request.AbstractRequest;
import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.exception.DBException;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.m2m.request.UpdateItemRequest;
import jp.co.brycen.m2m.response.UpdateResultResponse;

/**
 * The Class UpdateProcess.
 */
public class UpdateProcess extends AbstractProcess {

	/**
	 * Instantiates a new update process.
	 *
	 * @param logSender the log sender
	 */
	public UpdateProcess(ILogSender logSender) {
		super(logSender);
	}

	/**
	 * Process and respond
	 */
	@Override
	public AbstractResponse process(DBAccessor dba, AbstractRequest inParam)
			throws DBException, SQLException, IOException, MessagingException,
			Exception {
		DBStatement ps = null;

		// Will return a UpdateResultResponse
		UpdateResultResponse result = new UpdateResultResponse();
		try {
			// Get request as UpdateItemRequest
			UpdateItemRequest updateItemInfo = (UpdateItemRequest) inParam;

			// Building SQL statement. (Should it be in DAO?)
			StringBuilder strSql = new StringBuilder();
			strSql.append("UPDATE hello");
			strSql.append(" SET message = ?");
			strSql.append(" WHERE id = ?");
			ps = dba.prepareStatement(strSql);
			// Set parameters
			ps.setString(1, updateItemInfo.message);
			ps.setInt(2, updateItemInfo.id);

			// Execute and finalize result
			result.result = ps.executeUpdate() > 0;
		} catch (DBException e) {

		} finally {
			if (ps != null)
				ps.close();
		}
		return result;
	}
}
