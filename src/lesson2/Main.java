package lesson2;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Random r = new Random();
        MyArrayList<Integer> mal = new MyArrayList<>(100000);
        for (int i = 0; i < mal.getCapacity(); i++) {
            mal.add(r.nextInt(1000000000));
        }

        // mal.selectionSort();    //Время выполнения сортировки методом выбора 10980мс
        // mal.insertionSort();    //Время выполнения сортировки методом вставки 3925мс
        // mal.bubbleSort();       //Время выполнения пузырьковой сортировки 29906мс
        //  mal.bubbleSortO();        //Время выполнения оптимизированной пузырьковой сортировки 29989мс


    }
}
