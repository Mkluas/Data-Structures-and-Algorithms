package cn.mklaus.learn.sort;

/**
 *
 * 插入排序
 *
 * 最差时间分析	平均时间复杂度	稳定度	空间复杂度
 * O(n2)	    O(n2)	        稳定 	O(1)
 *
 *
 * 折半插入排序
 *
 *  时间复杂度：不难看出，折半插入排序仅仅是减少了比较元素的次数，约为O(nlogn)，
 *  而且该比较次数与待排序表的初始状态无关，仅取决于表中的元素个数n；而元素的移动次数没有改变，它依赖于待排序表的初始状态。
 *  因此，折半插入排序的时间复杂度仍然为O(n²)，但它的效果还是比直接插入排序要好。
 *
 *  空间复杂度：很显然，排序只需要一个位置来暂存元素，因此空间复杂度为O（1）。
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


    /**
     * 改进 折半插入排序
     * @param array     数组
     */
    public static void binarySort(int[] array) {

        int temp;
        int high;
        int low;
        for (int i = 1; i < array.length; i++) {
            // 暂存元素
            temp = array[i];

            // 找插入点
            low = 0;
            high = i - 1;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (array[mid] > temp) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            // 在包括插入点以及后面元素 统一后移，空出插入位置
            for (int j = i - 1; j > high; j--) {
                array[j + 1] = array[j];
            }

            // 插入元素
            array[high + 1] = temp;
        }

    }

}
