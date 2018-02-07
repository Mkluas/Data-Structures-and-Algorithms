package cn.mklaus.learn.sort;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Mklaus
 * @date 2018-02-07 下午4:09
 */
public class BucketSortTest extends BaseSortTest{
    @Test
    public void sort() throws Exception {
        BucketSort.sort(array);
    }

}