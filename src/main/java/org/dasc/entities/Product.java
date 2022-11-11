package org.dasc.entities;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Product extends PanacheEntity {

    private String code;
    private String name;
    private String description;
}
