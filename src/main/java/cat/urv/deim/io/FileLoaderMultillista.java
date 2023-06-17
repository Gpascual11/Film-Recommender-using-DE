package cat.urv.deim.io;

import cat.urv.deim.models.IIdP_IdU;
import cat.urv.deim.models.MultillistaIdP_IdU;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileLoaderMultillista {
    public static void loadRatings(String filePath, IIdP_IdU multillista) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String idPelicula = null;

            while ((line = br.readLine()) != null) {
                if (line.endsWith(":")) {
                    // Línia amb Id_Pelicula
                    idPelicula = line.substring(0, line.length() - 1);
                } else {
                    // Línia amb dades d'usuari i rating
                    String[] parts = line.split(",");
                    String idUsuari = parts[0];
                    int rating = Integer.parseInt(parts[1]);

                    // Inserir el rating a la multillista
                    multillista.inserir(idPelicula, idUsuari, rating);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        IIdP_IdU multillista = new MultillistaIdP_IdU();
        FileLoaderMultillista.loadRatings("movie_users_10_20.txt", multillista);
    }
}
