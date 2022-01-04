package algorithms.search;

import java.util.concurrent.ThreadLocalRandom;

public class MedianSearch <T extends Comparable<T>>{
    private T[] input_array;
    private int median_location; // left median in for array with even number of members

    // --------------------
    // Region: Constructors
    // --------------------
    public MedianSearch(T[] input_array) {
        this.input_array = input_array;
        if(this.input_array.length %2 == 0){
            this.median_location = this.input_array.length/2-1;
        }else{
            this.median_location = (this.input_array.length-1)/2;
        }
    }
    // ------------------------
    // End Region: Constructors
    // ------------------------

    public void findMedian(){
        var median_search = new MedianSearch<T>(this.input_array);
        median_search.findMedian(this.input_array, 0, this.input_array.length-1);
    }

    public void findMedian(T[] array, int start, int end){
        if(start<=end) {
            int pivot_location = partition(array, start, end);
            if (pivot_location != this.median_location) {
                if (pivot_location < this.median_location) {
                    findMedian(array, pivot_location + 1, end);
                } else {
                    findMedian(array, start, pivot_location - 1);
                }
            }else{
                System.out.println("Pivot is located at:"+pivot_location+", with value:"+array[pivot_location]);
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

    public static void main(String[] args){
        for(int t=0;t<10000;t++) {
            int array_len = 1000;
            var random_array = new Integer[array_len];
            for (int k = 0; k < array_len; k++) {
                random_array[k] = ThreadLocalRandom.current().nextInt(-1000, 1000 + 1);
            }
            var median_search = new MedianSearch<Integer>(random_array);
            median_search.findMedian();
            // check if median is correct
            for (int i = 0; i < median_search.median_location; i++) {
                if (median_search.input_array[i] > median_search.input_array[median_search.median_location]) {
                    System.out.println("Test failed!");
                }
            }
            for (int i = median_search.median_location + 1; i < median_search.input_array.length; i++) {
                if (median_search.input_array[i] < median_search.input_array[median_search.median_location]) {
                    System.out.println("Test failed!");
                }
            }
        }
    }
}
