package br.com.vfs.estoque.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DepartmentResponse {

    private Long id;

    private String name;

    private String description;

    private String changeDate;

}
