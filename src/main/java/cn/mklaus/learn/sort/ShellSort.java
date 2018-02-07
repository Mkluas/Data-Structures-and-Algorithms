package cn.mklaus.learn.sort;

/**
 * 希尔排序
 *
 *      希尔排序也成为“缩小增量排序”，其基本原理是，现将待排序的数组元素分成多个子序列，使得每个子序列的元素个数相对较少，
 *      然后对各个子序列分别进行直接插入排序，待整个待排序列“基本有序”后，最后在对所有元素进行一次直接插入排序。
 *
 * @author Mklaus
 * @date 2018-02-07 下午4:29
 */
public class ShellSort {
    
    public static void sort(int[] array) {
        int j;

        for (int increment = array.length / 2; increment > 0; increment /= 2) {

            for (int i = increment; i < array.length; i++) {
                int temp = array[i];
                for (j = i - increment; j >= 0; j -= increment) {
                    if (array[j] > temp) {
                        array[j + increment] = array[j];
                    } else {
                        break;
                    }
                }
                array[j + increment] = temp;
            }

        }
    }
    
}
