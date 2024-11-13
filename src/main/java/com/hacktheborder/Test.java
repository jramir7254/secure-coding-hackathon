package com.hacktheborder;

public class Test {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        addOne(arr);
        printArray(arr);
    }

    public static void addOne(int[] arr) {
        for (int i = 0; i < arr.length + 1; i++) {
            arr[i] += 1;
        }
    }

    public static void printArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i] + " ");
        }
    }
}

