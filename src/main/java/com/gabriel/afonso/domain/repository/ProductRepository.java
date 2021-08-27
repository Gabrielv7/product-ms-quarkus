package com.gabriel.afonso.domain.repository;

import com.gabriel.afonso.domain.model.Product;
import io.quarkus.hibernate.orm.panache.PanacheRepository;


import javax.enterprise.context.ApplicationScoped;
import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {

    public List<Product> findPrice(BigDecimal minPrice, BigDecimal maxPrice){

        var builder = this.getEntityManager().getCriteriaBuilder();

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

        return this.getEntityManager().createQuery(criteria).getResultList();

    }

}
