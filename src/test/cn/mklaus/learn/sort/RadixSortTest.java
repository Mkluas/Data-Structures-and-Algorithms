package cn.mklaus.learn.sort;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Mklaus
 * @date 2018-02-06 下午2:05
 */
public class RadixSortTest extends BaseSortTest {
    @Test
    public void sortByLSD() throws Exception {
        RadixSort.sortByLSD(array);
    }

    @Test
    public void sortByMSD() throws Exception {
        RadixSort.sortByMSD(array);
    }

}