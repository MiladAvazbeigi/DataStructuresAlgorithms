package algorithms.search.tests;

import algorithms.search.BinarySearch;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

public class BinarySearchTest extends TestCase {
    @Test
    public void testIntegerNonEmptyArray() {
        var search_array = new Integer[]{0,1,2,3, 4, 5, 6, 7};
        var binary_search = new BinarySearch<Integer>(search_array);
        assertEquals("Element 0 is located at index 0.",0,  binary_search.search(0));
        assertEquals("Element 1 is located at index 1.",1,  binary_search.search(1));
        assertEquals("Element 2 is located at index 2.",2,  binary_search.search(2));
        assertEquals("Element 3 is located at index 3.",3,  binary_search.search(3));
        assertEquals("Element 4 is located at index 4.",4,  binary_search.search(4));
        assertEquals("Element 5 is located at index 5.",5,  binary_search.search(5));
        assertEquals("Element 6 is located at index 6.",6,  binary_search.search(6));
        assertEquals("Element -10 is not in the list.",-1,  binary_search.search(-10));
        assertEquals("Element 1000 is not in the list.",-1,  binary_search.search(1000));
    }

    @Test
    public void testDoubleNonEmptyArray() {
        var search_array = new Double[]{-2.234, -1.234, 0.12, 1.123, 2.87, 3.76, 4.87, 5.98, 6.23, 7.65};
        var binary_search = new BinarySearch<Double>(search_array);
        assertEquals("Element -2.234 is located at index 0.",0,  binary_search.search(-2.234));
        assertEquals("Element -1.234 is located at index 1.",1,  binary_search.search(-1.234));
        assertEquals("Element 0.12 is located at index 2.",2,  binary_search.search(0.12));
        assertEquals("Element 1.123 is located at index 3.",3,  binary_search.search(1.123));
        assertEquals("Element 2.87 is located at index 4.",4,  binary_search.search(2.87));
        assertEquals("Element 2.873 is not in the array.",-1,  binary_search.search(2.873));
    }

    @Test
    public void testEmptyArray() {
        var binary_search = new BinarySearch<Integer>(null);
        assertEquals("Empty array does not include anything.",-1,  binary_search.search(20));
        assertEquals("Empty array does not include anything.",-1,  binary_search.search(-30));
        assertEquals("Empty array does not include anything.",-1,  binary_search.search(null));

    }
}