package br.com.vfs.pedido.client;

import br.com.vfs.pedido.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("estoque")
//@FeignClient(url = "172.33.0.101:8080")
public interface EstoqueClient {

    @RequestMapping(value = "/v1/departments/{departmentId}/products/{id}", method = RequestMethod.GET)
    Product findById(@PathVariable("departmentId") Long departmentId,
            @PathVariable("id") String productId);

    @RequestMapping(value = "/v1/departments/{departmentId}/products/{id}/subtractAmount", method = RequestMethod.POST)
    void subtractAmount(@PathVariable("departmentId") Long departmentId,
            @PathVariable("id") String productId,
            @RequestParam("amount") Integer amount);

}
