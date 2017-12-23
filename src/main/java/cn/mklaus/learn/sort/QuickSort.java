package cn.mklaus.learn.sort;

import java.util.Random;

/**
 *
 * 快速排序
 *
 * 快排的运行时间依赖于划分是否平衡，而平衡与否又依赖于用户划分的主元素。
 *
 *      如果划分是平衡的，那么快速排序算法性能与归并排序一样。
 *      如果划分时不平衡的，那么快速排序的性能就接近于插入排序了。
 *
 * 最坏的情况
 *      当分区选取的基准元素为待排序元素中的最大或最小值时，时间复杂度和直接插入排序的一样，移动次数达到最大值
 *      Cmax = 1+2+...+(n-1) = n*(n-1)/2 = O(n2) 此时最好时间复杂为O(n2)
 *
 * 最好的情况
 *      当分区选取的基准元素为待排序元素中的"中值"，为最好的情况，时间复杂度为O(nlog2n)。
 *
 *
 * 空间复杂度
 *      (提示：思考调用栈)
 *      每次递归传参start和end，平均递归次数是log(n)次，所以平均空间复杂度是O(log(n))。最坏的情况是O(n)（初始是逆序的情况）。
 *
 * 当待排序元素类似[6,1,3,7,3]且基准元素为6时，
 * 经过分区，形成[1,3,3,6,7],两个3的相对位置发生了改变，所是快速排序是一种不稳定排序。
 *
 * 学习：DualPivotQuicksort
 *
 * @author Mklaus
 * @date 2017-12-23 上午11:04
 */
public class QuickSort {

    public static void sort(int[] array, int low, int high) {
       if (low < high) {
           int pivotIndex = partition(array, low, high);
           sort(array, low, pivotIndex - 1);
           sort(array, pivotIndex + 1, high);
       }
    }

    /**
     * 快速排序也有不足之处，比如对于元素较少或接近有序的数组来说，快速排序平均性能比插入排序差。
     * 这是因为小数组信息熵相对来说比较小（特别是经过一系列的快速排序调用以后），
     * 而插入排序在数据接近有序的情况下时间复杂度接近 O(N)O(N)，再加上快速排序递归调用也会有一些性能损耗。
     * 因此，针对小数组，我们可以加个判断，对小数组使用插入排序。
     * Java标准库自带的排序DualPivotQuicksort就是这么干的，INSERTION_SORT_THRESHOLD = 47。
     */
    public static final int INSERTION_SORT_THRESHOLD = 5;
    public static void sort2(int[] array, int low, int high) {
        if (low < high) {
            if (high - low < INSERTION_SORT_THRESHOLD) {
                InsertSort.sort(array, low, high);
            } else {
                int pivotIndex = partition(array, low, high);
                sort2(array, low, pivotIndex - 1);
                sort2(array, pivotIndex + 1, high);
            }
        }
    }

    private static int partition(int[] array, int low, int high) {

        int pivot = choosePivot(array, low, high);

        while (low < high) {

            while (low < high && array[high] >= pivot) {
                high--;
            }
            SortUtils.swap(array, low, high);


            while (low < high && array[low] <= pivot) {
                low++;
            }
            SortUtils.swap(array, low, high);

        }

        return low;
    }

    /**
     * 选择枢轴 三种方式
     *
     * fixedPivot   : 固定位置选取
     * randomPivot  : 随机位置选取
     * medianOfThree: 三值取中法
     *
     **/
    private static int choosePivot(int[] array, int low, int high) {
        int way = 1;

        switch (way) {
            case 1 : return fixedPivot(array, low, high);
            case 2 : return randomPivot(array, low, high);
            case 3 : return medianOfThree(array, low, high);
            default: return fixedPivot(array, low, high);
        }
    }

    private static int fixedPivot(int[] array, int low, int high) {
        return array[low];
    }

    private static int randomPivot(int[] array, int low, int high) {
        return new Random().nextInt(high - low + 1) + low;
    }

    private static int medianOfThree(int[] array, int low, int high) {
        int mid = low + ((high - low) >> 1);

        if (array[low] > array[high]) {
            SortUtils.swap(array, low, high);
        }

        if (array[mid] > array[high]) {
            SortUtils.swap(array, low, high);
        }

        if (array[mid] > array[low]) {
            SortUtils.swap(array, low, mid);
        }

        return array[low];
    }

}
