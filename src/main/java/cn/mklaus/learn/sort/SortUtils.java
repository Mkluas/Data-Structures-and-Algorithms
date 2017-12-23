package cn.mklaus.learn.sort;

import java.util.Arrays;

/**
 * @author Mklaus
 * @date 2017-12-23 下午3:40
 */
public class SortUtils {

    public static void swap(int[] array, int form, int to) {
        int temp = array[form];
        array[form] = array[to];
        array[to] = temp;
    }

    public static void printArray(int[] array) {
        System.out.println("Array: " + Arrays.toString(array));
    }

}
