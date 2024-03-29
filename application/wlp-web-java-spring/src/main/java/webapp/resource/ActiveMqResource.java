package webapp.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import webapp.tier.mq.activemq.ActiveMqService;

@Path("/activemq")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ActiveMqResource {

	@POST
	@Path("/put")
	public Response putcache() {
		ActiveMqService svc = new ActiveMqService();
		try {
			return Response.ok().entity(svc.putActiveMq()).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).build();
		}
	}

	@GET
	@Path("/get")
	public Response getcache() {
		ActiveMqService svc = new ActiveMqService();
		try {
			return Response.ok().entity(svc.getActiveMq()).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).build();
		}
	}

	@POST
	@Path("/publish")
	public Response publish() {
		ActiveMqService svc = new ActiveMqService();
		try {
			return Response.ok().entity(svc.publishActiveMq()).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).build();
		}
	}
}
