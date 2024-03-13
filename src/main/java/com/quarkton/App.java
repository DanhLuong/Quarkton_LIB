package com.quarkton;


import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

import static com.quarkton.ArrayUtils.copy;
import static com.quarkton.ArrayUtils.copyParallel;


/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        BenchMark bm = new BenchMark();
        int[] arr = IntStream.rangeClosed(1, 1_500_000_000).toArray();
        for(int i =0; i<5; i++) {
            int[] arr0 = ArrayUtils.copy(arr, 0, arr.length);
            arr0=null;
        }
        bm.track("s0");
        int[] arr0 = ArrayUtils.copy(arr, 0, arr.length);
        bm.track("e0");
        System.out.println(bm.checkInterval("s0", "e0"));
        System.out.println(ArrayUtils.compare(arr, arr0) ? "OK" : "Wrong");
        arr0=null;
        //---------------------
        bm.track("s1");
        int[] arr1 = ArrayUtils.copyParallel(arr, 0, arr.length);
        bm.track("e1");
        System.out.println(bm.checkInterval("s1", "e1"));
        System.out.println(ArrayUtils.compare(arr, arr1) ? "OK" : "Wrong");
        arr1=null;
        //---------------------
        bm.track("s2");
        int[] arr2 = new int[arr.length];
        System.arraycopy(arr ,0, arr2,0, arr.length);
        bm.track("e2");
        System.out.println(bm.checkInterval("s2", "e2"));
        System.out.println(ArrayUtils.compare(arr, arr2) ? "OK" : "Wrong");
//        for (int i = 0; i < 100; i++) {
//            bm.track("s"+i);
//            int[] arr2 = new int[arr.length];
//            System.arraycopy(arr ,0, arr2,0, arr.length);
//            bm.track("e"+i);
//            arr2 = null;
//            System.out.println(bm.checkInterval("s"+i, "e"+i));
//        }
//
//        for (int i = 0; i < 100; i++) {
//            bm.track("s"+i);
//            int[] arr2 = copy(arr);
//            bm.track("e"+i);
//            arr2 = null;
//            System.out.println(bm.checkInterval("s"+i, "e"+i));
//        }
//
//        for (int i = 0; i < 100; i++) {
//            bm.track("s"+i);
//            int[] arr2 = copyParallel(arr ,0, arr.length);
//            bm.track("e"+i);
//            arr2 = null;
//            System.out.println(bm.checkInterval("s"+i, "e"+i));
//        }

//        System.out.println(ArrayUtils.compare(arr, arr3) ? "OK" : "Wrong");
    }
}
