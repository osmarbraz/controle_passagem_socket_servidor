
/**
 * Programa principal.
 * 
 * @author osmar
 */
public class Principal {

    public static void main(String[] args) {
        try {
            ServidorSocket servidor = new ServidorSocket();
            servidor.rodarServidor();
        } catch (Exception e) {
            System.out.println("Exceção:" + e);
        }
    }
}
