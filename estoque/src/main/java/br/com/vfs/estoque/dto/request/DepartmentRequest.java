package br.com.vfs.estoque.dto.request;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRequest implements Serializable {

    @NotBlank(message = "Nome deve ser informado")
    private String name;
    private String description;

}
