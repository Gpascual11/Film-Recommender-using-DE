package cat.urv.deim.models;

public class MNode<A extends Comparable<A>, B extends Comparable<B>> {

    private A valA;
    private B valB;

    private int rating;

    private MNode<A, B> nextA;
    private MNode<A, B> prevA;
    private MNode<A, B> nextB;
    private MNode<A, B> prevB;

    public MNode(A valA, B valB, int rating) {
        this.valA = valA;
        this.valB = valB;
        this.rating = rating;
    }

    public A getVal() { // Retorna el valor del primer tipus: si (Node<A,B> -> A) pero si (Node<B,A> -> B)
        return valA;
    }

    public void setVal(A valA) {
        this.valA = valA;
    }

    public B getOtherVal() { // Retorna el valor del segon tipus: si (Node<A,B> -> B) pero si (Node<B,A> -> A)
        return valB;
    }

    public void setOtherVal(B valB) {
        this.valB = valB;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public MNode<A, B> getNext() {
        return nextA;
    }

    public void setNext(MNode<A, B> nextA) {
        this.nextA = nextA;
    }

    public MNode<A, B> getPrev() {
        return prevA;
    }

    public void setPrev(MNode<A, B> prevA) {
        this.prevA = prevA;
    }

    public MNode<A, B> getOtherNext() {
        return nextB;
    }

    public void setOtherNext(MNode<A, B> nextB) {
        this.nextB = nextB;
    }

    public MNode<A, B> getOtherPrev() {
        return prevB;
    }

    public void setOtherPrev(MNode<A, B> prevB) {
        this.prevB = prevB;
    }

}