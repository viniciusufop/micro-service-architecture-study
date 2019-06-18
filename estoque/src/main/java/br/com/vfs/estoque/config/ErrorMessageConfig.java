package br.com.vfs.estoque.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @author vinicius
 * @version : $<br/>
 * : $
 * @since 18/06/19 13:05
 */
@Data
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "error.message")
public class ErrorMessageConfig {

    private String productExist;

    private String productQuantityNotAvailable;

    private String departmentExist;

}
