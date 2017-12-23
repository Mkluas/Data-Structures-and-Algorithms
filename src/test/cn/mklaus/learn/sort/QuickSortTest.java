package cn.mklaus.learn.sort;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author Mklaus
 * @date 2017-12-23 上午11:26
 */
public class QuickSortTest extends BaseSortTest {
    @Test
    public void sort() throws Exception {
        QuickSort.sort(array, 0, array.length - 1);
        SortUtils.printArray(array);
    }


    @Test
    public void sort2() throws Exception {
        QuickSort.sort2(array, 0, array.length - 1);
        SortUtils.printArray(array);
    }
}