package org.dasc.repositories;

import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import org.dasc.entities.Product;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductRepository implements PanacheRepositoryBase<Product, Long> {

}
