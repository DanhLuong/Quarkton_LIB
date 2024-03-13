package com.quarkton;

import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SortUtilsTest {
    private final int TEST_SIZE = 100_000;
    int[] arr = IntStream.rangeClosed(1, TEST_SIZE).toArray();
    int[] original = IntStream.rangeClosed(1, TEST_SIZE).toArray();
    BenchMark bm = new BenchMark();
    @BeforeEach
    public void initTest() {
        ArrayUtils.shuffle(arr);
    }
    @Test
    @Ignore
    public void testBubbleSort() {
        bm.track("startBubbleSort");
        SortUtils.bubbleSort(arr);
        bm.track("endBubbleSort");
        System.out.println("BubbleSort:" + bm.checkInterval("startBubbleSort","endBubbleSort"));
        Assert.assertTrue("Wrong", ArrayUtils.compare(arr, original));
    }

    @Test
    public void testSelectionSort() {
        bm.track("startSelectionSort");
        SortUtils.selectionSort(arr);
        bm.track("endSelectionSort");
        System.out.println("SelectionSort:" + bm.checkInterval("startSelectionSort","endSelectionSort"));
        Assert.assertTrue("Wrong", ArrayUtils.compare(arr, original));
    }
    @Test
    public void testInsertionSort() {
        bm.track("startInsertionSort");
        SortUtils.insertionSort(arr);
        bm.track("endInsertionSort");
        System.out.println("InsertionSort:" + bm.checkInterval("startInsertionSort","endInsertionSort"));
        Assert.assertTrue("Wrong", ArrayUtils.compare(arr, original));
    }
    @Test
    public void testShellSort() {
        bm.track("startShellSort");
        SortUtils.shellSort(arr);
        bm.track("endShellSort");
        System.out.println("ShellSort:" + bm.checkInterval("startShellSort","endShellSort"));
        Assert.assertTrue("Wrong", ArrayUtils.compare(arr, original));
    }
    @Test
    public void testMergeSort() {
        bm.track("startMergeSort");
        SortUtils.mergeSort(arr, 0, arr.length);
        bm.track("endMergeSort");
        System.out.println("MergeSort:" + bm.checkInterval("startMergeSort","endMergeSort"));
        Assert.assertTrue("Wrong", ArrayUtils.compare(arr, original));
    }

    @Test
    public void testquickSort() {
        bm.track("startQuickSort");
        SortUtils.quickSort(arr, 0, arr.length);
        bm.track("endQuickSort");
        System.out.println("QuickSort:" + bm.checkInterval("startQuickSort","endQuickSort"));
        Assert.assertTrue("Wrong", ArrayUtils.compare(arr, original));
    }
}
