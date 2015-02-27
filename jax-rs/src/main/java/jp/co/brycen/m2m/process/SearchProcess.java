/*****************************************************************
 *
 *****************************************************************/

package jp.co.brycen.m2m.process;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.mail.MessagingException;

import jp.co.brycen.common.ILogSender;
import jp.co.brycen.common.database.DBAccessor;
import jp.co.brycen.common.database.DBStatement;
import jp.co.brycen.common.dto.request.AbstractRequest;
import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.exception.DBException;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.m2m.dto.Row;
import jp.co.brycen.m2m.request.SearchItemRequest;
import jp.co.brycen.m2m.response.ResultResponse;

/**
 * The Class SearchProcess.
 */
public class SearchProcess extends AbstractProcess{
	
	/**
	 * Instantiates a new search process.
	 * 
	 * @param logSender
	 *            the log sender
	 */
	public SearchProcess(ILogSender logSender) {
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

			// Get request as SearchItemRequest
			SearchItemRequest searchItemInfo = (SearchItemRequest)inParam;
			String item = searchItemInfo.item;

			StringBuilder strSql = new StringBuilder();
			strSql.append("select * from hello");

			if (!item.equals("")) {
				strSql.append(" where message like '%").append(item).append("%'");
			}

			ps = dba.prepareStatement(strSql);
			rs = ps.executeQuery();

			ArrayList<Row> lst = new ArrayList<Row>();
			
			// Get parameters
			while(rs.next()){
				Row row = new Row();
				row.id = rs.getInt(1);
				row.message = rs.getString(2);
				lst.add(row);
			}
			
			// Finalize result
			ResultResponse result = new ResultResponse();
	        result.rows = lst;

			return result;
		}finally{
			if (ps != null ) ps.close();
			if (rs != null) rs.close();
		}

	}
}

