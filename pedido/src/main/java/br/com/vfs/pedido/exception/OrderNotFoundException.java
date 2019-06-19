package br.com.vfs.pedido.exception;

/**
 * @author vinicius
 * @version : $<br/>
 * : $
 * @since 19/06/19 13:36
 */
public class OrderNotFoundException extends BusinessServiceException {

    public OrderNotFoundException(){
        super("Pedido não encontrado");
    }
}
