package ch2.section4;

import edu.princeton.cs.algs4.In;

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;


    public MaxPQ(int cap) {
        pq = (Key[]) new Comparable[cap + 1];
    }

    public void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }

    public Key delMax() {
        Key item = pq[1];
        exch(1, N--);
        pq[N + 1] = null;
        sink(1);
        return item;
    }

    public int size() {
        return N;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) j++;
            if (less(j, k)) break;
            exch(j, k);
            k = j;
        }
    }



    public static void main(String[] args) {
        int[] a = In.readInts();
        Integer[] A = new Integer[a.length];
        for (int i = 0; i < a.length; i++) {
            A[i] = a[i];
        }
        MaxPQ<Integer> pq = new MaxPQ<>(A.length);

        for (int i = 0; i < A.length; i++) {
            pq.insert(A[i]);
        }
        System.out.println(pq.delMax());
        System.out.println(pq.delMax());

    }
}
