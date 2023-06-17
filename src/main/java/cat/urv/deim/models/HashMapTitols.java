package cat.urv.deim.models;

import java.util.Iterator;

import cat.urv.deim.exceptions.ElementNoTrobat;

public class HashMapTitols implements ITitols {

    private HashMapIndirecte<String, String> hashMap;

    public HashMapTitols() {
        hashMap = new HashMapIndirecte<String,String>(1000);
    }

    public void inserir(String idPelicula, String titol) {
        hashMap.inserir(idPelicula, titol);
    }

    // Metode per a esborrar un element de la taula de hash
    public void esborrar(String idPelicula) throws ElementNoTrobat {
        hashMap.esborrar(idPelicula);
    }

    // Metode per a comprovar si un element esta a la taula de hash
    public boolean buscar(String idPelicula) {
        return hashMap.buscar(idPelicula);
    }

    public String getTitol (String idPelicula) {
        return hashMap.getTitol(idPelicula);
    }

    // Metode per a comprovar si la taula te elements
    public boolean esBuida(){
        return hashMap.esBuida();
    }

    // Metode per a obtenir el nombre d'elements de la taula
    public int longitud(){
        return hashMap.longitud();
    }

    public String[] llistaTitols() {
        String[] titols = null;    //Array de pelicules    
        int aux = 0;                //Auxiliar per a recorrer l'array
    
        if (hashMap.esBuida()) {    //Si la taula esta buida
            return null;        //Retornem null
        }
        else {
            Iterator<String> iterator = hashMap.iterator();   //Creem un iterador per a recorrer la taula
            titols = new String[hashMap.longitud()];          //Creem un array de pelicules amb la mida de la taula
            while(iterator.hasNext()) { //Mentre hi hagi elements a la taula
                titols[aux] = iterator.next();   //Afegim la pelicula a l'array
                aux++;
            }
            return titols;  //Retornem l'array
        }
    }
}