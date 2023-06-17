package cat.urv.deim.models;

public class HNode<K,V> {

    private K key;
    private V value;
    private HNode<K,V> next;

    public HNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public HNode<K, V> getNext() {
        return next;
    }

    public void setNext(HNode<K, V> next) {
        this.next = next;
    }
}