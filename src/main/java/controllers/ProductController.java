package controllers;

import services.ProductService;
import utils.JSONOperations;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Path("/products")
public class ProductController {
    
    private ProductService productService = new ProductService();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts() {
        
        try {
            List<Map<String,Object>> mapList = productService.getAllProducts();
            return Response.ok(JSONOperations.
                    listMapToJson(mapList), MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
