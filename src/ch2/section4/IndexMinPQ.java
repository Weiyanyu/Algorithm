package ch2.section4;

import java.util.NoSuchElementException;

public class IndexMinPQ<Key extends Comparable<Key>> {
    private int maxN;
    private int N;
    private int[] pq;
    private int[] qp;
    private Key[] keys;

    public IndexMinPQ(int maxN) {
        if (maxN < 0) throw new IllegalArgumentException("Illegal arguments");
        this.maxN = maxN;
        N = 0;
        keys = (Key[]) new Comparable[maxN + 1];
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        for (int i = 0; i <= maxN; i++) {
            //to index maxN
            qp[i] = -1;
        }
    }

    public boolean isEmpty() {
        return N == 0;
    }
    public int size() {
        return N;
    }

    public boolean contains(int k) {
        if (k < 0 || k >= maxN) throw new IllegalArgumentException();
        return qp[k] != -1;
    }

    public void insert(int k, Key key) {
        if (k < 0 || k >= maxN) throw new IllegalArgumentException();
        if (contains(k)) throw new IllegalArgumentException();
        N++;
        pq[N] = k;
        qp[k] = N;
        keys[k] = key;
        swim(N);
    }

    public int minIndex() {
        if (N == 0) throw new NoSuchElementException();
        return pq[1];
    }

    public Key minKey() {
        if (N == 0) throw new NoSuchElementException();
        return keys[pq[1]];
    }

    public int delMin() {
        if (N == 0) throw new NoSuchElementException("Priority queue underflow");
        int min = pq[1];
        exch(1, N--);
        sink(1);
        qp[min] = -1;
        pq[N + 1] = -1;
        keys[min] = null;
        return min;
    }

    public Key keyOf(int k) {
        if (k < 0 || k >= maxN) throw new IllegalArgumentException();
        if (!contains(k)) throw new NoSuchElementException();
        return keys[k];
    }

    public void changeKey(int i, Key key) {
        if (i < 0 || i >= maxN) throw new IllegalArgumentException();
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        keys[i] = key;
        swim(qp[i]);
        sink(qp[i]);
    }

    public void change(int i, Key key) {
        changeKey(i, key);
    }

    public void delete(int k) {
        if (k < 0 || k >= maxN) throw new IllegalArgumentException();
        if (!contains(k)) throw new NoSuchElementException("index is not in the priority queue");
        int index = qp[k];
        exch(index, N--);
        swim(index);
        sink(index);
        keys[k] = null;
        qp[k] = -1;
    }

    private void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    private boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    private void swim(int k) {
        while (k > 1 && greater(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && greater(j, j+1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

}
