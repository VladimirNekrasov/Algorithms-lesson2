package lesson2;

import java.util.Arrays;

public class MyArrayList<T extends Comparable<T>> {
    private T[] list;
    private int size;
    private int capacity;
    private final int DEFAULT_CAPACITY = 10;

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    public MyArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity <=0 " + capacity);
        }
        this.capacity = capacity;
        list = (T[]) new Comparable[capacity];
    }

    public MyArrayList() {
        this.capacity = DEFAULT_CAPACITY;
        list = (T[]) new Comparable[capacity];
    }

    public void add(T item) {
        increaseCapacity();
        list[size] = item;
        size++;


    }

    public void add(int index, T item) {
        increaseCapacity();
        if (index > this.capacity) {
            throw new ArrayIndexOutOfBoundsException("Вы не можете добавить элемент на позицию " + index +
                    ", так как емкость массива " + this.capacity);
        }

        for (int i = size; i > index; i--) {
            list[i] = list[i - 1];
        }
        list[index] = item;
        size++;
    }

    public final T remove(int index) {
        if (index > this.capacity) {
            throw new ArrayIndexOutOfBoundsException("Вы не можете удалить элемент под номером " + index +
                    ", так как емкость массива " + this.capacity);
        }
        T temp = list[index];
        for (int i = index; i < size; i++) {
            list[i] = list[i + 1];
        }
        size--;
        list[size] = null;
        return temp;
    }

    public boolean remove(T item) {
        int i = indexOf(item);
        if (i == -1) {
            return false;
        }
        remove(i);
        return true;
    }

    public int indexOf(T item) {
        for (int i = 0; i < size; i++) {
            if (list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    public T get(int index) {
        if (index > this.capacity) {
            throw new ArrayIndexOutOfBoundsException("Запрашиваемый элемент под номером " + index +
                    " отсутствует, так как емкость массива " + this.capacity);
        }
        return list[index];
    }

    public void set(int index, T item) {
        if (index > this.capacity) {
            throw new ArrayIndexOutOfBoundsException("Вы не можете изменить элемент под номером " + index + ", " +
                    "отсутствует, так как емкость массива " + this.capacity);
        }
        list[index] = item;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        for (int i = 0; i < size; i++) {
            sb.append(list[i]).append(", ");
        }
        if (size > 0) {
            sb.setLength(sb.length() - 2);
        }

        sb.append(" ]");
        return sb.toString();
    }


    private boolean less(T item1, T item2) {
        return item1.compareTo(item2) < 0;
    }

    private void swap(int index1, int index2) {
        T temp = list[index1];
        list[index1] = list[index2];
        list[index2] = temp;
    }

    public boolean increaseCapacity() {
        if (size == capacity) {
            capacity = capacity * 3 / 2;
            T[] list1 = new MyArrayList<T>(this.capacity * 3 / 2).list;
            for (int i = 0; i < list.length; i++) {
                list1[i] = list[i];
            }
            list = list1;
            return true;
        }
        return false;
    }

    public void selectionSort() {
        long start = System.currentTimeMillis();
        int iMin;
        for (int i = 0; i < size - 1; i++) {
            iMin = i;
            for (int j = i + 1; j < size; j++) {
                if (less(list[j], list[iMin])) {
                    iMin = j;
                }
            }
            swap(i, iMin);
        }
        long finish = System.currentTimeMillis();
        System.out.println("Время выполнения сортировки методом выбора " + (finish - start) + "мс");

    }

    public void insertionSort() {
        long start = System.currentTimeMillis();
        T key;
        for (int i = 1; i < size; i++) {
            int j = i;
            key = list[i];
            while (j > 0 && less(key, list[j - 1])) {
                list[j] = list[j - 1];
                j--;
            }
            list[j] = key;
        }
        long finish = System.currentTimeMillis();
        System.out.println("Время выполнения сортировки методом вставки " + (finish - start) + "мс");
    }

    public void bubbleSort() {
        long start = System.currentTimeMillis();
        for (int i = size - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (less(list[j + 1], list[j])) {
                    swap(j + 1, j);
                }
            }
        }
        long finish = System.currentTimeMillis();
        System.out.println("Время выполнения пузырьковой сортировки " + (finish - start) + "мс");
    }

    public void bubbleSortO() {
        long start = System.currentTimeMillis();
        boolean isSwap;
        for (int i = size - 1; i > 0; i--) {
            isSwap = false;
            for (int j = 0; j < i; j++) {
                if (less(list[j + 1], list[j])) {
                    swap(j + 1, j);
                    isSwap = true;
                }
            }
            if (!isSwap) {
                break;
            }
        }
        long finish = System.currentTimeMillis();
        System.out.println("Время выполнения оптимизированной пузырьковой сортировки " + (finish - start) + "мс");
    }
}
