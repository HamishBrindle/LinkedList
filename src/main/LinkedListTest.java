package main;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.Random;

/**
 * Created by Hamish on 2017-03-27.
 */
public class LinkedListTest {

    private LinkedList<String> stringList ;

    @Before
    public void setUp() throws Exception {
        stringList = new LinkedList<>();
    }

    @After
    public void tearDown() throws Exception {
        stringList = null;
    }

    @Test
    public void testAdd() {
        // Assemble object(s) to act upon
        stringList.add("Hamish");
        // Act on those objects
        // Assert that the actions were completed/invalid
        Assert.assertEquals(1, stringList.size());
        Assert.assertNotNull(stringList.getHead());
        Assert.assertNotNull(stringList.getTail());
        Assert.assertTrue(stringList.getHead().getData().equals(stringList.getTail().getData()));
    }

    @Test
    public void testIteratorHasNextEmptyList() {
        Iterator<String> it = stringList.iterator();
        Assert.assertFalse(it.hasNext());
    }

    @Test
    public void testIteratorHasNextListSizeOne() {
        stringList.add("Hamish");

        Iterator<String> it = stringList.iterator();

        Assert.assertTrue(it.hasNext());
    }

    @Test
    public void testIteratorHasNextSmallList() {
        Random generator = new Random();
        int size = generator.nextInt(6) + 10;

        for (int i = 0; i < size; i++) {
            stringList.add("" + i);
        }

        Iterator<String> it = stringList.iterator();

        for (int i = 0; i < size; i++) {
            Assert.assertTrue(it.hasNext());
            Assert.assertNotNull(it.next());
        }

        Assert.assertFalse(it.hasNext());
    }

}
