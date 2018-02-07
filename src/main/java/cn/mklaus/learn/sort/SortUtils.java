package cn.mklaus.learn.sort;

import java.util.Arrays;

/**
 * @author Mklaus
 * @date 2017-12-23 下午3:40
 */
public class SortUtils {

    public static void printArray(int[] array) {
        System.out.println("Array: " + Arrays.toString(array));
    }

    public static void printArray(int[] array, String intro) {
        System.out.println(intro + " array: " + Arrays.toString(array));
    }

    public static void printArray(int[][] array) {
        System.out.println("Array:");
        for (int i = 0; i < array.length; i++) {
            System.out.println(i + ": " + Arrays.toString(array[i]));
        }
    }

    public static void swap(int[] array, int form, int to) {
        int temp = array[form];
        array[form] = array[to];
        array[to] = temp;
    }

    public static int findMax(int[] array) {
        if (array.length < 1) {
            throw new IllegalArgumentException("Array length must empty");
        }
        int max = array[0];
        for (int value :
                array) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public static int findMin(int[] array) {
        if (array.length < 1) {
            throw new IllegalArgumentException("Array length must empty");
        }
        int min = array[0];
        for (int value :
                array) {
            if (value < min) {
                min = value;
            }
        }
        return min;
    }

}
