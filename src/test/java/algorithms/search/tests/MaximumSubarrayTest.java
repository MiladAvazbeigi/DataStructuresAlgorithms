package algorithms.search.tests;

import algorithms.search.MaximumSubarray;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Hashtable;

import static org.junit.Assert.*;

public class MaximumSubarrayTest extends TestCase {
    @Test // Trail of negative numbers followed by positive ones
    public void testSearchTestI() throws Exception {
        Integer[] random_array = new Integer[]{-123,-1212, -2,-3, -4, 0,1, 2, 3, 4};
        var search_hndl = new MaximumSubarray<Integer>(random_array);
        Hashtable expected_output = new Hashtable();
        expected_output.put("Start", 5);
        expected_output.put("End", 9);
        expected_output.put("Sum", 10.0f);
        assertEquals("The subarray with maximum sum has sum  1099", expected_output, search_hndl.search());
    }

    @Test // trail of positive numbers followed by negative ones
    public void testSearchTestII() throws Exception {
        Integer[] random_array = new Integer[]{123,1212, 2,3, -1, -2,-1, -2, -3, -40};
        var search_hndl = new MaximumSubarray<Integer>(random_array);
        Hashtable expected_output = new Hashtable();
        expected_output.put("Start", 0);
        expected_output.put("End", 3);
        expected_output.put("Sum", 1340.0f);
        assertEquals("The subarray with maximum sum has sum 1099", expected_output, search_hndl.search());
    }

    @Test // Singleton: one positive on left most
    public void testSearchTestIII() throws Exception {
        Integer[] random_array = new Integer[]{1234,-1, -2,-1, -2, -3, -40};
        var search_hndl = new MaximumSubarray<Integer>(random_array);
        Hashtable expected_output = new Hashtable();
        expected_output.put("Start", 0);
        expected_output.put("End", 0);
        expected_output.put("Sum", 1234.0f);
        assertEquals("The subarray with maximum sum has sum 1234.0f", expected_output, search_hndl.search());
    }

    @Test // Singleton: positive on right most
    public void testSearchTestIV() throws Exception {
        Integer[] random_array = new Integer[]{-1234,-1, -2,-1, -2, -3, 40};
        var search_hndl = new MaximumSubarray<Integer>(random_array);
        Hashtable expected_output = new Hashtable();
        expected_output.put("Start", 6);
        expected_output.put("End", 6);
        expected_output.put("Sum", 40.0f);
        assertEquals("The subarray with maximum sum has sum 40.0f", expected_output, search_hndl.search());
    }

    @Test // Two positive on either sides
    public void testSearchTestV() throws Exception {
        Integer[] random_array = new Integer[]{1234,-1, -2,-1, -2, -3, 40};
        var search_hndl = new MaximumSubarray<Integer>(random_array);
        Hashtable expected_output = new Hashtable();
        expected_output.put("Start", 0);
        expected_output.put("End", 6);
        expected_output.put("Sum", 1265.0f);
        assertEquals("The subarray with maximum sum has sum 1274", expected_output, search_hndl.search());
    }
}