package cat.urv.deim.models;

import java.util.List;
import java.util.ArrayList;
import cat.urv.deim.exceptions.ElementNoTrobat;
import cat.urv.deim.exceptions.ElementRepetit;

public class MultiLlista<A extends Comparable<A>, B extends Comparable<B>> implements IMultiLlistaGenerica<A,B> {

    private final int mida = 100;    // Mida de la taula de hash

    private HashMapIndirecte<A,MNode<A,B>> taulaA; // Taula de hash A
    private HashMapIndirecte<B,MNode<B,A>> taulaB; // Taula de hash B

    public MultiLlista() {
        taulaA = new HashMapIndirecte<A,MNode<A,B>>(mida); // Inicialitzem la taula de hash A
        taulaB = new HashMapIndirecte<B,MNode<B,A>>(mida); // Inicialitzem la taula de hash B
    }

    @Override
    public void inserir(A a, B b, int rating) throws ElementRepetit {

        MNode<A,B> nodeA = new MNode<>(a, b, rating); // Crea el node A
        MNode<B,A> nodeB = new MNode<>(b, a, rating); // Crea el node B

        if (existeix(a, b)) {
            throw new ElementRepetit("La relació entre " + a + " i " + b + " ja existeix"); // Llença una excepció si la relació ja existeix
        }

        try {
            MNode<A,B> primerNodeA = taulaA.obtenirValor(a); // Obtenim el primer node de la llista de hash A
            nodeA.setNext(primerNodeA);  // Afegeix el nou node al principi de la llista
            if (primerNodeA != null) { // Si el primer node no és null, actualitza la seva referència previa
                primerNodeA.setPrev(nodeA); // Actualitza la referència del primer node de la llista de hash A
            }
            taulaA.inserir(a, nodeA); // Actualitza la referència del primer node de la llista de hash A
        } catch (ElementNoTrobat e) {
            taulaA.inserir(a, nodeA); 
        }

        try {
            MNode<B,A> primerNodeB = taulaB.obtenirValor(b); // Obtenim el primer node de la llista de hash B
            nodeB.setOtherNext(primerNodeB);  // Afegeix el nou node al principi de la llista
            if (primerNodeB != null) { // Si el primer node no és null, actualitza la seva referència previa
                primerNodeB.setOtherPrev(nodeB); // Actualitza la referència del primer node de la llista de hash B
            }
            taulaB.inserir(b, nodeB); // Actualitza la referència del primer node de la llista de hash B
        } catch (ElementNoTrobat e) {
            taulaB.inserir(b, nodeB);
        }
    }

    @Override
    public void esborrar(A a, B b) throws ElementNoTrobat {
    try {
        MNode<A, B> nodeA = taulaA.obtenirValor(a); // Obtenim el primer node de la llista de hash A

        boolean relacioExisteix = false; // Variable per a comprovar si la relació existeix
        while (nodeA != null) { // Recorrem la llista de hash A
            if (nodeA.getVal().equals(a) && nodeA.getOtherVal().equals(b)) { // Si la relació existeix
                relacioExisteix = true;
                break; // Sortim del bucle
            }
            nodeA = nodeA.getNext(); // Obtenim el següent node
        }

        if (relacioExisteix) { // Si la relació existeix
            // Esborrar de HashA
            if (nodeA.getPrev() != null) { // Si el node anterior no és null
                nodeA.getPrev().setNext(nodeA.getNext()); // Actualitza la referència del node anterior
            } else {
                taulaA.inserir(a, nodeA.getNext()); // Actualitza la referència del primer node de la llista de hash A
            }

            if (nodeA.getNext() != null) {
                nodeA.getNext().setPrev(nodeA.getPrev());
            }

            // Recorre la llista de hash B per esborrar la referència inversa
            MNode<B, A> nodeB = taulaB.obtenirValor(b);
            while (nodeB != null) {
                if (nodeB.getOtherVal().equals(a)) { // Troba el nodeB que apunta a 'a'
                    if (nodeB.getOtherPrev() != null) {
                        nodeB.getOtherPrev().setOtherNext(nodeB.getOtherNext());
                    } else {
                        taulaB.inserir(b, nodeB.getOtherNext());
                    }

                    if (nodeB.getOtherNext() != null) {
                        nodeB.getOtherNext().setOtherPrev(nodeB.getOtherPrev());
                    }
                    break;
                }
                nodeB = nodeB.getOtherNext();
            }
        } else {
            throw new ElementNoTrobat("No s'ha trobat la relació a esborrar entre " + a + " i " + b); // Llença una excepció si la relació no existeix
        }
    } catch (ElementNoTrobat e) {
        throw new ElementNoTrobat("No s'ha trobat la relació a esborrar entre " + a + " i " + b); // Llença una excepció si la relació no existeix
    }
}


