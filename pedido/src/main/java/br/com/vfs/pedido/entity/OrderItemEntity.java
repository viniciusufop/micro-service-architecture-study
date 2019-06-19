package br.com.vfs.pedido.entity;

import java.io.Serializable;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author vinicius
 * @version : $<br/>
 * : $
 * @since 19/06/19 13:17
 */
@Data
@Document("OrderItem")
public class OrderItemEntity implements Serializable {

    private Integer amount;

    private String product;
}
