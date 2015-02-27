/*****************************************************************
 *
 *****************************************************************/

package jp.co.brycen.m2m.process;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.mail.MessagingException;

import jp.co.brycen.common.ILogSender;
import jp.co.brycen.common.database.DBAccessor;
import jp.co.brycen.common.database.DBStatement;
import jp.co.brycen.common.dto.request.AbstractRequest;
import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.exception.DBException;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.m2m.request.InsertItemRequest;
import jp.co.brycen.m2m.response.InsertResultResponse;

/**
 * The Class InsertProcess.
 */
public class InsertProcess extends AbstractProcess {
	
	/**
	 * Instantiates a new insert process.
	 * 
	 * @param logSender
	 *            the log sender
	 */

	public InsertProcess(ILogSender logSender) {
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
		ResultSet rs = null;
		
		// Return a InsertResultResponse
		InsertResultResponse result = new InsertResultResponse();
		
		try {
			
			// Get request as InsertItemRequest
			InsertItemRequest insertItemInfo = (InsertItemRequest) inParam;
			int idmax = 0;
			StringBuilder strSql1 = new StringBuilder();
			strSql1.append("SELECT MAX(id) FROM hello");
			ps = dba.prepareStatement(strSql1);
			rs = ps.executeQuery();
			if (rs.next()) {
				idmax = rs.getInt(1);
			}

			StringBuilder strSql = new StringBuilder();
			strSql.append("INSERT INTO hello(id, message) " + "VALUES (?,?)");
			ps = dba.prepareStatement(strSql);
			
			// Set parameters
			ps.setInt(1, idmax + 1);
			ps.setString(2, insertItemInfo.message);
			ps.executeUpdate();
			
			result.result = true;

		} catch (DBException e) {

		} finally {
			if (ps != null)
				ps.close();
			if (rs != null)
				rs.close();
		}
		return result;
	}
}
