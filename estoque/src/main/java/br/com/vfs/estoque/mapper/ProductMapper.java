package br.com.vfs.estoque.mapper;

import br.com.vfs.estoque.dto.request.ProductRequest;
import br.com.vfs.estoque.dto.response.ProductResponse;
import br.com.vfs.estoque.model.ProductEntity;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
@DecoratedWith(ProductMapperDecorator.class)
public interface ProductMapper {

    @Mappings({
            @Mapping(source = "department.name", target = "department"),
            @Mapping(source = "changeDate", dateFormat = "dd/MM/yyyy hh:mm:ss", target = "changeDate")
    })
    ProductResponse productEntityToProductResponse(ProductEntity productEntity);

    ProductEntity productRequestToProductEntity(Long departmentId, ProductRequest productRequest);

}
