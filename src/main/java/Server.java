import jdk.internal.org.jline.utils.InputStreamReader;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;


public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(9135);) {
            String city = null;
            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream());
                ) {
                    System.out.println("Start");
                    String word = in.readLine();
                    System.out.println(word);

                    String stringFromSocket = in.readLine();
                    if (city == null) {
                        out.println("???");
                        city = stringFromSocket;
                        out.println("OK");
                    } else {
                        if (city.toLowerCase().endsWith(stringFromSocket.toLowerCase().substring(0, 1))) {
                            city = stringFromSocket;
                            out.println("OK");
                            System.out.println("Ответ: " + city);
                        } else {
                            out.println("NOT OK");
                            System.out.println("Не правильный ответ: " + stringFromSocket);
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
