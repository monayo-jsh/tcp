package com.exmple.tcp;

import com.exmple.tcp.client.TcpRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TcpClientApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TcpClientApplication.class, args);
        TcpRequest tcpRequest = context.getBean(TcpRequest.class);
        tcpRequest.runTcpClient();
    }

}
