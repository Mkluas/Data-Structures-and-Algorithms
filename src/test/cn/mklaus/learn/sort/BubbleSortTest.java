package cn.mklaus.learn.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author Mklaus
 * @date 2017-12-21 下午6:04
 */
public class BubbleSortTest extends BaseSortTest {


    @Test
    public void sort1() throws Exception {
        System.out.println("Bubble sort1:");
        BubbleSort.sort1(array);
        SortUtils.printArray(array);
    }

    @Test
    public void sort2() throws Exception {
        System.out.println("Bubble sort2:");
        BubbleSort.sort2(array);
        SortUtils.printArray(array);
    }


    @Test
    public void sort3() throws Exception {
        System.out.println("Bubble sort3:");
        BubbleSort.sort3(array);
        SortUtils.printArray(array);
    }
}