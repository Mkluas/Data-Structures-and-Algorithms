package cn.mklaus.learn.sort;

/**
 *
 * 冒泡排序
 *
 * @author Mklaus
 * @date 2017-12-21 下午5:49
 */
public class BubbleSort {


    public static void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }


    public static void upgradeSort(int[] array) {
        boolean exchange = true;

        for (int i = 1; i < array.length && exchange; i++) {
            exchange = false;

            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    exchange = true;
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }

        }
    }


}
