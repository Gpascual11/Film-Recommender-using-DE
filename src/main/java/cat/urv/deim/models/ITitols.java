package cat.urv.deim.models;

import cat.urv.deim.exceptions.ElementNoTrobat;

public interface ITitols {

    // Metode per a afegir un titol
    public void inserir(String idPelicula, String titol);

    // Metode per a esborrar un titol
    public void esborrar(String idPelicula) throws ElementNoTrobat;

    // Metode per a saber si un titol esta guardat
    public boolean buscar(String titol);

    // Metode per a obtenir el titol d'una pelicula
    public String getTitol (String idPelicula);

    // Metode per saber si hi ha alguna pelicula guardada a l'estructura
    public boolean esBuida();

    // Metode per saber quantes pelicules hi ha emmagatzemades
    public int longitud();

    // Funcio que retorna totes els titols de l'estructura
    public String[] llistaTitols();

}