package br.com.vfs.estoque.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import br.com.vfs.estoque.config.ErrorMessageConfig;
import br.com.vfs.estoque.exception.BusinessServiceException;
import br.com.vfs.estoque.model.DepartmentEntity;
import br.com.vfs.estoque.repository.DepartmentRepository;
import br.com.vfs.estoque.service.impl.DepartmentServiceImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class DepartmentServiceTest {

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private ErrorMessageConfig errorMessageConfig;

    @Before
    public void setUp(){
        final DepartmentEntity department1 = buildDepartment1();
        final DepartmentEntity department2 = buildDepartment2();
        when(departmentRepository.findByNameContainingIgnoreCase("literatura"))
                .thenReturn(Arrays.asList(department1, department2));
        when(departmentRepository.findByName(department1.getName())).thenReturn(Optional.of(department1));
    }

    @Test
    public void whenFindByName_thenDepartmentSholdBeFound(){
        final String name = "literatura";
        final List<DepartmentEntity> result = departmentService.findByName(name);
        assertThat(result.size()).isEqualTo(2);
    }

    @Test(expected = BusinessServiceException.class)
    public void whenSaveWithSameName_thenFail(){
        final DepartmentEntity department = buildDepartment1();
        departmentService.save(department);
    }

    private DepartmentEntity buildDepartment1(){
        return DepartmentEntity.builder()
                .name("Literatura Infantil")
                .description("Descrição do departamento Literatura Infantil")
                .build();
    }

    private DepartmentEntity buildDepartment2(){
        return DepartmentEntity.builder()
                .name("Literatura Nacional")
                .description("Descrição do departamento Literatura Nacional")
                .build();
    }

}
