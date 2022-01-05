package algorithms.search.tests;

import algorithms.search.MedianSearch;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class MedianSearchTest extends TestCase {
    @Test
    public void testfindMedian() {
        boolean copy= true;
        for(int t=0;t<100000;t++) {
            int array_len = ThreadLocalRandom.current().nextInt(1, 1000);
            var random_array = new Integer[array_len];
            for (int k = 0; k < array_len; k++) {
                random_array[k] = ThreadLocalRandom.current().nextInt(-1000, 1000 + 1);
            }
            var median_search = new MedianSearch<Integer>(random_array, copy);
            int median_location = median_search.findMedian();
            // check if median is correct
            for (int i = 0; i < median_location; i++) {
                if (median_search.getArray()[i] > median_search.getArray()[median_location]) {
                    System.out.println("Test failed!");
                    assertEquals("Test failed. Median is not in the right place:"+median_search.getArray(),false, true);
                }
            }
            for (int i = median_location + 1; i < median_search.getArray().length; i++) {
                if (median_search.getArray()[i] < median_search.getArray()[median_location]) {
                    assertEquals("Test failed. Median is not in the right place:"+median_search.getArray(),false, true);
                }
            }
            assertEquals("Test passed! Median is in the right place:"+median_search.getArray(),true,true);
        }
    }
}