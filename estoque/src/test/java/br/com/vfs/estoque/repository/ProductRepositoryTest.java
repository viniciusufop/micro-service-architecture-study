package br.com.vfs.estoque.repository;

import static org.assertj.core.api.Assertions.assertThat;

import br.com.vfs.estoque.model.DepartmentEntity;
import br.com.vfs.estoque.model.ProductEntity;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void whenFindById_thenReturnProduct(){
        DepartmentEntity department = buildDepartment();
        department = entityManager.persist(department);
        ProductEntity product = buildProduct(department);
        entityManager.persist(product);
        entityManager.flush();

        Optional<ProductEntity> result = productRepository.findById(product.getId());
        assertThat(result.orElseThrow(RuntimeException::new).getName()).isEqualTo(product.getName());
    }

    @Test
    public void whenInvalidId_thenReturnNull(){
        Optional<ProductEntity> result = productRepository.findById("ID_NOT_FOUND");
        assertThat(result.isPresent()).isFalse();
    }

    private ProductEntity buildProduct(DepartmentEntity department){
        return ProductEntity.builder()
                .name("A Bota do Bode")
                .id("112314.123123")
                .amount(10)
                .cost(19.99)
                .department(department)
                .build();
    }

    private DepartmentEntity buildDepartment(){
        return DepartmentEntity.builder()
                .name("Literatura Infantil")
                .build();
    }

}
