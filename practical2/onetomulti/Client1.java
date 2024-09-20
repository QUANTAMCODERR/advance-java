import java.io.*;
import java.net.*;

class Client1 {
    private Socket socket;
    private BufferedReader br;
    private PrintWriter out;

    public Client1() {
        try {
            socket = new Socket("127.0.0.1", 7777);

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            startReading();
            startWriting();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startReading() {
        Runnable reader = () -> {
            try {
                String msg;
                while ((msg = br.readLine()) != null) {
                    System.out.println("Server: " + msg);
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client1();
    }
}
