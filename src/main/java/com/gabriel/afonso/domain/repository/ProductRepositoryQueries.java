package com.gabriel.afonso.domain.repository;

import com.gabriel.afonso.domain.model.Product;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

@ApplicationScoped
public class ProductRepositoryQueries {

    @Inject
    ProductRepositoryImpl productRepositoryImpl;

    public List<Product> findPrice(BigDecimal minPrice, BigDecimal maxPrice){

        return productRepositoryImpl.findPrice(minPrice,maxPrice);

    }

}
