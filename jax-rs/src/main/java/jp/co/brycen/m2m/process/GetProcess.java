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
import jp.co.brycen.m2m.dto.Row;
import jp.co.brycen.m2m.request.GetItemRequest;
import jp.co.brycen.m2m.response.GetResultResponse;

/**
 * The Class GetProcess.
 */
public class GetProcess extends AbstractProcess{

	/**
	 * Instantiating.
	 *
	 * @param logSender
	 */
	public GetProcess(ILogSender logSender) {
		super(logSender);
	}

	/**
	 * Process and respond
	 */
	@Override
	public AbstractResponse process(DBAccessor dba, AbstractRequest inParam) throws DBException, SQLException, IOException, MessagingException, Exception
	{
		ResultSet rs = null;
		DBStatement ps = null;

		try
		{
			// Get request as GetItemRequest
			GetItemRequest getItemInfo = (GetItemRequest)inParam;

			// Building SQL statement. (Should it be in DAO?)
			StringBuilder strSql = new StringBuilder();
			strSql.append("SELECT * FROM hello");
			strSql.append(" WHERE id = ?");
			ps = dba.prepareStatement(strSql);
			// Set parameter
			ps.setInt(1, getItemInfo.id);

			// Execute and get query
			rs = ps.executeQuery();
			Row row = null;
			if(rs.next()){
				row = new Row();
				row.id = rs.getInt(1);
				row.message = rs.getString(2);
			}

			// Returns a GetResultResponse
			GetResultResponse result = new GetResultResponse();
	        result.row = row;
			return result;
		}finally{
			if (ps != null ) ps.close();
			if (rs != null) rs.close();
		}
	}
}

