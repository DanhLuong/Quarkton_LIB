package com.quarkton;

public class CommonUtils {
    private CommonUtils() {}
    public static void swapByIdx(int[] arr, int idx1, int idx2) {
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }
}
