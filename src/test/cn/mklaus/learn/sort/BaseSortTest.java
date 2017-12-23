package cn.mklaus.learn.sort;

import org.junit.After;
import org.junit.Before;

import java.util.Arrays;

/**
 * @author Mklaus
 * @date 2017-12-23 上午11:27
 */
public class BaseSortTest {

    int[] array;

    @Before
    public void setUp() throws Exception {
        this.array = new int[]{6, 3, 9, 4, 7, 1, 2, 8, 0, 5};
        System.out.println("Init " + Arrays.toString(array) + "\n");
    }

    @After
    public void tearDown() throws Exception {
    }


}
