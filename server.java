import java.io.*;
import java.net.*;

class Server {
    private ServerSocket server;
    private Socket socket;
    private BufferedReader br;
    private PrintWriter out;

    public Server() {
        try {
            server = new ServerSocket(7777);
            System.out.println("Server is ready to accept connection");
            System.out.println("Waiting....");
            socket = server.accept();
            System.out.println("Connection established.");

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);  // Auto-flush

            startReading();
            startWriting();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startReading() {
        Runnable reader = () -> {
            System.out.println("Reader Started...");
            try {
                while (true) {
                    String msg = br.readLine();
                    if (msg == null || msg.equalsIgnoreCase("exit")) {
                        System.out.println("Client terminated the chat");
                        break;
                    }
                    System.out.println("Client: " + msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                closeResources();
            }
        };
        new Thread(reader).start();
    }

    public void startWriting() {
        Runnable writer = () -> {
            System.out.println("Writer started...");
            try (BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in))) {
                while (true) {
                    String content = br1.readLine();
                    out.println(content);
                    if (content.equalsIgnoreCase("exit")) {
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        new Thread(writer).start();
    }

    private void closeResources() {
        try {
            if (br != null) br.close();
            if (out != null) out.close();
            if (socket != null) socket.close();
            if (server != null) server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("This is the server. Going to start the server.");
        new Server();
    }
}
