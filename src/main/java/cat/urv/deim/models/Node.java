package cat.urv.deim.models;

public class Node<E> {  
    private E element;   
    private Node<E> seguent;    
    private Node<E> anterior;

    public Node(E element, Node<E> seguent, Node<E> anterior) {
        this.element = element; // Element que conté el node
        this.seguent = seguent; // Referència al següent node
        this.anterior = anterior;   // Referència al node anterior
    }

    public E getElement() {
        return element; // Retorna l'element del node
    }

    public void setElement(E element) {
        this.element = element; // Estableix l'element del node
    }

    public Node<E> getSeguent() {
        return seguent; // Retorna la referència al següent node
    }

    public void setSeguent(Node<E> seguent) {
        this.seguent = seguent; // Estableix la referència al següent node
    }   

    public Node<E> getAnterior() {
        return anterior;    // Retorna la referència al node anterior
    }

    public void setAnterior(Node<E> anterior) {
        this.anterior = anterior;   // Estableix la referència al node anterior
    }
}