import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        int port = 5000;//Es crear el port
        try {
            ServerSocket servidor = new ServerSocket(port);// Es crear el port a partir del server pocket
            System.out.println("Fent temps a que un client es conecti al servidor " + port );

            while (true) { //el bucle per fer un multiclient i que accepti clients sense parar
                Socket connexioClient = servidor.accept();
                System.out.println("Client connectat: " + connexioClient.getInetAddress());

                Clients c = new Clients(connexioClient); //Crea el fil amb el socket
                c.start();//executa el fil
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Clients extends Thread {
    private Socket connexioClient;

    public Clients(Socket connexioClient) {
        this.connexioClient = connexioClient;
    }

    @Override
    public void run() {
        try {
            BufferedReader lectorEntrada = new BufferedReader(
                    new InputStreamReader(connexioClient.getInputStream()));
            PrintWriter escriptorSortida = new PrintWriter(connexioClient.getOutputStream(), true);

            String missatge = lectorEntrada.readLine();
            System.out.println("Client " + connexioClient.getInetAddress() + " diu: " + missatge);
            escriptorSortida.println("Hola des del servidor! (IP: " + connexioClient.getInetAddress() + ")");

            connexioClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

