package br.com.vfs.estoque.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductResponse {

    private String id;

    private String name;

    private Integer amount;

    private Double cost;

    private String department;

    private String changeDate;

}
