
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Servidor socket.
 *
 * @author osmar
 */
public class ServidorSocket {

    ControladorVoo controlador = new ControladorVoo();

    /**
     * Calcula o código do status do voo.
     *
     * @param textoRecebido
     * @return
     */
    public int calcularCodigoStatus(String textoRecebido) {
        String[] processoEcodigoEAssento = textoRecebido.split(";");
        if (processoEcodigoEAssento.length != 0) {
            String processo = processoEcodigoEAssento[0].trim();
            String voo = processoEcodigoEAssento[1].trim();
            int assento = Integer.parseInt(processoEcodigoEAssento[2].trim());
            if (processo.equals("C")) {
                return controlador.verificarStatus(voo, assento);
            } else {
                if (processo.equals("M")) {
                    return controlador.marcarVoo(voo, assento);
                } else {
                    return 0;
                }
            }
        } else {
            return 0;
        }
    }

    public void rodarServidor() {
        try {
            //Abre o servidor na porta especificada
            ServerSocket serversocket = new ServerSocket(4444);
            System.out.println("Servidor iniciado!");

            //Laço para tratar as requisições
            String textoRecebido = "";
            
            //Enquanto texto recebido diferente de quit
            while (!textoRecebido.equalsIgnoreCase("quit")) {
                //if (aceitarConexaoCliente()) {
                Socket socketClient = serversocket.accept(); // fase de conexao

                //Cria o fluxo de saida para o socket
                PrintWriter out = new PrintWriter(socketClient.getOutputStream(), true);
                //Cria o fluxo de entrada para o socket
                BufferedReader in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));

                //textoRecebido = Conexao.receber(socketClient);
                // Receber mensagem do cliente
                textoRecebido = in.readLine();
                System.out.println("Texto Recebido:" + textoRecebido);
                        
                //Processa solicitação do cliente diferente de quit
                if (!textoRecebido.equalsIgnoreCase("quit")) {

                    //Processa o texto recebido
                    int codigoStatus = calcularCodigoStatus(textoRecebido);

                    //Envia a mensagem ao cliente
                    out.println(Integer.toString(codigoStatus));
                    out.flush();
                }
                //Fecha a conexão
                socketClient.close();                
            }
        } catch (IOException ioe) {
            System.out.println("Excecao: " + ioe.getMessage());
        }
        System.out.println("Servidor finalizado!");
    }
}
