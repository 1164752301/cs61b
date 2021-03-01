public class ArrayDeque<T> implements Deque<T>{
    private int size;
    private T[] items;
    private int first = 8;
    private int last = -1;

    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[8];
    }

    private void reSizeUp() {
        items = copyArray(items.length * 2);
        first = items.length;
        last = size - 1;
    }

    private void reSizeDown() {
        items = copyArray(items.length / 2);
        first = items.length;
        last = size;
    }

    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            reSizeUp();
        }
        size += 1;
        first -= 1;
        items[first] = item;
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            reSizeUp();
        }
        size += 1;
        last += 1;
        items[last] = item;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int i;
        for (i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        T removed = items[first];
        first += 1;
        if (first == items.length) {
            first = 0;
        }
        if (size > 16 && size * 4 < items.length) {
            reSizeDown();
        }
        return removed;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        T removed = items[last];
        last -= 1;
        if (last == -1) {
            last = items.length - 1;
        }
        if (size > 16 && size * 4 < items.length) {
            reSizeDown();
        }
        return removed;
    }

    @Override
    public T get(int index) {
        if (isEmpty()) {
            return null;
        }
        return items[(first + index) % items.length];
    }

    private T[] copyArray(int length) {
        int i;
        T[] newArray = (T[]) new Object[length];
        for (i = 0; i < size; i++, first++) {
            newArray[i] = items[first % items.length];
        }
        return newArray;
    }

    public static void main(String[] args) {
        ArrayDeque arr = new ArrayDeque();
        arr.addFirst(3);
        arr.addFirst(4);
        arr.addFirst(5);
        arr.addFirst(6);
        arr.addLast(2);
        arr.addFirst(7);
        arr.addLast(1);
        arr.addFirst(8);
        arr.addLast(0);
        arr.addLast(-1);
        arr.addFirst(9);
        arr.addFirst(10);
        System.out.println(arr.removeLast());
        System.out.println(arr.removeLast());
        System.out.println(arr.removeLast());
        System.out.println(arr.removeLast());
        System.out.println(arr.removeLast());
        System.out.println(arr.removeLast());
        System.out.println(arr.removeLast());
        System.out.println(arr.get(2));
        System.out.println(arr.size());
        System.out.println(arr.isEmpty());
        arr.printDeque();
    }
}
