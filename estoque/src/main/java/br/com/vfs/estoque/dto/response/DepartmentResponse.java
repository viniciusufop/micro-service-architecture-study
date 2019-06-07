package br.com.vfs.estoque.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DepartmentResponse {

    private Long id;
    private String name;
    private String description;
    private String changeDate;

}
