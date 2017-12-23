package cn.mklaus.learn.sort;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Mklaus
 * @date 2017-12-23 下午3:43
 */
public class InsertSortTest extends BaseSortTest {
    @Test
    public void sort() throws Exception {
        InsertSort.sort(array);
        SortUtils.printArray(array);
    }

}