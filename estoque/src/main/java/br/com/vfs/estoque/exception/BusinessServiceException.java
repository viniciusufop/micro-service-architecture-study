package br.com.vfs.estoque.exception;

/**
 * @author vinicius
 * @version : $<br/>
 * : $
 * @since 07/06/19 13:32
 */
public class BusinessServiceException extends RuntimeException {

    private final String message;

    public BusinessServiceException(String message){
        super(message);
        this.message = message;
    }
}
