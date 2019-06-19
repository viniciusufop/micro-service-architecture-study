package br.com.vfs.pedido.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author vinicius
 * @version : $<br/>
 * : $
 * @since 19/06/19 13:20
 */
@Data
@Document("Client")
public class ClientEntity {

    @Id
    private String cpf;

    private String name;
}
