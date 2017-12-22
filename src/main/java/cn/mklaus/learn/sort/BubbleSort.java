package cn.mklaus.learn.sort;

/**
 *
 * 冒泡排序
 *
 * BubbleSortTest：
 *
 *      测试数据：{6, 3, 9, 4, 7, 1, 2, 8, 0, 5}
 *
 *      测试结果(runCount)：
 *              sort1 : 81
 *              sort2 : 45
 *              sort3 : 18
 *
 * @author Mklaus
 * @date 2017-12-21 下午5:49
 */
public class BubbleSort {

    /**
     * * 本示例是以降序作为默认排序
     * （改升序，直接将冒泡条件 array[form] < array[to] 改为 array[from] > array[to]）
     */
    private static boolean shouldExchange(int[] array, int form, int to) {
        return array[form] < array[to];
    }


    private static void swap(int[] array, int form, int to) {
        int temp = array[form];
        array[form] = array[to];
        array[to] = temp;
    }

    /**
     * 两个for循环：
     *      如何冒泡？也就是说，把数组中最小的那个往上冒，冒的过程就是和他相邻的元素交换。这个冒的过程就是内循环。
     *      经过了一个冒的过程，可以使一个最小的元素冒出来，如果数组里面有 n 个元素，就得冒 n-1 次，这就是外循环。
     *
     * @param array
     */
    public static void sort1(int[] array) {
        int runCount = 0;

        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                runCount++;

                if (shouldExchange(array, j, j + 1)) {
                    swap(array, j, j + 1);
                }
            }
        }

        System.out.println("runCount = " + runCount);
    }


    /**
     * 对于sort1方法，内循环每次都是对比了（array.length - 1）次。
     * 思考下，这是不必要的。
     * 为什么？
     *
     *      这是因为每经过一个外循环，就会有一个最大值排到后面。
     *      意味着，内循环所要对比交换的元素就少一个。
     *      那么，内循环就可以根据外循环的次数来相应的减少对比冒泡次数。
     *      则内循环的区间就等于 [0 ~ (array.length - i)]。
     *
     * @param array
     */
    public static void sort2(int[] array) {
        int runCount = 0;

        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < array.length - i; j++) {
                runCount++;

                if (shouldExchange(array, j, j + 1)) {
                    swap(array, j, j + 1);
                }
            }
        }

        System.out.println("runCount = " + runCount);
    }


    /**
     * 算法改进。
     *
     * 冒泡是前后元素的比较交换。
     * 试想下，如果在一轮冒泡中，没有一个元素与相邻的元素交换
     * 那么则可以说明，每一个元素都比前一个元素小 （或者每一个元素都比前一个元素大）
     * 即有序，已经排好序了，那么我们可以提前中止冒泡排序，返回正确的结果。
     *
     * exchange初始化true, 进入循环。
     * 每次开始内循环的时候，我们都假设没有交换过 exchange = false;
     *
     * 如果有交换过，exchange = true。 在下次会继续进入内循环。
     * 否则，exchange = false, 会在内循环结束的时候，直接导致程序会跳出外循环，即数组已经排好序。直接返回。
     *
     * @param array
     */
    public static void sort3(int[] array) {
        int runCount = 0;

        boolean exchange = true;
        for (int i = 1; i < array.length && exchange; i++) {
            exchange = false;

            for (int j = 0; j < array.length - 1; j++) {
                if (shouldExchange(array, j, j + 1)) {
                    runCount++;
                    exchange = true;
                    swap(array, j, j + 1);
                }
            }

        }

        System.out.println("runCount = " + runCount);
    }

}
