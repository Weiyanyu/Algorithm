package ch3.section1;

import ch1.section3.Queue;

public class BinarySearchST<Key extends Comparable, Value> {
    private Key[] keys;
    private Value[] vals;

    private int N = 0;

    public BinarySearchST(int cap) {
        keys = (Key[]) new Comparable[cap];
        vals = (Value[]) new Object[cap];
    }

    private void resize(int cap) {
        assert cap > N;
        Key[] tempK = (Key[]) new Comparable[cap];
        Value[] tempV = (Value[]) new Object[cap];
        for (int i = 0; i < N; i++) {
            tempK[i] = keys[i];
            tempV[i] = vals[i];
        }
        keys = tempK;
        vals = tempV;
    }

    public int size() {
        return N;
    }

    public int rank(Key key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int comp = key.compareTo(keys[mid]);
            if (comp > 0) lo = mid + 1;
            else if (comp < 0) hi = mid - 1;
            else return mid;
        }
        return lo;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    @SuppressWarnings("unchecked")
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException();
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && key.compareTo(keys[i]) == 0) return vals[i];
        else return null;
    }

    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException();
        // if value == null we should delete
        if (value == null) {
            delete(key);
            return;
        }

        int i = rank(key);
        if (i < N && key.compareTo(keys[i]) == 0) {
            vals[i] = value;
            return;
        }

        if (N == keys.length) {
            resize(2 * keys.length);
        }

        for (int j = N; j > i; j--) {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = value;
        N++;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < N; i++) {
            queue.enqueue(keys[i]);
        }
        return queue;
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException();
        if (isEmpty()) return;

        int i = rank(key);

        if (i == N || key.compareTo(keys[i]) != 0) {
            return;
        }

        for (int j = i; j < N; j++) {
            keys[j] = keys[j+1];
            vals[j] = vals[j+1];
        }
        N--;
        keys[N] = null;
        vals[N] = null;

        if (N <= keys.length / 4) {
            resize(keys.length / 2);
        }
    }
}
