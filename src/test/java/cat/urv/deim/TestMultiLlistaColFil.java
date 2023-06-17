package cat.urv.deim;

import java.util.List;

import cat.urv.deim.io.FileLoaderMultillista;
import cat.urv.deim.models.IIdP_IdU;
import cat.urv.deim.models.MultillistaIdP_IdU;

public class TestMultiLlistaColFil {
    public static void main(String[] args) {
        // Crear una instància de la multillista
        IIdP_IdU multillista = new MultillistaIdP_IdU();

        // Carregar les dades de les valoracions des d'un fitxer
        FileLoaderMultillista.loadRatings("movie_users_10_20.txt", multillista);

        // Realitzar proves
        testObtenirFilaIColumna(multillista);
        testObtenirRating(multillista);
    }


    private static void testObtenirFilaIColumna(IIdP_IdU multillista) {
        // Obtindre la fila per a un ID de pel·lícula
        System.out.println("Dades de la fila '28':");
        List<MultillistaIdP_IdU.RatingPelicula> ratingsPelicula = multillista.obtenirRatingsPerPelicula("28");
        for (MultillistaIdP_IdU.RatingPelicula ratingPelicula : ratingsPelicula) {
            System.out.println("Usuari: " + ratingPelicula.getIdUsuari() + ", Rating: " + ratingPelicula.getRating());
        }

        // Obtindre la columna per a un ID d'usuari
        System.out.println("Dades de la columna '1343191':");
        List<MultillistaIdP_IdU.RatingUsuari> ratingsUsuari = multillista.obtenirRatingsPerUsuari("2165002");
        for (MultillistaIdP_IdU.RatingUsuari ratingUsuari : ratingsUsuari) {
            System.out.println("Pel·lícula: " + ratingUsuari.getIdPelicula() + ", Rating: " + ratingUsuari.getRating());
        }
    }

    private static void testObtenirRating(IIdP_IdU multillista) {
        // Obtindre la valoració per a una pel·lícula i un usuari específics
        String idPelicula = "28";
        String idUsuari = "2165002";
        int rating = multillista.obtenirRating(idPelicula, idUsuari);

        // Imprimir resultat
        System.out.println("Valoració de la pel·lícula '" + idPelicula + "' per l'usuari '" + idUsuari + "': " + rating);
    }
}
