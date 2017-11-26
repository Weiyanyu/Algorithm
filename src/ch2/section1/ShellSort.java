package ch2.section1;

import edu.princeton.cs.algs4.In;

public class ShellSort {

    public static void sort(Comparable[] a) {
        int h = 1;
        while (h < a.length / 3) h = h * 3 + 1;
        while (h >= 1) {
            for (int i = h; i < a.length; i++) {
                for (int j = i; j >= h && less(a[j], a[j-h]); j-=h) {
                    exch(a, j-h, j);
                }
            }
            h /= 3;
        }
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }


    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void show(Comparable[] a) {
        for (Comparable e : a) {
            System.out.print(e + " ");
        }
        System.out.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i-1])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] a = In.readInts();
        Integer[] A = new Integer[a.length];
        for (int i = 0; i < a.length; i++) {
            A[i] = a[i];
        }
        ShellSort.sort(A);
        assert ShellSort.isSorted(A);
        ShellSort.show(A);
    }
}
