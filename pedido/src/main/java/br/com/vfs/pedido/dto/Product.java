package br.com.vfs.pedido.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class Product implements Serializable {

    private Long id;

    private String name;

    private String barCode;

    private String department;

    private Integer amount;
}
