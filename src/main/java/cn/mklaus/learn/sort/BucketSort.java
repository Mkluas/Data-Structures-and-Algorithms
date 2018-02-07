package cn.mklaus.learn.sort;

/**
 *  桶排序
 *
 *
 * @author Mklaus
 * @date 2018-02-07 下午3:37
 */
public class BucketSort {

    public static void sort(int[] array) {
        int length = array.length;
        int max = SortUtils.findMax(array);
        int min = SortUtils.findMin(array);
        int bucketSize = (max - min) / length + 1;

        int[][] bucket = new int[bucketSize][length];
        int[] count = new int[bucketSize];

        // 分桶
        for (int i = 0; i < array.length; i++) {
            int num = (array[i] - min) / array.length;
            bucket[num][count[num]++] = array[i];
        }

        // 每个桶进行桶内排序
        for (int i = 0; i < bucket.length; i++) {
            int[] temp = new int[count[i]];
            System.arraycopy(bucket[i], 0, temp, 0, count[i]);
            InsertSort.sort(temp);
            bucket[i] = temp;
        }

        // 合并
        for (int i = 0, index = 0; i < bucket.length; i++) {
            for (int j = 0; j < count[i]; j++) {
                array[index++] = bucket[i][j];
            }
        }

    }

}
