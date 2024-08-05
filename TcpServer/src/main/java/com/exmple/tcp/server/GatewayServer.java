package com.exmple.tcp.server;

import com.exmple.tcp.server.handler.ClientHandler;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.stereotype.Component;

@Component
public class GatewayServer {

    private static final int PORT = 65432;

    public void runGatewayServer() {

        int cpuCores = Runtime.getRuntime().availableProcessors();
        int threadPoolSize = cpuCores * 2; // 일반적으로 코어 수의 2배를 권장

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server listening on port: " + PORT);
            System.out.println("Using Thread Pool Size: " + threadPoolSize);

            // 풀 생성
            ExecutorService threadPool = Executors.newFixedThreadPool(threadPoolSize);

            while(true) {

                try {
                    // 클라이언트의 연결 요청을 수락
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Connected to client at "+ clientSocket.getRemoteSocketAddress());

//                    threadPool.execute(new ClientHandler(clientSocket));
                    new ClientHandler(clientSocket).run();
                } catch (IOException e) {
                    System.out.println("Error accepting client connection: " + e.getMessage());
                }

            }

        } catch (IOException e) {
            System.out.println("Error starting server: " + e.getMessage());
        }

    }

}