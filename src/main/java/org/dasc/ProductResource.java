package org.dasc;

import org.dasc.entities.Product;
import org.dasc.repositories.ProductRepository;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    ProductRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getProducts() {
        return repository.read();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getProduct(@PathParam("id") Long id) {
        return repository.find(id);
    }

    @POST
    public Response createProduct(Product product) {
        repository.create(product);
        return Response.ok().build();
    }

    @DELETE
    public Response deleteProduct(Product product) {
        repository.delete(product);
        return Response.noContent().build();
    }
}