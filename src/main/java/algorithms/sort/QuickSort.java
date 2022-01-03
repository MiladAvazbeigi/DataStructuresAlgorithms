package algorithms.sort;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import java.util.concurrent.ThreadLocalRandom;

public class QuickSort<T extends Comparable<T>> {
    /**
     * Description <br/>
     * ------------- <br/>
     * The class provides the recursive implementation of the QuickSort. Quick Sort algorithm is a divide and
     * conquer algorithm with time complexity O(nlogn) on average. The algorithm picks an element of the array as 'pivot'.
     * Then, it finds the right location of the pivot by dividing the array into two sub-arrays where one has all the
     * elements smaller than the pivot and one with all the elements larger than the pivot. Then, the algorithm is
     * repeated for the two sub-arrays. Finally, the two merged sub-arrays are merged with time complexity O(n).
     * <br/><br/>
     * Notes <br/>
     * -------- <br/>
     * - The implementation is using generics in Java.
     *
     * @author Milad Avazbeigi
     */
    private T[] sort_array;

    // --------------------
    // Region: Constructors
    // --------------------
    public QuickSort(T[] input_array) {
        this.sort_array = input_array;
    }
    // ------------------------
    // End Region: Constructors
    // ------------------------

    public void sort(){
        var quick_sort = new QuickSort<T>(this.sort_array);
        quick_sort.sort(this.sort_array, 0, this.sort_array.length-1);
    }

    public void sort(T[] array, int start, int end){
        if(start<=end){
            int pivot_location = partition(array, start, end);
            sort(array, start, pivot_location-1);
            sort(array, pivot_location+1, end);
        }
    }

    private int partition(T[] array, int start, int end){
        // --------------------
        // Region: Random Pivot
        // --------------------
        int initial_pivot_index = ThreadLocalRandom.current().nextInt(start, end + 1);
        T pivot_value = array[initial_pivot_index];
        array[initial_pivot_index] = array[end];
        array[end] = pivot_value; // pivot is moved to the end making operations easier
        // ------------------------
        // End Region: Random Pivot
        // ------------------------

        // ------------
        // Region: Swap
        // ------------
        int i = start -1; // the numbers with index less than equal i are less than pivot i.e. arr[i+1]>pivor_value
        for(int j=start; j<end;j++){
            if(array[j].compareTo(pivot_value) < 0){
                var swap = array[j];
                array[j] = array[i+1];
                array[i+1] = swap;
                i++;
            }
        }
        // ----------------
        // End Region: Swap
        // ----------------

        // ----------------------
        // Region: Pivot Location
        // ----------------------
        array[end] = array[i+1];
        array[i+1] = pivot_value;
        return i+1;
        // --------------------------
        // End Region: Pivot Location
        // --------------------------
    }

    public static void main(String[] args) throws Exception {
        int experiments_number = 10000;
        int repetitions = 10;
        double[] running_time = new double[experiments_number];
        double[] problem_size = new double[experiments_number];
        for(int i=0; i < experiments_number; i++){
            int array_len = 100*(i+1);
            long timeElapsed = 0;
            for(int j=0; j < repetitions;j++){
                // Random Array
                var random_array = new Integer[array_len];
                for (int k=0; k < array_len;k++){
                    random_array[k] = ThreadLocalRandom.current().nextInt(-1000000, 10000000 + 1);
                }
                // Sorting
                long start = System.currentTimeMillis();
                var quick_sort = new QuickSort<Integer>(random_array);
                quick_sort.sort();
                // Checking if the array is sorted
                for(int k=0;k<array_len-1;k++){
                    if(quick_sort.sort_array[k]>quick_sort.sort_array[k+1]){
                        System.out.println(k);
                        System.out.println("Test failed!");
                    }
                }
                long finish = System.currentTimeMillis();
                timeElapsed = timeElapsed + finish - start;
            }
            problem_size[i] = array_len;
            running_time[i] = timeElapsed/repetitions;
        }
        // ------------
        // Region: Plot
        // ------------
        XYChart chart = QuickChart.getChart("Running time", "n", "t", "t(n)", problem_size, running_time);
        new SwingWrapper(chart).displayChart();
        // ----------------
        // End Region: Plot
        // ----------------
        System.out.println("Experiments Finished!");
    }
}