package cn.mklaus.learn.datastructure;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Mklaus
 * @date 2018-02-09 下午1:50
 */
public class SkipListTest {
    @Test
    public void skipList() throws Exception {
        SkipList<String> list = new SkipList<>();

        list.put(1, "");
        Assert.assertEquals(list.size(), 1);

        list.remove(1);
        Assert.assertTrue(list.isEmpty());

        list.put(1, "value1");
        String value = list.get(1);
        Assert.assertTrue("value1".equals(value));
    }

}