package cat.urv.deim;

import cat.urv.deim.io.FileLoaderHashTitol;
import cat.urv.deim.models.HashMapTitols;
import cat.urv.deim.models.ITitols;

import java.util.Random;

public class TestHashTitols {

    public static void main(String[] args) {
        ITitols titols = new HashMapTitols();
        FileLoaderHashTitol.carregarFitxerTitols("movie_titles.csv", titols);
        mostrarInfo(titols);
    }

    public static void mostrarInfo(ITitols llista) {
        System.out.println("S'han carregat " + llista.longitud() + " titols");

        // Generar ID aleatori
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            String randomId = String.valueOf(random.nextInt(10000));

            String titol = llista.getTitol(randomId);
            if (titol != null) {
                System.out.println("Títol amb ID " + randomId + ": " + titol);
            } else {
                System.out.println("No s'ha trobat cap pel·lícula amb l'ID " + randomId);
            }
        }
    }

}




