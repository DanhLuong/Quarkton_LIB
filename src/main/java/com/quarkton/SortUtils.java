package com.quarkton;

import java.util.Arrays;

import static com.quarkton.CommonUtils.swapByIdx;

public class SortUtils {
    private SortUtils() {
    }
    private static <T extends Comparable<T>> void bubbleSort(T[] arr) {

    }

    public static void bubbleSort(int[] arr) {
        for (int i = arr.length; i > 0; i--) {
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
            for (j=i; j>0 && arr[j-1]>current; j--) {
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

    public static void mergeSort(int[] arr, int start, int end) {
        if(end - start < 2) return;
        int mid = (start + end)/2;
        mergeSort(arr, start, mid);
        mergeSort(arr, mid, end);
        merge(arr, start, mid, end);
    }

    private static void merge(int[] arr, int start, int mid, int end) {
        if(arr[mid-1] <= arr[mid]) return;
        int i = start;
        int j = mid;
        int tempIdx = 0;
        int[] tempArr = new int[end - start];
        while (i<mid && j<end) {
//            if(arr[i] <= arr[j]) {
//                tempArr[tempIdx] = arr[i];
//                i++;
//            } else {
//                tempArr[tempIdx] = arr[j];
//                j++;
//            }
//            tempIdx++;
            tempArr[tempIdx++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
//        int iStart=i;
//        while (i < mid) {
//            arr[start+tempIdx+i-iStart] = arr[i];
//            i++;
//        }
//
//        for (int k=start; k< start+tempIdx; k++) {
//            arr[k] = tempArr[k-start];
//        }
        System.arraycopy(arr, i, arr, start + tempIdx, mid-i);
        System.arraycopy(tempArr, 0, arr, start, tempIdx);
    }
    public static void quickSort(int[] arr, int start, int end) {
        if(end - start < 2) return;
        int pivotIdx = partition(arr, start, end);
        quickSort(arr, start, pivotIdx);
        quickSort(arr, pivotIdx+1, end);
    }

    private static int partition(int[] arr, int start, int end) {
        int pivot = arr[start];
        int right = end;
        int left = start+1;
        while(right >0 && arr[--right] >= pivot);
        while(left <= right) {
            if(arr[left] >= pivot) {
                swapByIdx(arr, left, right);
                while(right >0 && arr[--right] >= pivot);
            }
            left++;
        }
        swapByIdx(arr, start, left-1);
        return left-1;
//        int i = start+1;
//        int pivot = arr[start];
//        for (int j=start+1; j<end; j++) {
//            if(arr[j]<pivot) {
//                swapByIdx(arr, i, j);
//                i++;
//            }
//        }
//        swapByIdx(arr, start, i-1);
//        return i-1;
    }

}
