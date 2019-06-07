package br.com.vfs.estoque.service;

import br.com.vfs.estoque.model.DepartmentEntity;
import java.util.List;

/**
 * @author vinicius
 * @version : $<br/>
 * : $
 * @since 07/06/19 13:28
 */
public interface DepartmentService {

    /**
     * @param department
     * @return
     */
    DepartmentEntity save(DepartmentEntity department);

    /**
     * @param id
     */
    void delete(Long id);

    /**
     * @param id
     * @return
     */
    DepartmentEntity findById(Long id);

    /**
     * @param name
     * @return
     */
    List<DepartmentEntity> findByName(String name);
}
