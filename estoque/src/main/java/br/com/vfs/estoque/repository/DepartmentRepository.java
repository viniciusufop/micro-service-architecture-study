package br.com.vfs.estoque.repository;

import br.com.vfs.estoque.model.DepartmentEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author vinicius
 * @version : $<br/>
 * : $
 * @since 07/06/19 13:20
 */
@Repository
public interface DepartmentRepository extends PagingAndSortingRepository<DepartmentEntity, Long> {

    Optional<DepartmentEntity> findByName(String name);

    List<DepartmentEntity> findByNameContainingIgnoreCase(String name);
}
