package algorithms.search.tests;

import algorithms.search.IthElementSearch;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

public class IthElementSearchTest extends TestCase {
    @Test
    public void testFindIthElement() throws Exception {
        //  0  1 2 3 4 5 6 7 8  9
        // -9 -6 0 1 2 5 6 7 76 7666
        Integer[] random_array = new Integer[]{6,2,7, 0, 1,5, -6, -9, 7666, 76};
        var search_hndl = new IthElementSearch<Integer>(random_array);
        assertEquals("4th element of the sorted array is 2",
                        java.util.Optional.ofNullable(search_hndl.findIthElement(4)),
                        java.util.Optional.ofNullable(2));
        assertEquals("8th element of the sorted array is 76",
                     java.util.Optional.ofNullable(search_hndl.findIthElement(8)),
                     java.util.Optional.ofNullable(76));
        assertEquals("First element of the sorted array is -6",
                     java.util.Optional.ofNullable(search_hndl.findIthElement(1)),
                     java.util.Optional.ofNullable(-6));
    }
}