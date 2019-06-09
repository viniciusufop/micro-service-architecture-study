package br.com.vfs.estoque.service.impl;

import br.com.vfs.estoque.exception.BusinessServiceException;
import br.com.vfs.estoque.model.ProductEntity;
import br.com.vfs.estoque.repository.ProductRepository;
import br.com.vfs.estoque.service.ProductService;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author vinicius
 * @version : $<br/>
 * : $
 * @since 07/06/19 13:25
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(final ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public ProductEntity save(final ProductEntity product){
        log.info("m=save, product={}", product);
        if (productRepository.findById(product.getId()).isPresent())
            throw new BusinessServiceException("Ja existe um produto com esse codigo id");
        return productRepository.save(product);
    }

    @Override
    public void delete(final String id){
        log.info("m=delete, id={}", id);
        productRepository.deleteById(id);
    }

    @Override
    public ProductEntity addAmount(final String id, final Integer amount){
        log.info("m=addAmount, id={}, amount={}", id, amount);
        ProductEntity product = findById(id);
        product.setAmount(product.getAmount() + amount);
        return productRepository.save(product);
    }

    @Override
    public ProductEntity subtractAmount(final String id, final Integer amount){
        log.info("m=subtractAmount, id={}, amount={}", id, amount);
        ProductEntity product = findById(id);
        if (product.getAmount() < amount)
            throw new BusinessServiceException("Quantidade nao esta disponivel no estoque");
        product.setAmount(product.getAmount() - amount);
        return productRepository.save(product);
    }

    @Override
    public ProductEntity findById(final String id){
        Optional<ProductEntity> optional = productRepository.findById(id);
        return optional.orElse(null);
    }
}
