package br.com.vfs.estoque.repository;

import static org.assertj.core.api.Assertions.assertThat;

import br.com.vfs.estoque.model.DepartmentEntity;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DepartmentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void whenFindByName_thenReturnDepartment(){
        DepartmentEntity department = buildDepartment1();
        entityManager.persist(department);
        entityManager.flush();

        Optional<DepartmentEntity> result = departmentRepository.findByName(department.getName());
        assertThat(result.orElseThrow(RuntimeException::new).getName()).isEqualTo(department.getName());
    }

    @Test
    public void whenInvalidName_thenReturnNull(){
        Optional<DepartmentEntity> result = departmentRepository.findByName("NAME_NOT_FOUND");
        assertThat(result.isPresent()).isFalse();
    }

    @Test
    public void whenFindByContainingName_thenReturnDepartment(){
        DepartmentEntity department1 = buildDepartment1();
        DepartmentEntity department2 = buildDepartment2();
        entityManager.persist(department1);
        entityManager.persist(department2);
        entityManager.flush();

        List<DepartmentEntity> result = departmentRepository.findByNameContainingIgnoreCase("lIteratura");
        assertThat(result.size()).isEqualTo(2);
    }

    private DepartmentEntity buildDepartment1(){
        return DepartmentEntity.builder()
                .name("Literatura Infantil")
                .build();
    }

    private DepartmentEntity buildDepartment2(){
        return DepartmentEntity.builder()
                .name("Literatura Nacional")
                .build();
    }

}
