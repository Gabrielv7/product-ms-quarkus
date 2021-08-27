package com.gabriel.afonso.service;

import com.gabriel.afonso.domain.model.Product;
import com.gabriel.afonso.domain.repository.ProductRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.math.BigDecimal;
import java.util.List;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;

    public List<Product> listar(){
        return productRepository.listAll();
    }

    public List<Product> listProductPrice(BigDecimal minPrice, BigDecimal maxPrice) {

        return productRepository.findPrice(minPrice,maxPrice);
    }

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

    @Transactional
    public void deletar (Long id){

        buscarOuFalhar(id);

        productRepository.deleteById(id);

    }

    public Product buscarOuFalhar(Long id){

        return productRepository.findByIdOptional(id)
                .orElseThrow(NotFoundException::new);

    }
}
