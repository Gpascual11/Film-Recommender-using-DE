package cat.urv.deim.models;

import java.util.Iterator;
import java.util.NoSuchElementException;

import cat.urv.deim.exceptions.ElementNoTrobat;
 
@SuppressWarnings("unchecked")
public class HashMapIndirecte<K,V> implements IHashMap<K,V> {

    private int mida;   // Mida de la taula de hash
    private int numElements; // Nombre d'elements de la taula de hash
    private HNode<K, V>[] taula; // Taula de hash

    // Constructor de la classe
    public HashMapIndirecte(int mida) {
        this.mida = mida;
        this.numElements = 0;
        this.taula = new HNode[mida];
    }

    public HNode<K, V>[] getTaulaHash() {
        return taula;
    }

    // Metode per a inserir un element a la taula de hash
    @Override
    public void inserir(K key, V value) {
        int index = Math.abs(key.hashCode() % mida);    // Obtenim l'index de la taula
        HNode<K, V> actNode = taula[index]; // Obtenim el node actual
        HNode<K, V> antNode =  null;  // Obtenim el node anterior
        boolean keyFound = false;   // Variable per a comprovar si el node ja existeix

        while (actNode != null && !keyFound) {   // Recorrem la llista
            if (actNode.getKey().equals((Object) key)) {   // Si el node ja existeix
                actNode.setValue(value);    // Actualitzem el valor
                keyFound = true;    // Indiquem que el node ja existeix
            }
            antNode = actNode;  // Obtenim el node anterior
            actNode = actNode.getNext();    // Obtenim el node actual
        }
            if (!keyFound) {    // Si el node no existeix
                HNode<K, V> newNode = new HNode<K, V>(key, value); // Creem un nou node
                if (antNode == null) {  // Si el node anterior es null
                    taula[index] = newNode; // Inserim el nou node
                }
                else {  // Si el node anterior no es null
                    antNode.setNext(newNode);   // Inserim el nou node
                }
                numElements++;  // Incrementem el nombre d'elements de la taula
            }

        if (this.factorCarrega() > 0.75) {  // Si el factor de carrega es major que 0.75
            this.redimensionar();   // Redimensionem la taula de hash
        }
    }

    // Metode per a esborrar un element de la taula de hash
    @Override
    public void esborrar(K key) throws ElementNoTrobat{
        int index = Math.abs(key.hashCode() % mida);    // Obtenim l'index de la taula
        HNode<K,V> actNode = taula[index];  // Obtenim el node actual
        HNode<K,V> prevNode = null;    // Obtenim el node anterior
 
       while (actNode != null) {
            if(actNode.getKey().equals(key)) {
                if(prevNode == null) {
                    taula[index] = actNode.getNext();
                } else {
                    prevNode.setNext(actNode.getNext());
                }
                numElements--;
                return;
            } else {
                prevNode = actNode;
                actNode = actNode.getNext();
            }
       }

        throw new ElementNoTrobat("No s'ha trobat l'element");  // Mostrem un missatge d'error

    }

    // Metode per a comprovar si un element esta a la taula de hash
    @Override
    public boolean buscar(K key){   // Metode per a comprovar si un element esta a la taula de hash
        int index = Math.abs(key.hashCode() % mida);    // Obtenim l'index de la taula
        HNode<K, V> node = taula[index];  // Obtenim el node actual
    
        while (node != null) {   // Recorrem la llista
            if (node.getKey().equals(key)) {   // Si el node existeix
                return true;  // Indiquem que el node existeix
            }
            node = node.getNext();  // Obtenim el node actual
        }
        return false;  // Retornem si el node existeix o no
    }

    // Metode per a comprovar si la taula te elements
    @Override
    public boolean esBuida(){
        return numElements == 0;
    }

    // Metode per a obtenir el nombre d'elements de la taula
    @Override
    public int longitud(){
        return numElements;
    }

    //Metode per a poder iterar pels elements de la taula
    @Override
    public Iterator<V> iterator(){
        return new HashMapIterator<>(taula, mida);
    }


    // Metode per a obtenir les claus de la taula
    @Override
    public Object[] obtenirClaus(){
        Object[] claus = new Object[numElements];    // Creem un array amb la mida del nombre d'elements de la taula
        int index = 0;  // Inicialitzem l'index

        for (int i=0; i < mida; i++) {
            HNode<K, V> node = taula[i];    // Obtenim el node actual
            while (node != null) {  // Recorrem la llista
                claus[index++] = node.getKey(); // Inserim la clau a l'array
                node = node.getNext();  // Obtenim el node actual
            }
        }

        return claus;   // Retornem l'array amb les claus
    }

