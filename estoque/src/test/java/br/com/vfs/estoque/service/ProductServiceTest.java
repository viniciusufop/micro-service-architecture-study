package br.com.vfs.estoque.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import br.com.vfs.estoque.config.ErrorMessageConfig;
import br.com.vfs.estoque.exception.BusinessServiceException;
import br.com.vfs.estoque.model.DepartmentEntity;
import br.com.vfs.estoque.model.ProductEntity;
import br.com.vfs.estoque.repository.ProductRepository;
import br.com.vfs.estoque.service.impl.ProductServiceImpl;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ErrorMessageConfig errorMessageConfig;

    @Before
    public void setUp(){
        final ProductEntity product = buildProduct();
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
    }

    @Test(expected = BusinessServiceException.class)
    public void whenSaveWithSameId_thenFail(){
        final ProductEntity product = buildProduct();
        productService.save(product);
    }

    @Test
    public void whenFindById_thenReturnProduct(){
        final ProductEntity product = buildProduct();
        final ProductEntity result = productService.findById(product.getId());
        assertThat(result.getName()).isEqualTo(product.getName());
    }

    @Test
    public void whenFindByWrongId_thenReturnNull(){
        final ProductEntity result = productService.findById("ISBN_NOT_FOUND");
        assertThat(result).isNull();
    }

    @Test(expected = BusinessServiceException.class)
    public void whenSubtractAmountNegative_thenFail(){
        productService.subtractAmount("112314.123123", 11);
    }

    private ProductEntity buildProduct(){
        return ProductEntity.builder()
                .name("A Bota do Bode")
                .id("112314.123123")
                .amount(10)
                .cost(19.99)
                .department(buildDepartment())
                .build();
    }

    private DepartmentEntity buildDepartment(){
        return DepartmentEntity.builder()
                .name("Literatura Infantil")
                .description("Descrição do departamento Literatura Infantil")
                .build();
    }

}
