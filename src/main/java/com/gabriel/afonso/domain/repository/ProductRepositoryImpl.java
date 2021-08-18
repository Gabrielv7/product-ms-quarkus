package com.gabriel.afonso.domain.repository;

import com.gabriel.afonso.domain.model.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;

@ApplicationScoped
public class ProductRepositoryImpl {

    @PersistenceContext
    private EntityManager manager;

    public List<Product> findPrice(BigDecimal minPrice, BigDecimal maxPrice){

        var builder = manager.getCriteriaBuilder();

        var criteria = builder.createQuery(Product.class);

        var root = criteria.from(Product.class);

        var predicates = new ArrayList<Predicate>();

        if(minPrice != null){

            predicates.add(builder.greaterThanOrEqualTo(root.get("price"),minPrice));

        }

        if(maxPrice != null){

            predicates.add(builder.lessThanOrEqualTo(root.get("price"),maxPrice));

        }

        criteria.where(predicates.toArray(new Predicate[0]));

        return manager.createQuery(criteria).getResultList();

        }

    }

