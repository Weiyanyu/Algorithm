package ch1.section3;

public class FixedCapacityStack<Item> {
    private int N;
    private int currentCount;

    private Item[] container;

    public FixedCapacityStack(int cap) {
        this.N = cap;
        container = (Item[]) new Object[N];
    }

    public int size() {
        return currentCount;
    }

    public boolean isEmpty() {
        return currentCount == 0;
    }

    public void push(Item item) throws IllegalArgumentException{
        if (size() >= N) {
            throw  new IllegalArgumentException("can't add item already");
        }
        container[currentCount++] = item;
    }
    public Item pop() {
        if (size() <= 0) {
            throw new IllegalArgumentException("can't remove item already");
        }
        Item item;
        item = container[--currentCount] = null;
        container[currentCount] = null;
        return item;
    }

    public boolean isFull() {
        return N == currentCount;
    }

    public static void main(String[] args) {
        FixedCapacityStack<Integer> stack = new FixedCapacityStack<>(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("is Full ? " + stack.isFull());
//        stack.push(4);            this line will throw Exception(can't add item already)
        stack.pop();
        stack.pop();
        stack.pop();
        System.out.println(stack.size());   //will print 0
        //stack.pop();               will throw Exception(can't remove item already)
    }

}
