package ch3.section1;

import ch1.section3.Queue;

import java.util.Iterator;

public class SequentialSearchST<Key, Val> {

    private Node first;

    private class Node {
        Key key;
        Val val;
        Node next;

        public Node(Key key, Val val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public Val get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (x.key.equals(key)) {
                return x.val;
            }
        }
        return null;
    }

    public void put(Key key, Val val) {
        for (Node x = first; x != null; x = x.next) {
            if (x.key.equals(key)) {
                x.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (Node x = first; x != null; x = x.next) {
            queue.enqueue(x.key);
        }
        return queue;
    }

    public static void main(String[] args) {
        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
        st.put("a", 1);
        st.put("b", 2);
        st.put("c", 3);
        System.out.println(st.get("a"));
        System.out.println(st.get("d"));
    }
}
