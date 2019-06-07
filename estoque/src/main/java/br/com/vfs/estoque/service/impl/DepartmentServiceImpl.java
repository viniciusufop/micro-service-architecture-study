package br.com.vfs.estoque.service.impl;

import br.com.vfs.estoque.exception.BusinessServiceException;
import br.com.vfs.estoque.model.DepartmentEntity;
import br.com.vfs.estoque.repository.DepartmentRepository;
import br.com.vfs.estoque.service.DepartmentService;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author vinicius
 * @version : $<br/>
 * : $
 * @since 07/06/19 13:29
 */
@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(final DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentEntity save(final DepartmentEntity department){
        log.info("m=save, department={}", department);
        if (department.isNew() && departmentRepository.findByName(department.getName()).isPresent())
            throw new BusinessServiceException("Ja existe um departamento com esse nome");
        return departmentRepository.save(department);
    }

    @Override
    public void delete(final Long id){
        log.info("m=delete, id={}", id);
        departmentRepository.deleteById(id);
    }

    @Override
    public DepartmentEntity findById(final Long id){
        log.info("m=findById, id={}", id);
        Optional<DepartmentEntity> optional = departmentRepository.findById(id);
        return optional.orElseGet(null);
    }

    @Override
    public List<DepartmentEntity> findByName(final String name){
        log.info("m=findByName, name={}", name);
        return departmentRepository.findByNameContainingIgnoreCase(name);
    }
}
