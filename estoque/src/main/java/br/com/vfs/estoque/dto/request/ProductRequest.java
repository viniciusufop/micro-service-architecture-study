package br.com.vfs.estoque.dto.request;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest implements Serializable {

    @NotBlank(message = "id deve ser informado")
    private String id;

    @NotBlank(message = "Nome deve ser informado")
    private String name;

    @NotNull(message = "Quantidade deve ser informada")
    private Integer amount;

    @NotNull(message = "Preço de custo deve ser informado")
    private Double cost;

}
