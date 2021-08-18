package com.gabriel.afonso.domain.repository;

import com.gabriel.afonso.domain.model.Product;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.hibernate.Criteria;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {

    @Inject
    ProductRepositoryQueries productRepositoryQueries;

    public List<Product> findPrice(BigDecimal minPrice, BigDecimal maxPrice){

       return productRepositoryQueries.findPrice(minPrice,maxPrice);

    }

}
