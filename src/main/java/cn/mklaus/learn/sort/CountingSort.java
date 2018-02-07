package cn.mklaus.learn.sort;

/**
 * 计数排序
 *
 *      计数排序需要占用大量空间，它仅适用于数据比较集中的情况。比如 [0~100]
 *
 * @author Mklaus
 * @date 2018-02-07 下午3:01
 */
public class CountingSort {

    public static void sort(int[] array) {
        int max = SortUtils.findMax(array);

        // 求min值，可以节省分配空间
        int min = SortUtils.findMin(array);
        int[] temp = new int[max - min + 1];

        for (int i = 0; i < array.length; i++) {
            temp[array[i] - min]++;
        }

        for (int i = 0, index=0; i < temp.length; i++) {
            for (int j = 0; j < temp[i]; j++) {
                array[index++] = i + min;
            }
        }
    }

}
