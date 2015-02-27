package jp.sample.jaxrs.service;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


@Path("/sample")
public interface SampleResource {

    @GET
    @Path("/hello/{message}")
    String sayHello(@PathParam("message") String message);

    @GET
    @Path("/hello1")
    @Produces("application/json")
    @Consumes("application/json")
    iteminfo sayHello1();

    @POST
    @Path("/hello2")
    @Consumes("application/json")
    @Produces("application/json")
    iteminfo sayHello2(iteminfo item);

    @POST
    @Path("/search")
    @Consumes("application/json")
    @Produces("application/json")
    Result search(SearchItemInfo item)  throws SQLException;

    @POST
    @Path("/insert")
    @Consumes("application/json")
    @Produces("application/json")
    Result insert(SearchItemInfo item)  throws SQLException;
}