package com.exmple.tcp;

import com.exmple.tcp.server.GatewayServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TcpServerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TcpServerApplication.class, args);
        GatewayServer gatewayServer = context.getBean(GatewayServer.class);
        gatewayServer.runGatewayServer();
    }

}
