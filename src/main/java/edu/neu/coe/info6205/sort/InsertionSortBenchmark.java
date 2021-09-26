package edu.neu.coe.info6205.sort;


import edu.neu.coe.info6205.sort.elementary.InsertionSort;
import edu.neu.coe.info6205.util.Benchmark_Timer;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class InsertionSortBenchmark {

    public static final int END_INCLUSIVE = 10;
    public static final int ARRAY_SIZE_MULTIPLIER = 10000;
    public static final int NUMBER_OF_ITERATIONS = 1;

    public static void main(String[] args) {
        try {
            randomSortedArray();
            halfOrderedArray();
            sequentialSortedArray();
            inverseOrderedArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * To evaluate time taken for computation of insertion sort
     *
     * @param desc
     * @param input
     */
    public void evaluateTime(String desc, Integer[] input) {
        final Supplier<Integer[]> supplier = () -> Arrays.copyOf(input, input.length);
        final Benchmark_Timer<Integer[]> benchmark_timer = new Benchmark_Timer<>(desc, null, InsertionSort::sort, null);
        final double averageTime = benchmark_timer.runFromSupplier(supplier, NUMBER_OF_ITERATIONS);
        System.out.println(desc + " | Average Sorting Time (in ms): " + averageTime);
    }

    /**
     * To calculate time required for randomly sorted array
     */
    public static void randomSortedArray() {
        IntStream.rangeClosed(1, END_INCLUSIVE).forEach(incrementor -> {
            Integer[] arr = new Integer[incrementor * ARRAY_SIZE_MULTIPLIER];
            Arrays.setAll(arr, i -> new Random().nextInt());
            new InsertionSortBenchmark().evaluateTime("randomSortedArray -> Average time (ms):  for " + incrementor * ARRAY_SIZE_MULTIPLIER + " Array size", arr);
        });
    }

    /**
     * To calculate time required for sequentially sorted array
     */
    public static void sequentialSortedArray() {
        IntStream.rangeClosed(1, END_INCLUSIVE).forEach(incrementor -> {
            Integer[] arr = new Integer[incrementor * ARRAY_SIZE_MULTIPLIER];
            Arrays.setAll(arr, i -> i);
            new InsertionSortBenchmark().evaluateTime("sequentialSortedArray -> Average time (ms): for " + incrementor * ARRAY_SIZE_MULTIPLIER + " Array size", arr);
        });
    }

    /**
     * To calculate time required for half sorted array
     */
    public static void halfOrderedArray() {
        IntStream.rangeClosed(1, END_INCLUSIVE).forEach(incrementor -> {
            Integer[] arr = new Integer[incrementor * ARRAY_SIZE_MULTIPLIER];
            Arrays.setAll(arr, i -> new Random().nextInt());
            Arrays.sort(arr, 0, (arr.length) / 2);
            new InsertionSortBenchmark().evaluateTime("partiallySortedArray -> Average time (ms): for " + incrementor * ARRAY_SIZE_MULTIPLIER + " Array size", arr);
        });
    }

    /**
     * To calculate time required for inversely sorted array
     */
    public static void inverseOrderedArray() {
        for (int incrementor = 1; incrementor <= END_INCLUSIVE; incrementor++) {
            Integer[] arr = new Integer[incrementor * ARRAY_SIZE_MULTIPLIER];
            int j = 0;
            for (int i = arr.length - 1; i >= 0; i--) {
                arr[j] = i;
                j++;
            }
            new InsertionSortBenchmark().evaluateTime("reversedSortedArray -> Average time (ms): for " + incrementor * ARRAY_SIZE_MULTIPLIER + " Array size", arr);
        }
    }


}
