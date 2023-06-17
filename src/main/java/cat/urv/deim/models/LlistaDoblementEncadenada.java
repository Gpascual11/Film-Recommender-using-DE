package cat.urv.deim.models;

import cat.urv.deim.exceptions.ElementNoTrobat;

public class LlistaDoblementEncadenada<E extends Comparable<E>> implements ILlistaGenerica<E> {

    private Node<E> cap; //apuntador al primer node de la llista
    private Node<E> cua; //apuntador al darrer node de la llista
    private int longitud; //longitud de la llista

    //Constructor de la llista
    public LlistaDoblementEncadenada() {    
        this.cap = null;
        this.cua = null;
        this.longitud = 0;
    }

    //Mètode per insertar un element a la llista. No importa la posició on s'afegeix l'element
    public void inserir(E e) {  
        Node<E> nouNode = new Node<E>(e, null, null);   //creem un nou node amb l'element e
        if (cap == null) {  //si la llista és buida, el nou node serà el primer i l'últim
            cap = nouNode;
            cua = nouNode;
        } else {
            cua.setSeguent(nouNode);    //si la llista no és buida, el nou node serà l'últim
            nouNode.setAnterior(cua);
            cua = nouNode;
        }
        longitud++;
    }

    //Mètode per a esborrar un element de la llista
    public void esborrar(E e) throws ElementNoTrobat {
        Node<E> nodeAEsborrar = buscarNode(e);
        if (nodeAEsborrar == null) {    //si l'element no està a la llista, llançem una excepció
            throw new ElementNoTrobat("L'element no s'ha trobat a la llista");
        }
        Node<E> nodeAnterior = nodeAEsborrar.getAnterior(); //si l'element està a la llista, esborrem el node
        Node<E> nodeSeguent = nodeAEsborrar.getSeguent(); //i actualitzem els punts cap i cua
        if (nodeAnterior == null) { //si el node a esborrar és el primer de la llista
            cap = nodeSeguent;  //el nou primer node serà el següent
        } else {
            nodeAnterior.setSeguent(nodeSeguent);   //si no, el següent del node anterior serà el següent del node a esborrar
            nodeAEsborrar.setAnterior(null);    //i l'anterior del node a esborrar serà null
        }
        if (nodeSeguent == null) {  //si el node a esborrar és l'últim de la llista
            cua = nodeAnterior; //el nou darrer node serà l'anterior
        } else {
            nodeSeguent.setAnterior(nodeAnterior);  //si no, l'anterior del node següent serà l'anterior del node a esborrar
            nodeAEsborrar.setSeguent(null); //i el següent del node a esborrar serà null
        }
        longitud--; //actualitzem la longitud de la llista
    }

    //Mètode per a comprovar si un element està a la llista
    public boolean buscar(E e) {
        Node<E> node = buscarNode(e);   //busquem el node a partir de l'element
        return node != null;    //si el node no és null, l'element està a la llista
    }

    //Mètode per a comprovar si la llista té elements
    public boolean esBuida() {
        return longitud == 0;   //si la longitud és 0, la llista és buida
    }

    //Mètode per a obtenir el nombre d'elements de la llista
    public int longitud() {
        return longitud;    //retornem la longitud de la llista
    }

    //Metode per a obtenir un array amb tots els elements
    public Object[] elements() {
        Object[] arrayElements = new Object[longitud];  //creem un array d'objectes amb la longitud de la llista
        Node<E> nodeActual = cap;   //apuntador al primer node de la llista
        int i = 0;
        while (nodeActual != null) {    //recorrem tota la llista i afegim els elements a l'array
            arrayElements[i] = nodeActual.getElement(); //afegim l'element del node actual a l'array
            nodeActual = nodeActual.getSeguent();   //i actualitzem l'apuntador al següent node
            i++;
        }
        return arrayElements;   
    }

    //Mètode privat per a buscar un node a partir d'un element
    private Node<E> buscarNode(E e) {
        Node<E> node = cap; //apuntador al primer node de la llista
        while (node != null) {  //recorrem tota la llista
            if (node.getElement().compareTo(e) == 0) {  //si l'element del node actual és igual a l'element que busquem
                return node;
            }
            node = node.getSeguent();   //actualitzem l'apuntador al següent node
        }
        return null;
    }

    public Node<E> getInici() {
        return cap;
    }
}