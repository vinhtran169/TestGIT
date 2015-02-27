package jp.co.brycen.m2m.webservice;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.common.webservice.AbstractWebService;
import jp.co.brycen.m2m.process.InsertProcess;
import jp.co.brycen.m2m.request.InsertItemRequest;

import org.springframework.stereotype.Component;

@Path("/m2m")
@Component
public class InsertResource extends AbstractWebService {

	@POST
	@Path("/insert")
	@Consumes("application/json")
	@Produces("application/json")
	public AbstractResponse insert(InsertItemRequest item) throws SQLException {
		//ResultResponse result = (ResultResponse)super.execute(item);
		//return result;

		AbstractResponse result = super.execute(item);
		return result;
	}

	@Override
	protected AbstractProcess[] getProcesses() {
		return new AbstractProcess[] { new InsertProcess(this) };
	}
}