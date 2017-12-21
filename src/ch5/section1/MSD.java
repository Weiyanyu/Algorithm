package ch5.section1;

import edu.princeton.cs.algs4.Insertion;

public class MSD {
    private static int R = 256;
    private static final int M = 15;
    private static String[] aux;

    private static int charAt(String s, int d) {
        if (d < s.length())
            return s.charAt(d);
        else
            return -1;
    }

    public static void sort(String[] a) {
        int N = a.length;
        aux = new String[N];
        sort(a, 0, N-1, 0);
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        int[] count = new int[R];
        //计算频率
        for (int i = lo; i <= hi; i++) {
            count[charAt(a[i], d) + 2]++;
        }

        //转换成索引
        for (int r = 0; r < R +1; r++) {
            count[r+1] += count[r];
        }

        //分类排序
        for (int i = lo; i <= hi; i++) {
            aux[count[charAt(a[i], d) + 1]++] = a[i];
        }

        //写回
        for (int i = lo; i <= hi; i++) {
            a[i] = aux[i];
        }

        //递归的排除首字母进行排序
        for (int r = 0; r < R; r++) {
            sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1);
        }
    }
}
