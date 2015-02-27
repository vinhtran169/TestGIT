package jp.co.brycen.m2m.webservice;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import jp.co.brycen.common.dto.response.AbstractResponse;
import jp.co.brycen.common.process.AbstractProcess;
import jp.co.brycen.common.webservice.AbstractWebService;
import jp.co.brycen.m2m.process.UpdateProcess;
import jp.co.brycen.m2m.request.UpdateItemRequest;

import org.springframework.stereotype.Component;

@Path("/m2m")
@Component
public class UpdateResource extends AbstractWebService {
	public UpdateResource(){
		System.out.println("MY LOG========= USING UpdateResource");
	}
	@POST
	@Path("/update")
	@Consumes("application/json")
	@Produces("application/json")
	public AbstractResponse search(UpdateItemRequest item) throws SQLException {
		AbstractResponse result = super.execute(item);
		return result;
	}

	@Override
	protected AbstractProcess[] getProcesses() {
		return new AbstractProcess[] { new UpdateProcess(this) };
	}
}