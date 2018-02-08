package cn.mklaus.learn.sort;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Mklaus
 * @date 2017-12-29 下午4:46
 */
public class SelectionSortTest extends BaseSortTest {


    @Test
    public void sort() throws Exception {
        SelectionSort.sort(array);
    }

    @Test
    public void sort2() throws Exception {
        SelectionSort.sort2(array);
    }

}