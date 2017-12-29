package cn.mklaus.learn.sort;

/**
 *
 * 插入排序
 *
 * 最差时间分析	平均时间复杂度	稳定度	空间复杂度
 * O(n2)	    O(n2)	        稳定 	O(1)
 *
 * @author Mklaus
 * @date 2017-12-23 下午3:33
 */
public class InsertSort {

    public static void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    public static void sort(int[] array, int low, int high) {
        for (int i = low + 1; i <= high - low; i++) {
            for (int j = i; j > low && array[j] < array[j - 1]; j--) {
                SortUtils.swap(array, j, j - 1);
            }
        }
    }

}
