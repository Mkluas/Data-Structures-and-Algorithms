package cn.mklaus.learn.sort;

/**
 * 基数排序
 *
 * LSD: Least significant digital       最低位优先
 *
 *  求最高位
 *  for （当前位： 0 -> 高位） {
 *      以当前位分配
 *      收集
 *  }
 *
 * MSD: Most Significant Digit first    最高位优先
 *
 *  求最高位，
 *  msd(array, 高位)
 *
 *  function msd(array，bitCount) {
 *      以bitCount分配，
 *      if （单个桶中数值个数大于1个，且未到以个数分配） {
 *          msd(该桶，降位（bitCount - 1) )
 *      }
 *      收集
 *  }
 *
 * @author Mklaus
 * @date 2018-02-06 下午1:37
 */
public class RadixSort {

    private static final int RADIX = 10;

    public static void sortByLSD(int[] array) {
        int length = array.length;
        int divisor = 1;

        int[][] bucket = new int[RADIX][length];
        int[] count = new int[RADIX];

        int maxBit = getMaxBitCount(array);
        int digit;
        for (int i = 0; i < maxBit; i++) {

            for (int j = 0; j < length; j++) {
                digit = array[j] / divisor % RADIX;
                bucket[digit][count[digit]++] = array[j];
            }

            int index = 0;
            for (int j = 0; j < RADIX; j++) {
                if (count[j] > 0) {
                    for (int k = 0; k < count[j]; k++) {
                        array[index++] = bucket[j][k];
                    }

                    count[j] = 0;
                }
            }

            divisor *= RADIX;

        }
    }


    public static void sortByMSD(int[] array) {
        int length = array.length;
        int maxBitCount = getMaxBitCount(array);
        sortByMSDInternal(array, length, maxBitCount);
    }

    private static void sortByMSDInternal(int[] array, int length, int bitCount) {
        // 分配RADIX个桶，每个桶的容量是length。
        int[][] bucket = new int[RADIX][length];
        // 长度为RADIX的数组，记录对应每个桶放的数值的个数。
        int[] count = new int[RADIX];

        int divisor = getDivisorByBitCount(bitCount);
        int digit;
        for (int i = 0; i < length; i++) {
            digit = array[i] / divisor % RADIX;
            bucket[digit][count[digit]++] = array[i];
        }

        for (int i = 0; i < RADIX; i++) {
            if (count[i] > 1 && bitCount > 1) {
                // 当桶中的数量大于1，而且没有分配到个位时， 继续递归分配
                sortByMSDInternal(bucket[i], count[i], bitCount - 1);
            }
        }

        int index = 0;
        for (int i = 0; i < RADIX; i++) {
            for (int j = 0; j < count[i]; j++) {
                array[index++] = bucket[i][j];
            }
        }
    }

    /**
     * 从数组中获取最大的数，并计算其位数长度。
     * @param array     数组
     * @return
     */
    private static int getMaxBitCount(int[] array) {
        int max = SortUtils.findMax(array);
        return String.valueOf(max).length();
    }

    /**
     * 本质是: 整数版的Math.pow(10, bitCount - 1)
     *
     *      1 -> 1, 2 -> 10, 3 -> 100
     *
     * @param bitCount      位数
     * @return
     */
    private static int getDivisorByBitCount(int bitCount) {
        int divisor = 1;
        for (int i = 1; i < bitCount; i++) {
            divisor *= RADIX;
        }
        return divisor;
    }

}
