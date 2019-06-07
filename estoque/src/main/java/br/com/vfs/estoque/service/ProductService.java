package br.com.vfs.estoque.service;

import br.com.vfs.estoque.model.ProductEntity;

/**
 * @author vinicius
 * @version : $<br/>
 * : $
 * @since 07/06/19 13:25
 */
public interface ProductService {

    ProductEntity save(ProductEntity product);

    void delete(String id);

    ProductEntity addAmount(String id, Integer amount);

    ProductEntity subtractAmount(String id, Integer amount);

    ProductEntity findById(String id);
}
