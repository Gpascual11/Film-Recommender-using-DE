package cat.urv.deim.models;

import cat.urv.deim.exceptions.ElementNoTrobat;
import cat.urv.deim.exceptions.ElementRepetit;

import java.util.ArrayList;
import java.util.List;

public class MultillistaIdP_IdU implements IIdP_IdU {

    private MultiLlista<String, String> multillista;

    public MultillistaIdP_IdU() {
        multillista = new MultiLlista<String, String>();
    }

    public void inserir(String idPelicula, String idUsuari, int rating) {
        try {
            multillista.inserir(idPelicula, idUsuari, rating);
        } catch (ElementRepetit e) {

        }
    }

    public void esborrar(String idPelicula, String idUsuari) {
        try {
            multillista.esborrar(idPelicula, idUsuari);
        } catch (ElementNoTrobat e) {

        }
    }

    public List<String> fila(String idPelicula) {
        try {
            return multillista.fila(idPelicula);
        } catch (ElementNoTrobat e) {
            return null; 
        }
    }

    public List<String> columna(String idUsuari) {
        try {
            return multillista.columna(idUsuari);
        } catch (ElementNoTrobat e) {
            return null; 
        }
    }

    public boolean existeix(String idPelicula, String idUsuari) {
        return multillista.existeix(idPelicula, idUsuari);
    }

    public int obtenirRating(String idPelicula, String idUsuari) {
        try {
            return multillista.obtenirRating(idPelicula, idUsuari);
        } catch (ElementNoTrobat e) {
            return 0; 
        }
    }

    // Nou mètode per obtenir una llista d'objectes amb l'ID d'usuari i el rating
    public List<RatingPelicula> obtenirRatingsPerPelicula(String idPelicula) {
        List<RatingPelicula> ratings = new ArrayList<>();
        List<String> fila = fila(idPelicula);

        for (String idUsuari : fila) {
            int rating = obtenirRating(idPelicula, idUsuari);
            ratings.add(new RatingPelicula(idUsuari, rating));
        }

        return ratings;
    }

    // Nou mètode per obtenir una llista d'objectes amb l'ID de pel·lícula i el rating
    public List<RatingUsuari> obtenirRatingsPerUsuari(String idUsuari) {
        List<RatingUsuari> ratings = new ArrayList<>();
        List<String> columna = columna(idUsuari);

        for (String idPelicula : columna) {
            int rating = obtenirRating(idPelicula, idUsuari);
            ratings.add(new RatingUsuari(idPelicula, rating));
        }

        return ratings;
    }

    // Classe auxiliar per emmagatzemar l'ID d'usuari i el rating d'una pel·lícula
    public static class RatingPelicula {
        private String idUsuari;
        private int rating;

        public RatingPelicula(String idUsuari, int rating) {
            this.idUsuari = idUsuari;
            this.rating = rating;
        }

        public String getIdUsuari() {
            return idUsuari;
        }

        public int getRating() {
            return rating;
        }
    }

    // Classe auxiliar per emmagatzemar l'ID de pel·lícula i el rating d'un usuari
    public static class RatingUsuari {
        private String idPelicula;
        private int rating;

        public RatingUsuari(String idPelicula, int rating) {
            this.idPelicula = idPelicula;
            this.rating = rating;
        }

        public String getIdPelicula() {
            return idPelicula;
        }

        public int getRating() {
            return rating;
        }
    }
}