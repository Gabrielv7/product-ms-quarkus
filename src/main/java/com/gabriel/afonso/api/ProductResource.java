package com.gabriel.afonso.api;

import com.gabriel.afonso.domain.model.Product;
import com.gabriel.afonso.service.ProductService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    ProductService productService;

    @GET
    public List<Product> listar(){
        return productService.listar();
    }

    @GET
    @Path("/search")
    public List<Product> listProductPrice(@QueryParam("minPrice") BigDecimal minPrice,
                                            @QueryParam("maxPrice") BigDecimal maxPrice){

        return productService.listProductPrice(minPrice,maxPrice);

    }

    @GET
    @Path("/{id}")
    public Response buscar(@PathParam("id") Long id){

        var product = productService.buscarOuFalhar(id);

        return Response.ok().entity(product).build();
    }

    @POST
    public Response adicionar(@Valid Product product) {

      var productSave = productService.salvar(product);

      return Response.status(Response.Status.CREATED).entity(productSave).build();

    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id,
                              @Valid Product product){

       var productAt =  productService.atualizar(id,product);

        return Response.ok().entity(productAt).build();

    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletar(@PathParam("id") Long id){

        productService.deletar(id);

        return Response.noContent().build();
    }

}
