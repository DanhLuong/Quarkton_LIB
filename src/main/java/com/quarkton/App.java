package com.quarkton;


import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        BenchMark bm = new BenchMark();
        int[] arr = IntStream.range(1, 200_000).toArray();
        int[] checkArray = ArrayUtils.copy(arr);
        bm.track("start");
        ArrayUtils.shuffle(arr);
        bm.track("shuffle");
        SortUtils.selectionSort(arr);
        bm.track("sort");
        System.out.println(bm.checkInterval("start", "shuffle"));
        System.out.println(bm.checkInterval("shuffle", "sort"));
    }
}
