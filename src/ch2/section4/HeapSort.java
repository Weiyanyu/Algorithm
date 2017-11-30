package ch2.section4;

import edu.princeton.cs.algs4.In;

public class HeapSort {
    private HeapSort() {

    }

    public static void sort(Comparable[] pq) {
        int N = pq.length;
        for (int k = N / 2; k > 0; k--) {
            sink(pq, k, N);
        }

        while (N > 1) {
            exch(pq, 1, N--);
            sink(pq, 1, N);
        }
    }

    private static void sink(Comparable[] pq, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(pq, j, j+1)) j++;
            if (!less(pq, k, j)) break;
            exch(pq, k, j);
            k = j;
        }
    }


    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i-1];
        pq[i-1] = pq[j-1];
        pq[j-1] = swap;
    }

    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i-1].compareTo(pq[j-1]) < 0;
    }

    public static void main(String[] args) {
        int[] a = In.readInts();
        Integer[] A = new Integer[a.length];
        for (int i = 0; i < a.length; i++) {
            A[i] = a[i];
        }
        HeapSort.sort(A);
        for (int i = 0; i < A.length; i++) {
            System.out.print(A[i] + " ");
        }
    }
}
