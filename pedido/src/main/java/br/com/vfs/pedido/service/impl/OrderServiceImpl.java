package br.com.vfs.pedido.service.impl;

import static br.com.vfs.pedido.entity.OrderEntity.Status.CANCELED;
import static br.com.vfs.pedido.entity.OrderEntity.Status.CREATED;
import static br.com.vfs.pedido.entity.OrderEntity.Status.FINISHED;

import br.com.vfs.pedido.client.EstoqueClient;
import br.com.vfs.pedido.dto.Product;
import br.com.vfs.pedido.entity.OrderEntity;
import br.com.vfs.pedido.entity.OrderItemEntity;
import br.com.vfs.pedido.exception.BusinessServiceException;
import br.com.vfs.pedido.exception.OrderNotFoundException;
import br.com.vfs.pedido.repository.OrderRepository;
import br.com.vfs.pedido.service.OrderService;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author vinicius
 * @version : $<br/>
 * : $
 * @since 19/06/19 13:29
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final EstoqueClient estoqueClient;

    @Autowired
    public OrderServiceImpl(final OrderRepository orderRepository,
            final EstoqueClient estoqueClient){
        this.orderRepository = orderRepository;
        this.estoqueClient = estoqueClient;
    }

    @Override
    public OrderEntity create(final OrderEntity order){
        log.info("m=create, order={}", order);
        validateItensExists(order);
        order.setCreateDate(new Date());
        order.setStatus(CREATED);
        return orderRepository.save(order);
    }

    @Override
    public OrderEntity findById(final String id){
        return getOrderById(id);
    }

    @Override
    public List<OrderEntity> findByClient(final String cpf){
        return orderRepository.findByClient_CpfOrderByCreateDateAsc(cpf);
    }

    @Override
    public OrderEntity finish(final String id){
        log.info("m=finish, id={}", id);
        OrderEntity order = getOrderById(id);
        validateItensAmount(order);
        order.setFinishDate(new Date());
        order.setStatus(FINISHED);
        updateProductAmount(order);
        return orderRepository.save(order);
    }

    @Override
    public OrderEntity cancel(final String id){
        log.info("m=cancel, id={}", id);
        OrderEntity order = getOrderById(id);
        order.setFinishDate(new Date());
        order.setStatus(CANCELED);
        return orderRepository.save(order);
    }

    private void validateItensExists(OrderEntity order){
        for (OrderItemEntity item : order.getItens()) {
            Product product = estoqueClient.findById(1L, item.getProduct());
            if (product == null)
                throw new BusinessServiceException("Produto nao encontrado");
        }
    }

    private OrderEntity getOrderById(String id){
        Optional<OrderEntity> optionalOrder = orderRepository.findById(id);
        return optionalOrder.orElseThrow(OrderNotFoundException::new);
    }

    private void validateItensAmount(OrderEntity order){
        order.getItens().forEach(item -> {
            Product product = estoqueClient.findById(1l, item.getProduct());
            if (product.getAmount() < item.getAmount())
                throw new BusinessServiceException("Quantidade de produto insuficiente no estoque");
        });
    }

    private void updateProductAmount(OrderEntity order){
        order.getItens().forEach(item -> {
            estoqueClient.subtractAmount(1l, item.getProduct(), item.getAmount());
            ;
        });
    }
}
