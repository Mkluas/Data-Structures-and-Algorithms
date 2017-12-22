package cn.mklaus.learn.sort;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author Mklaus
 * @date 2017-12-21 下午6:04
 */
public class BubbleSortTest {

    int[] array;

    @Before
    public void setUp() throws Exception {
        this.array = new int[]{6, 3, 9, 4, 7, 1, 2, 8, 0, 5};
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void sort1() throws Exception {
        System.out.println("Bubble sort1:");
        System.out.println("Initial array = " + Arrays.toString(array));
        BubbleSort.sort1(array);
        System.out.println("Sorted array  = " + Arrays.toString(array));
    }

    @Test
    public void sort2() throws Exception {
        System.out.println("Bubble sort2:");
        System.out.println("Initial array = " + Arrays.toString(array));
        BubbleSort.sort2(array);
        System.out.println("Sorted array  = " + Arrays.toString(array));
    }


    @Test
    public void sort3() throws Exception {
        System.out.println("Bubble sort3:");
        System.out.println("Initial array = " + Arrays.toString(array));
        BubbleSort.sort3(array);
        System.out.println("Sorted array  = " + Arrays.toString(array));
    }
}