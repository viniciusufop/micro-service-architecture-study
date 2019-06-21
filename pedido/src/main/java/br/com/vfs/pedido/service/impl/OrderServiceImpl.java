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

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
    @HystrixCommand
    public OrderEntity create(final OrderEntity order){
        log.info("m=create, order={}", order);
        validateItensExists(order);
        order.setCreateDate(new Date());
        order.setStatus(CREATED);
        return orderRepository.save(order);
    }

    @Override
    @HystrixCommand
    public OrderEntity findById(final String id){
        return getOrderById(id);
    }

    @Override
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
                    value = "1000")}, fallbackMethod = "fallBackFindByClient")
    public List<OrderEntity> findByClient(final String cpf){
        sleep(); //para forcar ir no fallback
        return orderRepository.findByClient_CpfOrderByCreateDateAsc(cpf);
    }

    /**
     * metodo de fallback para teste do hystrix
     * @param cpf
     * @return
     */
    private List<OrderEntity> fallBackFindByClient(String cpf) {
        return Collections.EMPTY_LIST;
    }

    /**
     * metodo para forcar o fallback por timeout
     */
    private void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
            final Product product = findProductByBarCode(item);
            if (product == null)
                throw new BusinessServiceException("Produto nao encontrado");
        }
    }

    @HystrixCommand(threadPoolKey = "productByBarCodeThreadPool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize",value="30"),
                    @HystrixProperty(name="maxQueueSize", value="10")})
    private Product findProductByBarCode(OrderItemEntity item) {
        sleep();
        return estoqueClient.findById(1l, item.getProduct());
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
            subtractProductAmount(item);
        });
    }

    @HystrixCommand
    private void subtractProductAmount(OrderItemEntity item) {
        estoqueClient.subtractAmount(1l, item.getProduct(), item.getAmount());
    }
}
