package br.com.vfs.pedido.controller;

import br.com.vfs.pedido.entity.OrderEntity;
import br.com.vfs.pedido.service.OrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vinicius
 * @version : $<br/>
 * : $
 * @since 19/06/19 13:52
 */
@RestController
@RequestMapping("/v1/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(final OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping
    public OrderEntity create(@RequestBody OrderEntity order){
        return orderService.create(order);
    }

    @GetMapping(value = "/search")
    public List<OrderEntity> findByClient(@RequestParam("cpf") String cpf){
        return orderService.findByClient(cpf);
    }

    @GetMapping(value = "/{id}")
    public OrderEntity findById(@PathVariable("id") String id){
        return orderService.findById(id);
    }

    @PutMapping(value = "/{id}/finish")
    public OrderEntity finish(@PathVariable("id") String id){
        return orderService.finish(id);
    }

    @PutMapping(value = "/{id}/cancel")
    public OrderEntity cancel(@PathVariable("id") String id){
        return orderService.cancel(id);
    }

}
