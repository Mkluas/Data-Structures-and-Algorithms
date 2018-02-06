package cn.mklaus.learn.sort;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Mklaus
 * @date 2018-02-06 上午11:41
 */
public class HeapSortTest extends BaseSortTest {
    @Test
    public void sort() throws Exception {
        HeapSort.sort(array);
        SortUtils.printArray(array);
    }

}