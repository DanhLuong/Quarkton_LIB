package com.quarkton;

import java.util.Random;

public class ArrayUtils {
    private ArrayUtils(){}
    /**
     * Make a copy array from original array from start index (Inclusive) to end index (exclusive)
     *
     * @param arr the array to copy
     * @param startIdx start index inclusive
     * @param endIdx end index exclusive
     * @throws OutOfMemoryError exception when array have large size. Adjust heap size using argument -Xmx[megabytes]m
     */
     public static int[] copy(int[] arr, int startIdx, int endIdx) throws OutOfMemoryError{
         int len = arr.length;
         if(startIdx < 0 || startIdx >len || endIdx < 0 || endIdx >len) {
             throw new IllegalArgumentException("Invalid start / end index");
         }
         if(startIdx > endIdx) {
             throw new IllegalArgumentException("Start index is bigger than end index");
         }
         int[] copy = new int[endIdx - startIdx];
         for (int i = startIdx; i < endIdx; i++) {
             copy[i-startIdx] = arr[i];
         }
         return copy;
     }
    public static int[] copyParallel(int[] arr, int startIdx, int endIdx) throws OutOfMemoryError{
        int len = arr.length;
        if(startIdx < 0 || startIdx >len || endIdx < 0 || endIdx >len) {
            throw new IllegalArgumentException("Invalid start / end index");
        }
        if(startIdx > endIdx) {
            throw new IllegalArgumentException("Start index is bigger than end index");
        }
        int[] copy = new int[endIdx - startIdx];
        int segmentNumber = Runtime.getRuntime().availableProcessors();
        CopyThread[] threads = new CopyThread[segmentNumber];
        int segmentSize = (endIdx-startIdx) / segmentNumber;
        if(segmentSize < 1) {
            throw new IllegalArgumentException("Array size too small for copyParallel");
        } else {
            for (int i = 0; i < segmentNumber; i++) {
                int startSegmentIndex = i * segmentSize;
                int endSegmentIndex = (i == segmentNumber-1) ? len : (i + 1) * segmentSize;
                threads[i] = new CopyThread(arr, copy, startSegmentIndex, endSegmentIndex, i+"");
                threads[i].start();
            }
            try {
                for (CopyThread thread : threads) {
                    thread.join();
                }
            } catch (InterruptedException e) {
                System.out.println("concurrent failed");
            }
        }
        return copy;
    }

    static class CopyThread extends Thread {
        private final int[] sourceArray;
        private final int[] destinationArray;
        private final int startIdx;
        private final int endIdx;

        public CopyThread(int[] sourceArray, int[] destinationArray, int startIndex, int endIndex, String name) {
            this.sourceArray = sourceArray;
            this.destinationArray = destinationArray;
            this.startIdx = startIndex;
            this.endIdx = endIndex;
            this.setName(name);
        }

        @Override
        public void run() {
            for (int i = startIdx; i < endIdx; i++) {
                destinationArray[i] = sourceArray[i];
            }
        }
    }
    /**
     * Make a copy array from original array
     *
     * @param arr the array to copy
     */
    public static int[] copy(int[] arr) {
        return copy(arr, 0, arr.length);
    }
    /**
     * Reverse array in place
     *
     * @param arr the array to reverse
     */
    public static void reverseInPlace(int[] arr) {
        int len = arr.length;
        for (int i = 0; i<len/2; i++) {
            CommonUtils.swapByIdx(arr, i, len-1-i);
        }
    }
    /**
     * Make a reverse copy array from original array from start index (Inclusive) to end index (exclusive)
     *
     * @param arr the array to copy and reverse
     * @param startIdx start index inclusive
     * @param endIdx end index exclusive
     */
    public static int[] copyReverse(int[] arr, int startIdx, int endIdx) {
        int len = arr.length;
        if(startIdx < 0 || startIdx >len || endIdx < 0 || endIdx >len) {
            throw new IllegalArgumentException("Invalid start / end index");
        }
        if(startIdx > endIdx) {
            throw new IllegalArgumentException("Start index is bigger than end index");
        }
        int[] copy = new int[endIdx - startIdx];
        for (int i = startIdx; i < endIdx; i++) {
            copy[i-startIdx] = arr[i];
        }
        reverseInPlace(copy);
        return copy;
    }
    /**
     * Make a reverse copy array from original array
     *
     * @param arr the array to copy and reverse
     */
    public static int[] copyReverse(int[] arr) {
        return copyReverse(arr, 0, arr.length);
    }
    /**
     * Shuffle the array (in place) using a Fisher-Yates shuffle algorithm
     *
     * @param arr the array to shuffle
     */
    public static void shuffle(int[] arr) {
        Random random = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int randomIdx = random.nextInt(i + 1);
            CommonUtils.swapByIdx(arr, i, randomIdx);
        }
    }
    public static boolean compare(int[] arr1, int[] arr2) {
        if(arr1 == arr2) return true;
        if(arr1.length != arr2.length) return false;
        for (int i = 0; i < arr1.length; i++) {
            if(arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static int containAtIdx(int[] container, int[] inner) {
        if(container.length < inner.length) return -1;
        outer:
        for (int i = 0; i < container.length-inner.length+1; i++) {
            for(int j=i; j<i+inner.length; j++) {
                if(container[j] != inner[j-i]) continue outer;
            }
            return i;
        }
        return -1;
    }
}
