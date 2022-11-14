package org.dasc.graphql;

import io.smallrye.mutiny.Uni;
import org.dasc.entities.Product;
import org.dasc.repositories.ProductRepository;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

import javax.inject.Inject;
import java.util.List;

@GraphQLApi
public class ProductGraphqlResource {
    @Inject
    ProductRepository repository;

    @Query("allProducts")
    @Description("list of products")
    public Uni<List<Product>> getAll() {
        return repository.listAll();
    }

    @Query
    @Description("get product by id")
    public Uni<Product> getProduct(@Name("productId") Long id) {
        return repository.findById(id);
    }
}
