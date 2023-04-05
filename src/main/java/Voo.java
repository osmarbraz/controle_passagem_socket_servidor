
import java.util.ArrayList;

/**
 * Representa o Voo.
 * 
 * @author osmar
 */
public class Voo {

    public final int NUMERO_DE_ASSENTOS = 50;
    private String codigoVoo;
    private ArrayList<Assento> assentos = new ArrayList<>();

    public Voo(String codigoVoo) {
        this.codigoVoo = codigoVoo;
        // Adicionando 50 assentos
        for (int numero = 1; numero <= NUMERO_DE_ASSENTOS; numero++) {
            Assento assento = new Assento(numero);
            assentos.add(assento);
        }
    }

    public String getCodigoVoo() {
        return codigoVoo;
    }

    public void setCodigoVoo(String codigoVoo) {
        this.codigoVoo = codigoVoo;
    }

    public ArrayList getAssentos() {
        return assentos;
    }

    public void setAssentos(ArrayList<Assento> assentos) {
        this.assentos = assentos;
    }

    public Assento procurarAssento(int numeroAssento) {
        for (Assento a : assentos) {
            if (a.getNumero() == numeroAssento) {
                return a;
            }
        }
        return null;
    }
}
