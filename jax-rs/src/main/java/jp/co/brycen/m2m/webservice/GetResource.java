package jp.co.brycen.m2m.webservice;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.common.webservice.AbstractWebService;
import jp.co.brycen.m2m.process.GetProcess;
import jp.co.brycen.m2m.request.GetItemRequest;

import org.springframework.stereotype.Component;

@Path("/m2m")
@Component
public class GetResource extends AbstractWebService {
	public GetResource(){
		System.out.println("MY LOG========= USING GetResource");
	}
	@POST
	@Path("/get")
	@Consumes("application/json")
	@Produces("application/json")
	public AbstractResponse search(GetItemRequest item) throws SQLException {
		AbstractResponse result = super.execute(item);
		return result;
	}

	@Override
	protected AbstractProcess[] getProcesses() {
		return new AbstractProcess[] { new GetProcess(this) };
	}
}