package cat.urv.deim;

import cat.urv.deim.models.IIdP_IdU;
import cat.urv.deim.models.MultillistaIdP_IdU;
import cat.urv.deim.io.FileLoaderMultillista;

public class TestMultiLllistaMeu {

    public static void main(String[] args) {
        // Crear una instància de la multillista
        IIdP_IdU multillista = new MultillistaIdP_IdU();

        // Carregar les dades de les valoracions des d'un fitxer
        FileLoaderMultillista.loadRatings("movie_users_10_20.txt", multillista);

        // Realitzar proves
        testExisteixComplet(multillista);
        testObtenirFilaIColumna(multillista);
        testObtenirRating(multillista);
        testObtenirRatingsPerPelicula(multillista);
        testObtenirRatingsPerUsuari(multillista);
    }

    private static void testExisteixComplet(IIdP_IdU multillista) {

        // Comprovar si existeixen les relacions
        boolean existeixRelacio1 = multillista.existeix("7502", "1343191");
        boolean existeixRelacio2 = multillista.existeix("8006", "634299");
        boolean existeixRelacio3 = multillista.existeix("10532", "2087760");
        boolean existeixRelacio4 = multillista.existeix("11499", "1557684");
        boolean existeixRelacio5 = multillista.existeix("14305", "1273697");
        boolean existeixRelacio6 = multillista.existeix("8833", "194091");
        boolean existeixRelacio7 = multillista.existeix("16477", "1784944");
        boolean existeixRelacio8 = multillista.existeix("16553", "2131419");
        boolean existeixRelacio9 = multillista.existeix("16221", "838702");
        boolean existeixRelacio10 = multillista.existeix("4109", "526950");

        // Comprovar el rating de les relacions
        int rating1 = multillista.obtenirRating("7502", "1343191");
        int rating2 = multillista.obtenirRating("8006", "634299");
        int rating3 = multillista.obtenirRating("10532", "2087760");
        int rating4 = multillista.obtenirRating("11499", "1557684");
        int rating5 = multillista.obtenirRating("14305", "1273697");
        int rating6 = multillista.obtenirRating("8833", "194091");
        int rating7 = multillista.obtenirRating("16477", "1784944");
        int rating8 = multillista.obtenirRating("16553", "2131419");
        int rating9 = multillista.obtenirRating("16221", "838702");
        int rating10 = multillista.obtenirRating("4109", "526950");

        // Imprimir resultats
        System.out.println("Existeix relació 1: " + existeixRelacio1 + ", Rating: " + rating1 + ", Esperat: " + (rating1 == 2));
        System.out.println("Existeix relació 2: " + existeixRelacio2 + ", Rating: " + rating2 + ", Esperat: " + (rating2 == 3));
        System.out.println("Existeix relació 3: " + existeixRelacio3 + ", Rating: " + rating3 + ", Esperat: " + (rating3 == 3));
        System.out.println("Existeix relació 4: " + existeixRelacio4 + ", Rating: " + rating4 + ", Esperat: " + (rating4 == 5));
        System.out.println("Existeix relació 5: " + existeixRelacio5 + ", Rating: " + rating5 + ", Esperat: " + (rating5 == 1));
        System.out.println("Existeix relació 6: " + existeixRelacio6 + ", Rating: " + rating6 + ", Esperat: " + (rating6 == 4));
        System.out.println("Existeix relació 7: " + existeixRelacio7 + ", Rating: " + rating7 + ", Esperat: " + (rating7 == 5));
        System.out.println("Existeix relació 8: " + existeixRelacio8 + ", Rating: " + rating8 + ", Esperat: " + (rating8 == 2));
        System.out.println("Existeix relació 9: " + existeixRelacio9 + ", Rating: " + rating9 + ", Esperat: " + (rating9 == 5));
        System.out.println("Existeix relació 10: " + existeixRelacio10 + ", Rating: " + rating10 + ", Esperat: " + (rating10 == 2));
    }

    private static void testObtenirFilaIColumna(IIdP_IdU multillista) {
        // Obtindre la fila per a un ID de pel·lícula
        System.out.println("Dades de la fila '7502':");
        multillista.fila("7502").forEach(System.out::println);

        // Obtindre la columna per a un ID d'usuari
        System.out.println("Dades de la columna '1343191':");
        multillista.columna("1343191").forEach(System.out::println);
    }

    private static void testObtenirRating(IIdP_IdU multillista) {
        // Obtindre la valoració per a una pel·lícula i un usuari específics
        String idPelicula = "7502";
        String idUsuari = "1343191";
        int rating = multillista.obtenirRating(idPelicula, idUsuari);

        // Imprimir resultat
        System.out.println("Valoració de la pel·lícula '" + idPelicula + "' per l'usuari '" + idUsuari + "': " + rating);
    }

    private static void testObtenirRatingsPerPelicula(IIdP_IdU multillista) {
        // Obtindre els ratings per a una pel·lícula específica
        String idPelicula = "7502";
        System.out.println("Ratings per a la pel·lícula '" + idPelicula + "':");
        multillista.obtenirRatingsPerPelicula(idPelicula).forEach(rating ->
                System.out.println("Usuari: " + rating.getIdUsuari() + ", Rating: " + rating.getRating()));
    }

    private static void testObtenirRatingsPerUsuari(IIdP_IdU multillista) {
        // Obtindre els ratings per a un usuari específic
        String idUsuari = "1343191";
        System.out.println("Ratings per a l'usuari '" + idUsuari + "':");
        multillista.obtenirRatingsPerUsuari(idUsuari).forEach(rating ->
                System.out.println("Pel·lícula: " + rating.getIdPelicula() + ", Rating: " + rating.getRating()));
    }
}