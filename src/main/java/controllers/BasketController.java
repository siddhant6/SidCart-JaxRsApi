package controllers;

import exceptions.InvalidRequestException;
import pojos.BasketItem;
import services.BasketService;
import utils.JSONOperations;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Path("/basket")
public class BasketController {
    
    private BasketService basketService = new BasketService();
    
    @GET
    @Path("/{basketId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBasket(@PathParam("basketId") int basketId) {
        
        try {
            List<Map<String,Object>> mapList = basketService.getBasketItems(basketId);
            return Response.ok(JSONOperations.
                    listMapToJson(mapList),MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addItem(BasketItem item) {
        
        try {
            basketService.addItemToBasket(item);
            return Response.status(Response.Status.CREATED).build();
        } catch (InvalidRequestException e) {
            return Response.status(Response.Status.BAD_REQUEST).
                    entity(e.getMessage()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @DELETE
    @Path("/item/{itemId}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteBasketItem(@PathParam("itemId") int itemId) {
    
        try {
            basketService.deleteBasketItem(itemId);
            return Response.status(Response.Status.CREATED).build();
        } catch (InvalidRequestException e) {
            return Response.status(Response.Status.BAD_REQUEST).
                    entity(e.getMessage()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBasketFromUserId(@QueryParam("userId") int userId) {
        
        try {
            List<Map<String,Object>> mapList = basketService.getBasketFromUserId(userId);
            return Response.ok(JSONOperations.
                    listMapToJson(mapList),MediaType.APPLICATION_JSON).build();
        } catch (InvalidRequestException e) {
            return Response.status(Response.Status.BAD_REQUEST).
                    entity(e.getMessage()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
