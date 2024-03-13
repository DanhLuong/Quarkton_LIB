package com.quarkton;

import org.junit.jupiter.api.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static com.quarkton.ArrayUtils.*;
import static java.lang.System.arraycopy;

public class ArrayCopyBenchTest {
    private final int TEST_SIZE = 1_000_000_000;
    int[] arr = IntStream.rangeClosed(1, TEST_SIZE).toArray();
    BenchMark bm = new BenchMark();
    @Test
    public void copyBenchTest() {
        //warm up
        int[] arr0 = copy(arr, 0, arr.length);
        arr0 = null;
        //test
        bm.track("s0");
        arr0 = copy(arr, 0, arr.length);
        bm.track("e0");
        System.out.println("copy: " + bm.checkInterval("s0", "e0"));
        Assertions.assertTrue(ArrayUtils.compare(arr, arr0), "Wrong");
    }
    @Test
    public void copyParallelBenchTest() {
        //warm up
        int[] arr1 = copyParallel(arr, 0, arr.length);
        arr1 = null;
        //test
        bm.track("s1");
        arr1 = copyParallel(arr, 0, arr.length);
        bm.track("e1");
        System.out.println("copy parallel: " + bm.checkInterval("s1", "e1"));
        Assertions.assertTrue(ArrayUtils.compare(arr, arr1), "Wrong");
    }
    @Test
    public void SystemArrayCopyBenchTest() {
        //warm up
        int[] arr2 = new int[arr.length];
        arraycopy(arr ,0, arr2,0, arr.length);
        arr2 = null;
        //test
        bm.track("s2");
        arr2 = new int[arr.length];
        arraycopy(arr ,0, arr2,0, arr.length);
        bm.track("e2");
        System.out.println("System arraycopy: " + bm.checkInterval("s2", "e2"));
        Assertions.assertTrue(ArrayUtils.compare(arr, arr2), "Wrong");
    }
    @Test
    public void copyByPoolBenchTest() {
        //warm up
        int[] arr3 = new int[arr.length];
        arraycopy(arr ,0, arr3,0, arr.length);
        arr3 = null;
        //test
        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        try {
            bm.track("s3");
            arr3 = copyWithPool(arr ,10, service);
            bm.track("e3");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("copyWithPool: " + bm.checkInterval("s3", "e3"));
        Assertions.assertTrue(ArrayUtils.compare(arr, arr3), "Wrong");
    }

}
