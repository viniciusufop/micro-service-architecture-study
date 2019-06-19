package br.com.vfs.pedido.exception;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString @EqualsAndHashCode
public class MessageException {

    private int code;
    private String messages;

}
