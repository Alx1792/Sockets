import java.io.*;  // Classes per entrada/sortida
import java.net.*; // Classes per xarxes

public class Client {
    public static void main(String[] args) {
        try {
            Socket connexio = new Socket("127.0.0.1", 5000);
            System.out.println("Connectat al servidor");

            PrintWriter escriptor = new PrintWriter(
                    connexio.getOutputStream(), true);

            escriptor.println("Hola patatilles");
            System.out.println("Missatge enviat: 'Me encante que te encanti entrar al servidor'");

            BufferedReader lector = new BufferedReader(new InputStreamReader(connexio.getInputStream()));

            String resposta = lector.readLine();
            System.out.println("Servidor respon: '" + resposta + "'");

            connexio.close();
            System.out.println("Connexió tancada.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
