package br.com.vfs.estoque.repository;

import br.com.vfs.estoque.model.ProductEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author vinicius
 * @version : $<br/>
 * : $
 * @since 07/06/19 13:20
 */
@Repository
public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, String> {

}
