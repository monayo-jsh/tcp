package com.exmple.tcp.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import org.springframework.stereotype.Component;

@Component
public class TcpRequest {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 65432;

    public void runTcpClient() {

        try (
            Socket socket = new Socket(HOST, PORT);
            OutputStream out = socket.getOutputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        ) {

            System.out.println("Connected to server");

            String message;
            while (true) {
                System.out.println("Enter message to send (or 'exit' to quit): ");
                message = userInput.readLine();
                if ("exit".equalsIgnoreCase(message)) {
                    break; //exit 입력 시 클라이언트 종료
                }

                String sendMessage = message + "\n";
                out.write(sendMessage.getBytes());
                out.flush();

                String response = in.readLine();
                System.out.println("Received from server: " + response);
            }

        } catch (IOException e) {
            System.out.println("IOException error with server: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception error with server: " + e.getMessage());
        }

    }
}
