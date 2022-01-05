package algorithms.sort.tests;

import algorithms.sort.QuickSort;
import junit.framework.TestCase;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;


public class QuickSortTest extends TestCase {
    @Test
    public void testQuickSortIntegerArray() throws Exception {
        int experiments_number = 1000;
        int repetitions = 2;
        boolean copy = true;
        for(int i=1; i <= experiments_number; i++){
            int array_len = 10*i;
            System.out.println("Testing the sort algorithm for arrays of size:"+array_len);
            for(int j=0; j < repetitions;j++){
                // Random Array
                var random_array = new Integer[array_len];
                for (int k=0; k < array_len;k++){
                    random_array[k] = ThreadLocalRandom.current().nextInt(-1000, 1000 + 1);
                }
                // Sorting
                var merge_sort = new QuickSort<Integer>(random_array, copy);
                merge_sort.sort();
                // check if the array is actually sorted
                for (int k = 0; k < random_array.length-1; k++) {
                    if (merge_sort.getArray()[k] > merge_sort.getArray()[k+1]) {
                        assertEquals("Test failed. The array is not sorted correctly:"+merge_sort.getArray(),false, true);
                    }
                }
                assertEquals("All tests passed for the current problem size:"+array_len,true, true);
            }
        }
    }

    @Test
    public void testQuickSortDoubleArray() throws Exception {
        int experiments_number = 1000;
        int repetitions = 2;
        boolean copy = true;
        for(int i=1; i <= experiments_number; i++){
            int array_len = 10*i;
            System.out.println("Testing the sort algorithm for arrays of size:"+array_len);
            for(int j=0; j < repetitions;j++){
                // Random Array
                var random_array = new Double[array_len];
                for (int k=0; k < array_len;k++){
                    random_array[k] = ThreadLocalRandom.current().nextDouble(-1000, 1000 + 1);
                }
                // Sorting
                var merge_sort = new QuickSort<Double>(random_array, copy);
                merge_sort.sort();
                // check if the array is actually sorted
                for (int k = 0; k < random_array.length-1; k++) {
                    if (merge_sort.getArray()[k] > merge_sort.getArray()[k+1]) {
                        assertEquals("Test failed. The array is not sorted correctly:"+merge_sort.getArray(),false, true);
                    }
                }
                assertEquals("All tests passed for the current problem size:"+array_len,true, true);
            }
        }
    }
}