package algorithms.search;

import java.util.concurrent.ThreadLocalRandom;

public class MaximumSubarray<T extends Comparable<T>>{
    /**
     * Description  <br/>
     * --------------- <br/>
     * <br/>
     * <br/>
     * Time Complexity <br/>
     * -------------------<br/>
     *  <br/>
     *  <br/>
     * Example <br/>
     * -----------<br/>
     * >>> var median_search = new MedianSearch<Integer>(random_array);<br/>
     * >>> median_search.findMedian();<br/>
     * @author: Milad Avazbeigi
     */
    private T[] input_array;
    private int search_location; // left median in for array with even number of members

    // --------------------
    // Region: Constructors
    // --------------------
    public MaximumSubarray(T[] input_array, int i) throws Exception {
        this.input_array = input_array;
        if(i < input_array.length && i>=0){
            this.search_location = i;
        }else{
            throw new Exception();
        }
    }
    // ------------------------
    // End Region: Constructors
    // ------------------------

    public T findIthElement(int i) throws Exception {
        var search_handl = new MaximumSubarray<T>(this.input_array, i);
        return search_handl.findIthElement(this.input_array, 0, this.input_array.length-1);
    }

    public T findIthElement(T[] array, int start, int end){
        if(start==end){
            return array[start];
        }else {
            int pivot_location = partition(array, start, end);
            if(pivot_location == this.search_location){
                return array[this.search_location];
            }
            if (this.search_location < pivot_location) {
                return findIthElement(array, start, pivot_location - 1);
            } else {
                return findIthElement(array, pivot_location + 1, end);
            }
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
        // -9 -6 0 1 2 5 6 7 76 7666
        Integer[] random_array = new Integer[]{6,2,7, 0, 1,5, -6, -9, 7666, 76};
        var search_hndl = new MaximumSubarray<Integer>(random_array, 1);
        System.out.println(search_hndl.findIthElement(4));
    }
}
