package it.aidexa.controller;

import it.aidexa.dto.ProductDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.extensions.Extension;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/warehouse")
public class WarehouseController {

    @GET
    public String getAll(){return "All products";}

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Extension(name = "x-rond", value = "{\"requestFlow\": { \"policyName\": \"policy_to_be_executed_BEFORE_API_invocation\"}}", parseValue = true)
    public Response getById(@PathParam(value = "id") String id){
        ProductDTO product = new ProductDTO();
        product.id = id;
        product.name = "name";
        product.price = "1000";
        return Response.ok(product).build();}
    @GET
    @Path("/serverError")
    @Produces(MediaType.APPLICATION_JSON)
    @Extension(name = "x-rond", value = "{\"requestFlow\": { \"policyName\": \"policy_to_be_executed_BEFORE_API_invocation\"}}", parseValue = true)
    public Response getById2(){
        ProductDTO product = new ProductDTO();
        product.id = "pippo";
        product.name = "name";
        product.price = "1000";
        return Response.serverError().build();}
    @POST
    public ProductDTO add(ProductDTO product){return product;}

    @PUT
    @Path("{id}")
    public String editById(@PathParam(value = "id") String id){return "Product with id: "+ id + "modified";}

    @DELETE
    @Path("{id}")
    public String deleteById(@PathParam(value = "id") String id){return "Product with id: "+ id + "deleted";}
}