    @Override
    public List<B> fila(A a) throws ElementNoTrobat {
        try {
            MNode<A, B> nodeA = taulaA.obtenirValor(a); // Obtenim el primer node de la llista de hash A
            List<B> fila = new ArrayList<B>(estimacioTamany(nodeA)); // Crea una llista de B amb capacitat inicial estimada
            while (nodeA != null) {
                fila.add(nodeA.getOtherVal()); // Afegeix el valor de B a la llista
                nodeA = nodeA.getNext(); // Obtenim el següent node
            }
            return fila;
        } catch (ElementNoTrobat e) {
            throw new ElementNoTrobat("No s'ha trobat l'element " + a);
        }
    }

    @Override
    public List<A> columna(B b) throws ElementNoTrobat {
        try {
            MNode<B, A> nodeB = taulaB.obtenirValor(b); // Obtenim el primer node de la llista de hash B
            List<A> columna = new ArrayList<A>(estimacioTamany(nodeB)); // Crea una llista de A amb capacitat inicial estimada
            while (nodeB != null) {
                columna.add(nodeB.getOtherVal()); // Afegeix el valor de A a la llista
                nodeB = nodeB.getOtherNext(); // Obtenim el següent node
            }
            return columna;
        } catch (ElementNoTrobat e) {
            throw new ElementNoTrobat("No s'ha trobat l'element " + b);
        }
    }

    private int estimacioTamany(MNode<?, ?> node) { // Estima el tamany de la llista, Node<?, ?> node = Node<A, B> o Node<B, A>
        int tamanyEstimat = 0;
        while (node != null) {
            tamanyEstimat++;
            node = node.getNext();
        }
        return tamanyEstimat;
    }

    @Override
    public boolean existeix(A a, B b) {

        try {
            MNode<A, B> nodeA = taulaA.obtenirValor(a); // Obtenim el primer node de la llista de hash A

            while (nodeA != null) {
                if (nodeA.getVal().equals(a) && nodeA.getOtherVal().equals(b)) { // Si la relació existeix
                    return true;
                }
                nodeA = nodeA.getNext(); // Obtenim el següent node
            }
            return false;
        } catch (ElementNoTrobat e) {
            return false;
        }
    }

    @Override
    public int obtenirRating(A a, B b) throws ElementNoTrobat{
        try {
            MNode<A, B> nodeA = taulaA.obtenirValor(a); // Obtenim el primer node de la llista de hash A

            while (nodeA != null) {
                if (nodeA.getVal().equals(a) && nodeA.getOtherVal().equals(b)) { // Si la relació existeix
                    return nodeA.getRating();
                }
                nodeA = nodeA.getNext(); // Obtenim el següent node
            }
            throw new ElementNoTrobat("No s'ha trobat la relació entre " + a + " i " + b);
        } catch (ElementNoTrobat e) {
            throw new ElementNoTrobat("No s'ha trobat la relació entre " + a + " i " + b);
        }
    }


}