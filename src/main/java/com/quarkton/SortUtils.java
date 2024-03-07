package com.quarkton;

import java.util.Arrays;

import static com.quarkton.CommonUtils.swapByIdx;

public class SortUtils {
    private SortUtils() {
    }
    private static <T extends Comparable<T>> void bubbleSort(T[] arr) {

    }

    public static void bubbleSort(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < (i - 1); j++) {
                if (arr[j] > arr[j + 1]) swapByIdx(arr, j, j + 1);
            }
        }
    }

    public static void selectionSort(int[] arr) {
        int len = arr.length;
        for (int end = len - 1; end >= 0; end--) {
            int maxIdx = 0;
            for (int i = 0; i <= end; i++) {
                if (arr[i] > arr[maxIdx]) maxIdx = i;
            }
            swapByIdx(arr, maxIdx, end);
        }
    }

    public static void insertionSort(int[] arr) {
        int len = arr.length;
        for (int i=0; i<len; i++) {
            int current = arr[i];
            int j;
            for (j=i; j>0 && arr[j-1] > current; j--) {
                arr[j] =arr[j-1];
            }
            arr[j] = current;
        }
    }

    public static void shellSort(int[] arr) {
        int len = arr.length;
        for(int gap=len/2; gap>0; gap/=2) {
            for (int i=gap; i<len; i++) {
                int current = arr[i];
                int j;
                for (j=i; j>=gap && arr[j-gap] > current; j-=gap) {
                    arr[j] = arr[j-gap];
                }
                arr[j] = current;
            }
        }
    }
}
