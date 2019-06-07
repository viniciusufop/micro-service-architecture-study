package br.com.vfs.estoque.mapper;

import br.com.vfs.estoque.dto.request.ProductRequest;
import br.com.vfs.estoque.exception.InvalidDepartmentException;
import br.com.vfs.estoque.model.DepartmentEntity;
import br.com.vfs.estoque.model.ProductEntity;
import br.com.vfs.estoque.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class ProductMapperDecorator implements ProductMapper {

    @Autowired
    @Qualifier("delegate")
    private ProductMapper delegate;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public ProductEntity productRequestToProductEntity(Long departmentId, ProductRequest productRequest){
        ProductEntity product = delegate.productRequestToProductEntity(departmentId, productRequest);
        product.setDepartment(getDepartment(departmentId));
        return product;
    }

    private DepartmentEntity getDepartment(Long departmentId){
        return departmentRepository.findById(departmentId).orElseThrow(InvalidDepartmentException::new);
    }

}
