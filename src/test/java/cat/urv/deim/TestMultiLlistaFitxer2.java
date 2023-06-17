package cat.urv.deim;

import cat.urv.deim.models.IIdP_IdU;
import cat.urv.deim.models.MultillistaIdP_IdU;
import cat.urv.deim.io.FileLoaderMultillista;
import cat.urv.deim.models.ITitols;
import cat.urv.deim.models.HashMapTitols;
import cat.urv.deim.io.FileLoaderHashTitol;

public class TestMultiLlistaFitxer2 {

    public static void main(String[] args) {
        // Crear una instància de la multillista
        IIdP_IdU multillista = new MultillistaIdP_IdU();

        long startTime = System.currentTimeMillis(); // Moment inicial

        // Carregar les dades del fitxer a la multillista
        FileLoaderMultillista.loadRatings("movie_users_20_100.txt", multillista);

        long endTime = System.currentTimeMillis(); // Moment final
        long totalTime = endTime - startTime; // Temps d'execució en milisegons

        System.out.println("Temps d'execució total: " + totalTime + " ms");

        long startTime2 = System.currentTimeMillis(); // Moment inicial 

        // Crear i carregar el HashMap amb les parelles ID_Pelicula-Títol
        ITitols titolsPelicules = new HashMapTitols();
        FileLoaderHashTitol.carregarFitxerTitols("movie_titles.csv", titolsPelicules);

        generarProves(multillista, titolsPelicules);

        long endTime2 = System.currentTimeMillis(); // Moment final
        long totalTime2 = endTime2 - startTime2; // Temps d'execució en milisegons

        System.out.println("Temps d'execució total: " + totalTime2 + " ms");

    }

    private static void generarProves(IIdP_IdU multillista, ITitols titolsPelicules){

        String pelicula = "391";  
        String usuari = "2238863";

        // Comprovar el rating per la pel·lícula i l'usuari aleatoris
        int rating = multillista.obtenirRating(String.valueOf(pelicula), String.valueOf(usuari));
        String titol = titolsPelicules.getTitol(pelicula);
        System.out.println("Rating de la pel·lícula '" + titol + "' (ID: " + pelicula + ") per l'usuari '" + usuari + "': " + rating + ", el resultat esperat és 5");


        String pelicula2 = "1367";  
        String usuari2 = "2415537";

        // Comprovar el rating per la pel·lícula i l'usuari aleatoris
        int rating2 = multillista.obtenirRating(String.valueOf(pelicula2), String.valueOf(usuari2));
        String titol2 = titolsPelicules.getTitol(pelicula2);
        System.out.println("Rating de la pel·lícula '" + titol2 + "' (ID: " + pelicula2 + ") per l'usuari '" + usuari2 + "': " + rating2 + ", el resultat esperat és 3");

        String pelicula3 = "13820";  
        String usuari3 = "1329231";

        // Comprovar el rating per la pel·lícula i l'usuari aleatoris
        int rating3 = multillista.obtenirRating(String.valueOf(pelicula3), String.valueOf(usuari3));
        String titol3 = titolsPelicules.getTitol(pelicula3);
        System.out.println("Rating de la pel·lícula '" + titol3 + "' (ID: " + pelicula3 + ") per l'usuari '" + usuari3 + "': " + rating3 + ", el resultat esperat és 4");

        String pelicula4 = "6034";  
        String usuari4 = "1047263";

        // Comprovar el rating per la pel·lícula i l'usuari aleatoris
        int rating4 = multillista.obtenirRating(String.valueOf(pelicula4), String.valueOf(usuari4));
        String titol4 = titolsPelicules.getTitol(pelicula4);
        System.out.println("Rating de la pel·lícula '" + titol4 + "' (ID: " + pelicula4 + ") per l'usuari '" + usuari4 + "': " + rating4 + ", el resultat esperat és 1");

        String pelicula5 = "11191"; 
        String usuari5 = "2340573";

        // Comprovar el rating per la pel·lícula i l'usuari aleatoris
        int rating5 = multillista.obtenirRating(String.valueOf(pelicula5), String.valueOf(usuari5));
        String titol5 = titolsPelicules.getTitol(pelicula5);
        System.out.println("Rating de la pel·lícula '" + titol5 + "' (ID: " + pelicula5 + ") per l'usuari '" + usuari5 + "': " + rating5 + ", el resultat esperat és 2");

        String pelicula6 = "10231";
        String usuari6 = "2530638";
        // Comprovar el rating per la pel·lícula i l'usuari aleatoris
        int rating6 = multillista.obtenirRating(String.valueOf(pelicula6), String.valueOf(usuari6));
        String titol6 = titolsPelicules.getTitol(pelicula6);
        System.out.println("Rating de la pel·lícula '" + titol6 + "' (ID: " + pelicula6 + ") per l'usuari '" + usuari6 + "': " + rating6 + ", el resultat esperat és 5");
    }
}