package br.com.vfs.estoque.mapper;

import br.com.vfs.estoque.dto.request.DepartmentRequest;
import br.com.vfs.estoque.dto.response.DepartmentResponse;
import br.com.vfs.estoque.model.DepartmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    @Mapping(source = "changeDate", dateFormat = "dd/MM/yyyy hh:mm:ss", target = "changeDate")
    DepartmentResponse departmentEntityToDepartmentResponse(DepartmentEntity departmentEntity);

    DepartmentEntity departmentRequestToDepartmentEntity(DepartmentRequest departmentRequest);

}
