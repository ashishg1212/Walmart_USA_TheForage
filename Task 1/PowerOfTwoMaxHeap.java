
import java.util.ArrayList;
import java.util.List;

public class PowerOfTwoMaxHeap<T extends Comparable<T>> {

    private List<T> heap;
    private int childrenCount; // The number of children each node will have

    public PowerOfTwoMaxHeap(int childrenCount) {
        this.heap = new ArrayList<>();
        this.childrenCount = childrenCount; // Number of children each node will have
    }

    private void swap(int first, int second) {
        T temp = heap.get(first);
        heap.set(first, heap.get(second));
        heap.set(second, temp);
    }

    private int parent(int index) {
        if (index == 0) {
            return -1; // Root node has no parent
        }
        return (index - 1) / childrenCount;
    }

    private List<Integer> children(int index) {
        List<Integer> children = new ArrayList<>();
        int firstChildIndex = index * childrenCount + 1;
        for (int i = 0; i < childrenCount; i++) {
            int childIndex = firstChildIndex + i;
            if (childIndex < heap.size()) {
                children.add(childIndex);
            }
        }
        return children;
    }

    public void insert(T value) {
        heap.add(value);
        upheap(heap.size() - 1);
    }

    private void upheap(int index) {
        int p = parent(index);
        if (p >= 0 && heap.get(index).compareTo(heap.get(p)) > 0) {
            swap(index, p);
            upheap(p);
        }
    }

    public T popMax() throws Exception {
        if (heap.isEmpty()) {
            throw new Exception("Removing from an empty heap");
        }
        T max = heap.get(0);
        T last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            downheap(0);
        }
        return max;
    }

    private void downheap(int index) {
        List<Integer> children = children(index);
        int maxIndex = index;

        for (int childIndex : children) {
            if (heap.get(childIndex).compareTo(heap.get(maxIndex)) > 0) {
                maxIndex = childIndex;
            }
        }

        if (maxIndex != index) {
            swap(maxIndex, index);
            downheap(maxIndex);
        }
    }

    public List<T> heapSort() throws Exception {
        List<T> sortedList = new ArrayList<>();
        while (!heap.isEmpty()) {
            sortedList.add(popMax());
        }
        return sortedList;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }
}


// Main Class
public class Main {

    public static void main(String[] args) throws Exception {

        PowerOfTwoMaxHeap<Integer> heap = new PowerOfTwoMaxHeap<>(2);

        System.out.println("Inserting elements into the heap:");
        heap.insert(10);
        heap.insert(4);
        heap.insert(15);
        heap.insert(20);
        heap.insert(3);

        System.out.println("Heap size: " + heap.size());

        System.out.println("Popping max elements from the heap:");
        while (!heap.isEmpty()) {
            System.out.println(heap.popMax());
        }

    }
}
