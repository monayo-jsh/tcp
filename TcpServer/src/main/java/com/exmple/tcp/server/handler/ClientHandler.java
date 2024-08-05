package com.exmple.tcp.server.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {

        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            OutputStream out = clientSocket.getOutputStream()
        ) {

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Received: " + message);

                // 에코 응답
                String response = message + "\n";
                out.write(response.getBytes());
                out.flush();
            }

        } catch (IOException e) {
            System.out.println("IOException client: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception client: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("IOException closing client socket: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Exception closing client socket: " + e.getMessage());
            }
        }

    }
}
