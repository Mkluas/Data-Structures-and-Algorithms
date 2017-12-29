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

}
