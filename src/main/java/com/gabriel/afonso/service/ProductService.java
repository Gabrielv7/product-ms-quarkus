package com.gabriel.afonso.service;

import com.gabriel.afonso.domain.model.Product;
import com.gabriel.afonso.domain.repository.ProductRepository;
import org.hibernate.Criteria;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;

    @Transactional
    public Product salvar(Product product){

        productRepository.persist(product);
        return product;

    }

    @Transactional
    public Product atualizar(Long id, Product product){

        var productId = buscarOuFalhar(id);

        productId.setName(product.getName());
        productId.setDescription(product.getDescription());
        productId.setPrice(product.getPrice());

       return salvar(productId);
    }

    public Product buscarOuFalhar(Long id){

        return productRepository.findByIdOptional(id)
                .orElseThrow(NotFoundException::new);

    }




}
