package ch2.section4;

import edu.princeton.cs.algs4.In;

public class UnorderedArrayMaxPQ<Key extends Comparable<Key>> {

    private static int N;
    private Key[] aux;

    private void resize(int cap) {
        Key[] temp = (Key[]) new Comparable[cap];
        for (int i = 0; i < N; i++) {
            temp[i] = aux[i];
        }
        aux = temp;
    }

    public UnorderedArrayMaxPQ(Key[] a) {
        N = a.length;
        aux = (Key[]) new Comparable[N];
        for (int i = 0; i < N; i++) {
            aux[i] = a[i];
        }
    }

    public void insert(Key v) {
        if (N == aux.length) {
            resize(2 * aux.length);
        }
        aux[N++] = v;
    }


    public Key max() {
        int max = 0;
        for (int i = 1; i < N; i++) {
            if (less(aux[max],aux[i])) {
                max = i;
            }
        }
        return aux[max];
    }

    public Key delMax() {
        int max = 0;
        for (int i = 1; i < N; i++) {
            if (less(aux[max],aux[i])) {
                max = i;
            }
        }
        exch(aux, max, N - 1);
        Key item = aux[--N];
        aux[N] = null;
        if (N > 0 && N <= aux.length / 4) resize(aux.length / 2);
        return item;
    }

    public int size() {
        return N;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }


    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args) {
        int[] a = In.readInts();
        Integer[] A = new Integer[a.length];
        for (int i = 0; i < a.length; i++) {
            A[i] = a[i];
        }
        UnorderedArrayMaxPQ<Integer> pq = new UnorderedArrayMaxPQ<>(A);
        System.out.println(pq.size());
        System.out.println(pq.delMax());
        System.out.println(pq.size());
        System.out.println(pq.delMax());
        System.out.println(pq.size());
        pq.insert(200);
        System.out.println(pq.max());
        System.out.println(pq.delMax());
    }
}
