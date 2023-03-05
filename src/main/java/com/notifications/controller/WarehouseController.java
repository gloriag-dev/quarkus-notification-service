package com.notifications.controller;

import com.notifications.dto.ProductDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.notifications.service.WarehouseService;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.extensions.Extension;

@Path("/warehouse")
@Slf4j  //logger di java

public class WarehouseController {

    @Inject
    WarehouseService warehouseService;
    @GET
    public String getAll(){return "All products";}

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Extension(name = "x-rond", value = "{\"requestFlow\": { \"policyName\": \"policy_to_be_executed_BEFORE_API_invocation\"}}", parseValue = true)
    public Response getById(@PathParam(value = "id") String id){
        ProductDTO product = new ProductDTO();
        product.setId(id);
        product.setName("scatola");
        product.setPrice(1000);
        product.setQuantity(5);
        int totPrice = product.getTotalPrice();
        log.info("stiamo scrivendo il prodotto " + product);
        log.info("il prezzo totale" +  totPrice);
        return Response.ok(product).build();}
    @GET
    @Path("/serverError")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById2(){
        ProductDTO product = new ProductDTO();
        product.setId("pippo");
        product.setName("name");
        product.setPrice(500);
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
