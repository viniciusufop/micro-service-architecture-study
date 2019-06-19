package br.com.vfs.pedido.service;

import br.com.vfs.pedido.entity.OrderEntity;
import java.util.List;

/**
 * @author vinicius
 * @version : $<br/>
 * : $
 * @since 19/06/19 13:27
 */
public interface OrderService {

    OrderEntity create(OrderEntity order);

    OrderEntity findById(String id);

    List<OrderEntity> findByClient(String cpf);

    OrderEntity finish(String id);

    OrderEntity cancel(String id);
}
