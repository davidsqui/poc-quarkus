package org.dasc.repositories;

import org.dasc.entities.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ProductRepository {

    @Inject
    EntityManager entityManager;

    @Transactional
    public void create(Product product) {
        entityManager.persist(product);
    }

    @Transactional
    public void delete(Product product) {
        entityManager.remove(product);
    }

    public List<Product> read() {
        return entityManager.createQuery("SELECT p FROM Product p").getResultList();
    }

    public Product find(Long id) {
        return (Product) entityManager.createQuery("SELECT p FROM Product p WHERE p.id=:id")
        .setParameter("id", id)
        .getSingleResult();
    }
}
