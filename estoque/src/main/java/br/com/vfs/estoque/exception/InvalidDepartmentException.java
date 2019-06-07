package br.com.vfs.estoque.exception;

/**
 * @author vinicius
 * @version : $<br/>
 * : $
 * @since 07/06/19 13:51
 */
public class InvalidDepartmentException extends BusinessServiceException {

    private static final String MESSAGE = "Departamento invalido";

    public InvalidDepartmentException(){
        super(MESSAGE);
    }
}
