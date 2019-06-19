package br.com.vfs.pedido.entity;

import java.util.Date;
import java.util.List;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author vinicius
 * @version : $<br/>
 * : $
 * @since 19/06/19 13:21
 */
@Data
@Document("Order")
public class OrderEntity {

    @Id
    private String id;

    private Status status;

    private Date createDate;

    private List<OrderItemEntity> itens;

    private Date finishDate;

    private ClientEntity client;

    public enum Status {
        CREATED, FINISHED, CANCELED
    }
}
