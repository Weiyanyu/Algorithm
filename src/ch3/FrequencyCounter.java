package ch3;

import ch3.section1.BinarySearchST;
import ch3.section1.SequentialSearchST;
import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.StdIn;

public class FrequencyCounter {
    public static void main(String[] args) {
        int minLen = Integer.parseInt(args[0]);
        BST<String, Integer> st = new BST<>();
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (word.length() < minLen) continue;
            if (!st.contains(word)) st.put(word, 1);
            else                    st.put(word, st.get(word) + 1);
        }

        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max)) {
                max = word;
            }
        }

        System.out.println("max frequency word is : " + max);
        System.out.println("count is : " + st.get(max));
    }
}
