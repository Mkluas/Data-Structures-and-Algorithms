package cn.mklaus.learn.sort;

/**
 * 堆排序
 *
 * 最差时间分析	平均时间复杂度	稳定度	空间复杂度
 * O(nlog2n)    O(nlog2n)	    不稳定 	O(1)
 *
 * 堆排序是一种不稳定的排序方法：
 *
 *      因为在堆的调整过程中，关键字进行比较和交换所走的是该结点到叶子结点的一条路径
 *      当发生交互时 n <=> 2n + 1,  则 n+1 -> 2n 这些节点就会排在 2n+1 的后面
 *
 * 时间复杂度：
 *
 *      堆的存储表示是顺序的。因为堆所对应的二叉树为完全二叉树，而完全二叉树通常采用顺序存储方式。
 *      当想得到一个序列中第k个最小的元素之前的部分排序序列，最好采用堆排序。
 *      因为堆排序的时间复杂度是O(n+klog2n)，若k≤n/log2n，则可得到的时间复杂度为O(n)。
 *
 *
 * @author Mklaus
 * @date 2018-02-06 上午11:31
 */
public class HeapSort {

    public static void sort(int[] array) {

        /**
         * 成堆
         *
         * (array.length / 2 - 1) 是最末尾的一个【非叶子节点】。
         * 选这个节点的原因是保证了每一个节点都参数了堆交换。
         * 而叶子节点没有子节点，是没有堆交互的意义，所有要选非叶子节点开始。
         *
         * array[i] <= array[2 * i + 1] && array[i] <= array[2 * i + 2] 小顶堆
         * array[i] >= array[2 * i + 1] && array[i] >= array[2 * i + 2] 大顶堆
         *
         */
        for (int i = (array.length / 2 - 1); i >= 0; i--) {
            headAdjust(array, i, array.length);
        }

        /**
         * 将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
         * 重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。
         */
        for (int i = array.length - 1; i >= 0; i--) {

            // 沉"到数组末端
            SortUtils.swap(array, 0, i);

            // 重新调整结构
            headAdjust(array, 0, i);
        }

    }

    public static void headAdjust(int[] array, int parent, int length) {
        int temp = array[parent];
        int child = 2 * parent + 1;

        while (child < length) {

            // 对比左右子节点大小； 在测示例是选择大的
            if (child + 1 < length && array[child] < array[child + 1]) {
                child++;
            }

            if (array[child] > temp) {
                array[parent] = array[child];
                parent = child;
                child = parent * 2 + 1;
            } else {
                // 符合堆定义，退出对比交互
                break;
            }

        }

        array[parent] = temp;
    }

}
