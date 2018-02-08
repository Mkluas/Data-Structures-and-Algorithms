package cn.mklaus.learn.sort;

/**
 * 选择排序
 *
 * 最差时间分析	平均时间复杂度	稳定度	空间复杂度
 * O(n2)	    O(n2)	        不稳定 	O(1)
 *
 * @author Mklaus
 * @date 2017-12-25 下午3:34
 */
public class SelectionSort {

    public static void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int max = array[i];
            int index = i;
            for (int j = i + 1; j < array.length; j++) {
                if (max < array[j]) {
                    max = array[j];
                    index = j;
                }
            }
            if (i != index) {
                SortUtils.swap(array, i, index);
            }
        }
    }


    /**
     * 选择最大的 和 选择最小的 同时进行选择排序
     * @param array 数组
     */
    public static void sort2(int[] array) {
        for (int i = 0; i <= array.length / 2; i++) {
            int max = i;
            int min = i;
            for (int j = i + 1; j < array.length - i; j++) {
                if (array[max] < array[j]) {
                    max = j; continue;
                }
                if (array[min] > array[j]) {
                    min = j;
                }
            }

            // 顺序不能乱，先交换末尾的（array.length - 1 - i）, 在交互开始的 i
            SortUtils.swap(array, min, array.length - 1 - i);
            SortUtils.swap(array, max, i);
        }
    }

}
