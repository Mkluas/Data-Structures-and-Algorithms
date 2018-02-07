package cn.mklaus.learn.sort;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Mklaus
 * @date 2018-02-07 下午3:10
 */
public class CountingSortTest extends BaseSortTest {
    @Test
    public void sort() throws Exception {
        CountingSort.sort(array);
    }

}