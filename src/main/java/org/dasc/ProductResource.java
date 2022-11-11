package org.dasc;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;
import org.dasc.entities.Product;

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

import static javax.ws.rs.core.Response.Status.*;

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<PanacheEntityBase>> getProducts() {
        return Product.listAll(Sort.by("code"));
    }

    @GET
    @Path("/{id}")
    public Uni<PanacheEntityBase> getProduct(@PathParam("id") Long id) {
        return Product.findById(id);
    }

    @POST
    public Uni<Response> createProduct(Product product) {
        return Panache.withTransaction(product::persist)
                .replaceWith(Response.ok(product).status(CREATED)::build);
    }

    @DELETE
    public Uni<Response> deleteProduct(@PathParam("id") Long id) {
        return Panache.withTransaction(() -> Product.deleteById(id))
                .map(deleted -> deleted
                        ? Response.ok().status(NO_CONTENT).build()
                        : Response.ok().status(NOT_FOUND).build());
    }
}