    // Metode per a obtenir un array amb tots els elements de K
    @Override
    public V element(K key) throws ElementNoTrobat{
        int index = Math.abs(key.hashCode() % mida);    // Obtenim l'index de la taula
        HNode<K, V> node = taula[index];    // Obtenim el node actual

        while(node != null) {   // Recorrem la llista
            if(node.getKey().equals(key)) { // Si el node existeix
                return node.getValue(); // Retornem el valor del node
            }
            node = node.getNext();  // Obtenim el node actual
        }

        throw new ElementNoTrobat("No s'ha trobat l'element");  // Mostrem un missatge d'error
    }

    // Metode per a saber el factor de carrega actual de la taula
    @Override
    public float factorCarrega(){
        return (float) numElements / (float) mida;  // Retornem el factor de carrega
    }

    // Metode per a saber la mida actual de la taula
    @Override
    public int midaTaula(){
        return mida;
    }   

    public void redimensionar() {
        int novaMida = mida * 2;    // Obtenim la nova mida de la taula
        HNode<K, V>[] novaTaula = new HNode[novaMida]; // Creem una nova taula de hash

        for(int i = 0; i < mida; i++) {
            HNode<K, V> node = taula[i];    // Obtenim el node actual
            while(node != null) {   // Recorrem la llista
                int index = Math.abs(node.getKey().hashCode() % novaMida); // Obtenim l'index de la taula
                HNode<K, V> next = node.getNext();  // Obtenim el seguent node
                node.setNext(novaTaula[index]); // Inserim el node a la nova taula
                novaTaula[index] = node;    // Inserim el node a la nova taula
                node = next;    // Obtenim el seguent node
            }
        }

        taula = novaTaula;  // Actualitzem la taula de hash
        mida = novaMida;    // Actualitzem la mida de la taula
    }

    public V obtenirValor(K key) throws ElementNoTrobat {
        int index = Math.abs(key.hashCode() % mida);    // Obtenim l'index de la taula
        HNode<K, V> node = taula[index];    // Obtenim el node actual

        while (node != null) {
            if (node.getKey().equals(key)) {   // Si el node existeix
                return node.getValue(); // Retornem el valor del node
            }
            node = node.getNext();  // Obtenim el node actual
        }
        
        throw new ElementNoTrobat("No s'ha trobat l'element");  // Mostrem un missatge d'error
    }

    @SuppressWarnings("hiding")
    private class HashMapIterator<K, V> implements Iterator<V> {
        private HNode<K, V>[] taula;    // Taula de hash
        private int mida;  // Mida de la taula
        private int index;      // Indica la posicio actual de l'iterador
        private HNode<K, V> actNode;  // Node actual

        public HashMapIterator(HNode<K, V>[] taula, int mida) {  // Constructor de la classe
            this.taula = taula; // Guardem la taula
            this.mida = mida;   // Guardem la mida
            this.index = 0;     // Inicialitzem l'index
            this.actNode = null;    // Inicialitzem el node actual
        }

        @Override
        public boolean hasNext() {  // Metode per a comprovar si hi ha un seguent element
            while (actNode == null) {   // Recorrem la taula
                if (index == mida) {    // Si l'index es igual a la mida
                    return false;   // Retornem false
                }
                actNode = taula[index++];   // Obtenim el node actual
            }
            return true;    // Retornem true
        }

        public V next() {   // Metode per a obtenir el seguent element
            if (!hasNext()) {
                throw new NoSuchElementException("No next element found");  // Mostrem un missatge d'error
            }
            V value = actNode.getValue();   // Obtenim el valor del node actual
            actNode = actNode.getNext();    // Obtenim el seguent node
            return value;   // Retornem el valor del node actual
        }	
    }

    public String getTitol(String idPelicula) {
    for (HNode<K, V> node : taula) {   // Recorrem tots els nodes de l'array
        while (node != null) {   // Recorrem la llista de nodes
            if (node.getKey().equals(idPelicula)) {   // Si l'ID de la pel·lícula coincideix
                return (String) node.getValue();   // Retornem el títol corresponent
            }
            node = node.getNext();   // Obtenim el següent node
        }
    }
    return null;   // Si no es troba cap títol per l'ID de la pel·lícula, retornem null
}
}