package array;

public class ResizableArray {
    private int[] items;
    private int size;

    public int size() {
        return size;
    }

    public void set(int index, int item) {
        if(index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        items[index] = item;
    }

    public void append(int item) {
        ensureExtraCapacity();
        items[size] = item;
        size++;
    }
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return items[index];
    }

    private void ensureExtraCapacity() {
        if (size == items.length) {
            int[] copy = new int[size * 2];
            System.arraycopy(items, 0, copy, 0, size);
            items = copy;
        }
    }
}