package it.aidexa;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String get(@QueryParam(value = "name")  String name) {
        return "Hello " + name;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getInlineName(@PathParam(value = "id") String id) {
        return "Hello " + id;
    }
    @GET
    @Path("pippo/pluto")
    @Produces(MediaType.TEXT_PLAIN)
    public String getStuff() {
        return "Hello " + "pippo/pluto";
    }
    @POST
    public String submit(){
        return "Added";
    }

    @PUT
    public String edit() {return "Modified";}

    @PATCH
    public String change() {return "Changed";}

    @DELETE
    public String remove() {return "Deleted";}
}