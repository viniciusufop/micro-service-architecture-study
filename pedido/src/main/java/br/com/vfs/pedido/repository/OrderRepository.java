package br.com.vfs.pedido.repository;

import br.com.vfs.pedido.entity.OrderEntity;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author vinicius
 * @version : $<br/>
 * : $
 * @since 19/06/19 13:23
 */
@Repository
public interface OrderRepository extends MongoRepository<OrderEntity, String> {

    List<OrderEntity> findByClient_CpfOrderByCreateDateAsc(String cpf);
}
