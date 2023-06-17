package cat.urv.deim.models;

import java.util.List;

import cat.urv.deim.models.MultillistaIdP_IdU.RatingPelicula;
import cat.urv.deim.models.MultillistaIdP_IdU.RatingUsuari;

// Interficie per una implementacio de la multillista amb Id_Pelicules i Id_Usuaris
public interface IIdP_IdU {

    // Metode per a afegir una relacio
    public void inserir(String idPelicula, String idUsuari, int rating);

    // Metode per a esborrar una relacio
    public void esborrar(String idPelicula, String idUsuari);

    // Metode per retornar la llista de relacions amb l'element idPelicula
    public List<String> fila(String idPelicula);

    // Metode per retornar la llista de relacions amb l'element idUsuari
    public List<String> columna(String idUsuari);

    // Metode per saber si existeix una relacio entre idPelicula i idUsuari
    public boolean existeix(String idPelicula, String idUsuari); 

    // Metode per obtenir el rating de la relacio entre idPelicula i idUsuari
    public int obtenirRating(String idPelicula, String idUsuari);

    // Metode per obtenir una llista d'objectes amb l'ID d'usuari i el rating
    public List<RatingPelicula> obtenirRatingsPerPelicula(String idPelicula);

    // Metode per obtenir una llista d'objectes amb l'ID de la pelicula i el rating
    public List<RatingUsuari> obtenirRatingsPerUsuari(String idUsuari);
}

