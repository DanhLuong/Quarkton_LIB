package com.quarkton;

import java.util.Random;

import com.quarkton.CommonUtils;

public class ArrayUtils {
    private ArrayUtils(){}
    /**
     * Make a copy array from original array from start index (Inclusive) to end index (exclusive)
     *
     * @param arr the array to copy
     * @param startIdx start index inclusive
     * @param endIdx end index exclusive
     */
     public static int[] copy(int[] arr, int startIdx, int endIdx) {
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
        if(arr1.length != arr2.length) throw new IllegalArgumentException("Can not compare arrays with different length");
        for (int i = 0; i < arr1.length; i++) {
            if(arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }
}
