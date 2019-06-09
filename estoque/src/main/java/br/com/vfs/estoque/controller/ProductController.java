package br.com.vfs.estoque.controller;

import br.com.vfs.estoque.dto.request.ProductRequest;
import br.com.vfs.estoque.dto.response.ProductResponse;
import br.com.vfs.estoque.mapper.ProductMapper;
import br.com.vfs.estoque.model.ProductEntity;
import br.com.vfs.estoque.service.ProductService;
import java.net.URI;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vinicius
 * @version : $<br/>
 * : $
 * @since 07/06/19 14:02
 */
@RestController
@RequestMapping(value = "/v1/departments/{idDepartments}/products")
public class ProductController {

    private final ProductService productService;

    private final ProductMapper productMapper;

    @Autowired
    public ProductController(final ProductService productService, final ProductMapper productMapper){
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> save(@PathVariable("idDepartments") Long departmentId,
            @Valid @RequestBody ProductRequest productRequest){
        final ProductEntity product = productMapper.productRequestToProductEntity(departmentId, productRequest);
        final ProductResponse response = productMapper.productEntityToProductResponse(productService.save(product));
        return ResponseEntity
                .created(URI.create(String.format("/v1/departments/%d/products/%s", departmentId, response.getId())))
                .body(response);
    }

    @GetMapping(value = "/{id}")
    public ProductResponse findById(@PathVariable("idDepartments") Long departmentId,
            @PathVariable("id") String id){
        final ProductEntity product = productService.findById(id);
        return productMapper.productEntityToProductResponse(product);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("idDepartments") Long departmentId,
            @PathVariable("id") String id){
        productService.delete(id);
    }

    @PostMapping(value = "/{id}/addAmount")
    public ProductResponse addAmount(@PathVariable("idDepartments") Long departmentId,
            @PathVariable("id") String id,
            @RequestParam("amount") Integer amount){
        final ProductEntity product = productService.addAmount(id, amount);
        return productMapper.productEntityToProductResponse(product);
    }

    @PostMapping(value = "/{id}/subtractAmount")
    public ProductResponse subtractAmount(@PathVariable("idDepartments") Long departmentId,
            @PathVariable("id") String id,
            @RequestParam("amount") Integer amount){
        final ProductEntity product = productService.subtractAmount(id, amount);
        return productMapper.productEntityToProductResponse(product);
    }
}
