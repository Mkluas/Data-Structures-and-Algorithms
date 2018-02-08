package cn.mklaus.learn.algorithm;

import cn.mklaus.learn.sort.SortUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * @author Mklaus
 * @date 2018-02-08 下午3:37
 */
public class DynamicProgrammingTest {


    @Test
    public void climbingWaysRecursion() throws Exception {
        Assert.assertEquals(1, DynamicProgramming.climbingWaysRecursion(1));
        Assert.assertEquals(2, DynamicProgramming.climbingWaysRecursion(2));
        Assert.assertEquals(3, DynamicProgramming.climbingWaysRecursion(3));
        Assert.assertEquals(8, DynamicProgramming.climbingWaysRecursion(5));
    }

    @Test
    public void climbingWaysByNote() throws Exception {
        HashMap<Integer, Integer> map = new HashMap<>();
        Assert.assertEquals(1, DynamicProgramming.climbingWaysNote(1, map));
        Assert.assertEquals(2, DynamicProgramming.climbingWaysNote(2, map));
        Assert.assertEquals(3, DynamicProgramming.climbingWaysNote(3, map));
        Assert.assertEquals(8, DynamicProgramming.climbingWaysNote(5, map));
    }

    @Test
    public void climbingWays() throws Exception {
        Assert.assertEquals(1, DynamicProgramming.climbingWays(1));
        Assert.assertEquals(2, DynamicProgramming.climbingWays(2));
        Assert.assertEquals(3, DynamicProgramming.climbingWays(3));
        Assert.assertEquals(8, DynamicProgramming.climbingWays(5));
    }


    int[] g;
    int[] p;
    int m;

    @Before
    public void setUp() throws Exception {
        this.m = 10;
        this.g = new int[]{3, 5, 3, 4, 3};
        this.p = new int[]{400, 500, 200, 300, 350};
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("--------------------------------------------");
    }



    @Test
    public void backpackRecursion() throws Exception {
        int result = DynamicProgramming.backpackRecursion(m, g.length, g, p);
        System.out.println("result = " + result);
    }

    @Test
    public void backpackNote() throws Exception {
        HashMap<String, Integer> map = new HashMap<>();
        int result = DynamicProgramming.backpackNote(m, p.length, g, p, map);
        System.out.println("result = " + result);
    }

    @Test
    public void backpack() throws Exception {
        int result = DynamicProgramming.backpack(m, p.length, g, p);
        System.out.println("result = " + result);
    }

}