package edu.neu.coe.info6205.sort.par;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

/**
 * This code has been fleshed out by Ziyao Qiao. Thanks very much.
 * TODO tidy it up a bit.
 */
public class Main {

    public static void main(String[] args) {
        for (int k = 2; k <= ForkJoinPool.getCommonPoolParallelism(); k = k + 2) {
            System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", String.valueOf(k));
            ArrayList<String> timeList = new ArrayList<>();
            performParSort(timeList, k);
            printResults(timeList, k);
        }
    }

    private static void printResults(ArrayList<String> timeList, int k) {
        try {
            FileOutputStream fis = new FileOutputStream("./src/result_ "+ k + ".csv");
            OutputStreamWriter isr = new OutputStreamWriter(fis);
            BufferedWriter bw = new BufferedWriter(isr);
            int j = 0;
            for (String i : timeList) {
                j++;
                bw.write(i);
                bw.flush();
            }
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void performParSort(ArrayList<String> timeList, int threadCount) {
        Random random = new Random();
        for (int k = 1; k <= 5; k++) {
            int[] array = new int[1000000 * k];
            for (int j = 50; j < 100; j++) {
                ParSort.cutoff = 10000 * (j + 1);
                long time;
                long startTime = System.currentTimeMillis();
                for (int t = 0; t < 10; t++) {
                    for (int i = 0; i < array.length; i++) array[i] = random.nextInt(10000000);
                    ParSort.sort(array, 0, array.length);
                }
                long endTime = System.currentTimeMillis();
                time = (endTime - startTime);

                String output = threadCount + "," + array.length + "," + ParSort.cutoff + "," + time + "," + "\n" ;
                timeList.add(output);
                System.out.println("cutoff：" + (ParSort.cutoff) + "\t\t10times Time: " + time + "ms " + "Array size: " + array.length);
            }
        }
    }

}
