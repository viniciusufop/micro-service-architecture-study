package br.com.vfs.estoque.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.vfs.estoque.dto.request.ProductRequest;
import br.com.vfs.estoque.dto.response.ProductResponse;
import br.com.vfs.estoque.mapper.ProductMapper;
import br.com.vfs.estoque.model.DepartmentEntity;
import br.com.vfs.estoque.model.ProductEntity;
import br.com.vfs.estoque.service.ProductService;
import br.com.vfs.estoque.util.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private ProductMapper productMapper;

    @Before
    public void setUp(){
        final ProductEntity productEntity = buildProductEntity();
        final ProductRequest productRequest = buildProductRequest();
        final ProductResponse productResponse = buildProductResponse();
        when(productMapper.productRequestToProductEntity(1l, productRequest)).thenReturn(productEntity);
        when(productMapper.productEntityToProductResponse(productEntity)).thenReturn(productResponse);
    }

    @Test
    public void whenSave_thenCreateProduct() throws Exception{
        final ProductRequest productRequest = buildProductRequest();
        final ProductEntity productEntity = buildProductEntity();
        given(productService.save(Mockito.any())).willReturn(productEntity);

        mvc.perform(post("/v1/departments/1/products").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(productRequest)))
                .andExpect(status().isOk()) //TODO mudar para created (201)
                .andExpect(jsonPath("$.id", is("112314.123123")));

    }

    private ProductRequest buildProductRequest(){
        return ProductRequest.builder()
                .name("A Bota do Bode")
                .id("112314.123123")
                .amount(10)
                .cost(19.99)
                .build();
    }

    private ProductEntity buildProductEntity(){
        return ProductEntity.builder()
                .name("A Bota do Bode")
                .id("112314.123123")
                .amount(10)
                .cost(19.99)
                .department(buildDepartmentEntity())
                .build();
    }

    private DepartmentEntity buildDepartmentEntity(){
        return DepartmentEntity.builder()
                .id(1l)
                .name("Literatura Nacional")
                .description("Descrição do departamento Literatura Nacional")
                .build();
    }

    private ProductResponse buildProductResponse(){
        return ProductResponse.builder()
                .name("A Bota do Bode")
                .id("112314.123123")
                .amount(10)
                .cost(19.99)
                .changeDate("12/10/2019 19:10:10")
                .build();
    }

}
