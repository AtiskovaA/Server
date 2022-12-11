
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.io.InputStreamReader;


public class Client {
    public static void main(String[] args) {
        try (
                Socket socket = new Socket("localhost", 9135);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                ) {
            //System.out.println("");
            String request = in.readLine();
            System.out.println("Предыдущий город: " + request);

            System.out.println("Введите город: ");
            Scanner scanner = new Scanner(System.in);
            String city = scanner.nextLine();
            out.println(city);

            out.println(scanner.nextLine());
            String requestTwo = in.readLine();
            System.out.println(requestTwo);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


