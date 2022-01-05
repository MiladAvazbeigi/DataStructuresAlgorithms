package algorithms.search;

import java.util.concurrent.ThreadLocalRandom;

public class MedianSearch <T extends Comparable<T>>{
    /**
     * Description  <br/>
     * --------------- <br/>
     * The class finds the median of an input array of objects implementing interface Comparable<T>.
     * The search is essentially a variation of quick sort where a pivot is selected randomly and then the array
     * is divided into two sub-arrays where one has all the elements less than equal the pivot and the other has elements
     * larger. In other words, pivot is the right place. Finally, the pivot location is compared to the location of the
     * median. If pivot is in the median location then it is the pivot. If not, the subarray including the position of
     * median is selected and the procedure is repeated. <br/>
     * <br/>
     * Note <br/>
     * ----------- <br/>
     * The code can be easily used to find the i-th element in the array. <br/>
     * <br/>
     * Example <br/>
     * -----------<br/>
     * >>> var median_search = new MedianSearch<Integer>(random_array);<br/>
     * >>> median_search.findMedian();<br/>
     * @author: Milad Avazbeigi
     */
    private T[] input_array;
    private int median_location; // left median in for array with even number of members

    // -----------------------------
    // Region: getArray and setArray
    // -----------------------------
    public T[] getArray() {
        return this.input_array;
    }
    public void setArray(T[] input_array) {
        this.input_array = input_array;
    }
    // -----------------------------
    // Region: getArray and setArray
    // -----------------------------

    // --------------------
    // Region: Constructors
    // --------------------
    public MedianSearch(T[] input_array, boolean copy) {
        if(copy){
            this.input_array = input_array.clone();
        }else{
            this.input_array = input_array;
        }
        if(this.input_array.length %2 == 0){
            this.median_location = this.input_array.length/2-1;
        }else{
            this.median_location = (this.input_array.length-1)/2;
        }
    }
    // ------------------------
    // End Region: Constructors
    // ------------------------

    public int findMedian(){
        this.findMedian(this.input_array, 0, this.input_array.length-1);
        return this.median_location;
    }

    private void findMedian(T[] array, int start, int end){
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
}
