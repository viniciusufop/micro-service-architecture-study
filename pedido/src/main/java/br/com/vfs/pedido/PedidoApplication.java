package br.com.vfs.pedido;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableHystrix
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableFeignClients
@SpringBootApplication
public class PedidoApplication {

    public static void main(String[] args){
        SpringApplication.run(PedidoApplication.class, args);
    }

}
