/*****************************************************************
 *
 *****************************************************************/

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
import jp.co.brycen.m2m.request.DeleteItemRequest;
import jp.co.brycen.m2m.response.DeleteResultResponse;

/**
 * The Class DeleteProcess.
 */
public class DeleteProcess extends AbstractProcess {

	/**
	 * Instantiates a new update process.
	 * 
	 * @param logSender
	 *            the log sender
	 */
	public DeleteProcess(ILogSender logSender) {
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

		// Return a DeleteResultResponse
		DeleteResultResponse result = new DeleteResultResponse();
		try {
			// Get request as DeleteItemRequest
			DeleteItemRequest deleteItemInfo = (DeleteItemRequest) inParam;
			StringBuilder strSql = new StringBuilder();

			strSql.append("DELETE FROM hello " + "WHERE id = ?");
			ps = dba.prepareStatement(strSql);
			
			// Set parameters
			ps.setInt(1, deleteItemInfo.id);
			ps.executeUpdate();

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
