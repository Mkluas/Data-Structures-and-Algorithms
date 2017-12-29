package cn.mklaus.learn.sort;

/**
 *
 * 归并排序
 *
 * 最差时间分析	平均时间复杂度	稳定度	空间复杂度
 * O(nlog2n)	O(nlog2n)	    稳定 	O(n)
 *
 * @author Mklaus
 * @date 2017-12-29 下午5:19
 */
public class MergeSort {

    public static void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    public static void sort(int[] array, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            sort(array, low, mid);
            sort(array, mid + 1, high);
            merge(array, low, mid, high);
        }
    }

    private static void merge(int[] array, int low, int mid, int high) {
        int leftLength = mid - low + 1;
        int rightLength = high - mid;
        int[] leftArray = new int[leftLength];
        int[] rightArray = new int[rightLength];

        for (int i = 0; i < leftLength; i++) {
            leftArray[i] = array[low + i];
        }

        for (int i = 0; i < rightLength; i++) {
            rightArray[i] = array[mid + 1 + i];
        }

        int r = 0;
        int l = 0;
        int a = low;
        while (l < leftLength && r < rightLength) {
            if (leftArray[l] > rightArray[r]) {
                array[a++] = leftArray[l++];
            } else {
                array[a++] = rightArray[r++];
            }
        }

        while (l < leftLength) {
            array[a++] = leftArray[l++];
        }

        while (r < rightLength) {
            array[a++] = rightArray[r++];
        }
    }

}
