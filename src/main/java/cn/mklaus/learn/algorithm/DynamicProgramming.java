package cn.mklaus.learn.algorithm;

import cn.mklaus.learn.sort.SortUtils;

import java.util.HashMap;

/**
 * @author Mklaus
 * @date 2018-02-08 下午3:15
 */
public class DynamicProgramming {

    /**
     * 问题：有一座高度是10级台阶的楼梯，从下往上走，每跨一步只能向上1级或者2级台阶。要求用程序来求出一共有多少种走法。
     */

    /**
     * 简单递归
     *
     * 三个重要概念
     *
     *  状态转移公式：F(n) = F(n - 1) + F(n - 2);
     *  最优子结构：F(9),F(8) 是 F(10) 的最优子结构
     *  边界： F(1),F(2) 是 该问题的边界
     *
     *  该解决方法的时间复杂度： 2的n次方。
     *
     * @param n
     * @return
     */
    public static int climbingWaysRecursion(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("The value of floor must larger than 0 :" + n);
        }
        if (n < 3) {
            return n;
        } else {
            return climbingWays(n - 1) + climbingWays(n - 2);
        }
    }


    /**
     * 备忘录模式
     *
     * 时间复杂度 O(n)  空间复杂度O(n)
     *
     * @param n     台阶数
     * @param map   map
     * @return
     */
    public static int climbingWaysNote(int n, HashMap<Integer, Integer> map) {
        if (n < 1) {
            throw new IllegalArgumentException("The value of floor must larger than 0 :" + n);
        }
        if (n < 3) {
            return n;
        }

        if (map.containsKey(n)) {
            return map.get(n);
        } else {
            int ways = climbingWays(n - 1) + climbingWays(n - 2);;
            map.put(n, ways);
            return ways;
        }
    }


    /**
     * 动态规划
     *
     * 时间复杂度 O(n)  空间复杂度O(1)
     *
     * @param n     台阶数
     * @return
     */
    public static int climbingWays(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("The value of floor must larger than 0 :" + n);
        }
        if (n < 3) {
            return n;
        }

        int a = 1;
        int b = 2;
        int ways = 0;
        for (int i = 2; i < n; i++) {
            ways = a + b;
            a = b;
            b = ways;
        }
        return ways;
    }


    /**
     * 假设现有容量10kg的背包，另外有3个物品，分别为a1，a2，a3。
     * 物品a1重量为3kg，价值为4；物品a2重量为4kg，价值为5；物品a3重量为5kg，价值为6。
     * 将哪些物品放入背包可使得背包中的总价值最大？
     */


    /**
     * 简单递归
     *
     * @param m     背包容量
     * @param n     物品数量
     * @param g     物品质量
     * @param p     物品价值
     * @return
     */
    public static int backpackRecursion(int m, int n, int[] g, int[] p) {
        if (n < 2) {
            return (m >= g[n - 1]) ? p[n - 1] : 0;
        }

        if (m < g[n - 1]) {
            return 0;
        }

        return Math.max(backpackRecursion(m, n - 1, g, p), backpackRecursion(m - g[n - 1], n - 1, g, p) + p[n - 1]);
    }


    /**
     * 备忘录模式
     *
     * @param m     背包容量
     * @param n     物品数量
     * @param g     物品质量
     * @param p     物品价值
     * @return
     */
    public static int backpackNote(int m, int n, int[] g, int[] p, HashMap<String, Integer> map) {
        if (n < 1) {
            return (m >= g[n]) ? p[n] : 0;
        }

        if (m < g[n]) {
            return 0;
        }

        String key = m + "_" + n;
        if (map.containsKey(key)) {
            return map.get(key);
        } else {
            int value = Math.max(backpackRecursion(m, n - 1, g, p), backpackRecursion(m - g[n], n - 1, g, p) + p[n]);
            map.put(key, value);
            return value;
        }
    }


    /**
     * 动态规划
     *
     * @param m     背包容量
     * @param n     物品数量
     * @param g     物品质量
     * @param p     物品价值
     * @return
     */
    public static int backpack(int m, int n, int[] g, int[] p) {
        int[] preResult = new int[m + 1];
        int[] result = new int[m + 1];

        for (int i = 0; i < n; i++) {

            for (int j = 0; j <= m; j++) {
                if (j < g[i]) {
                    result[j] = preResult[j];
                } else {
                    result[j] = Math.max(preResult[j], preResult[j - g[i]] + p[i]);
                }
            }

            System.arraycopy(result, 0, preResult, 0, result.length);

        }

        return result[m];
    }



}
