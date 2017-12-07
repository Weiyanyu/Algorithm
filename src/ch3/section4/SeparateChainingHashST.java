package ch3.section4;

import ch3.section1.SequentialSearchST;

import java.util.Iterator;

public class SeparateChainingHashST<Key, Value> {
    private int N;          //total
    private int M;          //array size

    private SequentialSearchST<Key, Value> st[];

    public SeparateChainingHashST() {
        this(4);
    }

    @SuppressWarnings("unchecked")
    public SeparateChainingHashST(int M) {
        this.M = M;
        this.st = (SequentialSearchST[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST<>();
        }

    }


    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("key can't be a null");
        return (Value) st[hash(key)].get(key);
    }

    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("key can't be a null");
        else {
            if (N >= 10 * M) {
                resize(2 * M);
            }

            int i = hash(key);
            if (!st[i].contains(key)) {
                N++;
            }
            st[i].put(key, val);
        }
    }


    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("key can't be a null");
        return get(key) != null;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    private void resize(int cap) {
        SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<>(cap);
        for (int i = 0; i < M; i++) {
            Iterator iter = st[i].keys().iterator();
            while (iter.hasNext()) {
                Key key = (Key) iter.next();
                temp.put(key, st[i].get(key));
            }
        }

        M = temp.M;
        N = temp.N;
        st = temp.st;
    }







}
