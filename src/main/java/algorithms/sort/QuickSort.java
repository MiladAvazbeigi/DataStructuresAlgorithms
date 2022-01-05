package algorithms.sort;

import java.util.concurrent.ThreadLocalRandom;

public class QuickSort<T extends Comparable<T>> {
    /**
     * Description <br/>
     * ------------- <br/>
     * The class provides the recursive implementation of the 'QuickSort'. 'Quick Sort' algorithm is a divide and
     * conquer algorithm with time complexity O(nlogn) on average. The algorithm picks an element of the array as 'pivot'.
     * Then, it finds the right location of the pivot by dividing the array into two sub-arrays where one has all the
     * elements smaller than the pivot and one with all the elements larger than the pivot. Then, the algorithm is
     * repeated for the two sub-arrays. Finally, the two merged sub-arrays are merged with time complexity O(n). The
     * popularity of the algorithm is due to the fast that all the changes are happening in place with no need of
     * copying or creating new arrays.
     * <br/><br/>
     * Notes <br/>
     * -------- <br/>
     * - The implementation is using generics in Java.
     *
     * @author Milad Avazbeigi
     */
    private T[] sort_array;

    // -----------------------------
    // Region: getArray and setArray
    // -----------------------------
    public T[] getArray() {
        return this.sort_array;
    }
    public void setArray(T[] sort_array) {
        this.sort_array = sort_array;
    }
    // ---------------------------------
    // End Region: getArray and setArray
    // ---------------------------------

    // --------------------
    // Region: Constructors
    // --------------------
    public QuickSort(T[] input_array, boolean copy){
        if(copy){
            setArray(input_array.clone());
        }else{
            setArray(input_array);
        }
    }
    // ------------------------
    // End Region: Constructors
    // ------------------------

    public void sort(){
        this.sort(this.sort_array, 0, this.sort_array.length-1);
    }

    private void sort(T[] array, int start, int end){
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
}