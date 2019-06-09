package br.com.vfs.estoque.controller;

import br.com.vfs.estoque.dto.request.DepartmentRequest;
import br.com.vfs.estoque.dto.response.DepartmentResponse;
import br.com.vfs.estoque.mapper.DepartmentMapper;
import br.com.vfs.estoque.model.DepartmentEntity;
import br.com.vfs.estoque.service.DepartmentService;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vinicius
 * @version : $<br/>
 * : $
 * @since 07/06/19 13:58
 */
@RestController
@RequestMapping(value = "/v1/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    private final DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentController(final DepartmentService departmentService,
            final DepartmentMapper departmentMapper){
        this.departmentService = departmentService;
        this.departmentMapper = departmentMapper;
    }

    @PostMapping
    public ResponseEntity<DepartmentResponse> save(@Valid @RequestBody DepartmentRequest departmentRequest){
        final DepartmentEntity department = departmentMapper.departmentRequestToDepartmentEntity(departmentRequest);
        final DepartmentResponse response = departmentMapper
                .departmentEntityToDepartmentResponse(departmentService.save(department));
        return ResponseEntity
                .created(URI.create(String.format("/v1/departments/%d", response.getId())))
                .body(response);
    }

    @GetMapping(value = "/search")
    public List<DepartmentResponse> findByName(@RequestParam("name") String name){
        final List<DepartmentEntity> departments = departmentService.findByName(name);
        if (departments != null)
            return departments.stream().map(departmentMapper::departmentEntityToDepartmentResponse).collect(
                    Collectors.toList());
        return Collections.EMPTY_LIST;
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id){
        departmentService.delete(id);
    }

    @GetMapping(value = "/{id}")
    public DepartmentResponse findById(@PathVariable("id") Long id){
        return departmentMapper.departmentEntityToDepartmentResponse(departmentService.findById(id));
    }
}
