package cat.urv.deim;

import cat.urv.deim.models.IIdP_IdU;
import cat.urv.deim.models.MultillistaIdP_IdU;

import java.util.ArrayList;
import java.util.List;

import cat.urv.deim.io.FileLoaderMultillista;

public class AlgoritmePelis {

    public static void main(String[] args) {
        // Crear una instància de la multillista
        IIdP_IdU multillista = new MultillistaIdP_IdU();

        // Carregar les dades del fitxer a la multillista
        FileLoaderMultillista.loadRatings("movie_users_10_20.txt", multillista);

        String idUsuari = "idUsuari";
        generarAlgoritme(multillista, idUsuari);
    }

    private static void generarAlgoritme(IIdP_IdU multillista, String idUsuari) {

    }
}
