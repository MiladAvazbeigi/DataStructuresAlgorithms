package algorithms.search;

import java.util.concurrent.ThreadLocalRandom;

public class IthElementSearch<T extends Comparable<T>>{
    /**
     * Description  <br/>
     * --------------- <br/>
     * The class finds the i-th element of an input array of objects implementing interface Comparable<T>.
     * The search is essentially a variation of quick sort where a pivot is selected randomly and then the array
     * is divided into two sub-arrays where one has all the elements less than equal the pivot and the other has elements
     * larger. In other words, pivot is the right place. Finally, the pivot location is compared to the location of the
     * i-th element. If pivot is in the i-th location then the value is returned. If not, the subarray including the
     * position of i-th element is selected and the procedure is repeated. <br/>
     * <br/>
     * Time Complexity <br/>
     * -------------------<br/>
     * Recurrence for perfectly balance partitioning: T(n) = T(n/2) + cn
     * This is because in a perfectly balanced partitioning, at every iteration the number of elements is reduced to half.
     * Note that here, we are only interested in one of the sub-arrays achieved i.e. the one including the i-th array.
     * Finally, at every iteration we need to find the location of the pivot which is done in O(n).  Solving this
     * recursive equation gives T(n) =O(n). So, the i-th element of an array can be found in O(n). <br/>
     *  <br/>
     * Example <br/>
     * -----------<br/>
     * >>> Integer[] random_array = new Integer[]{6,2,7, 0, 1,5, -6, -9, 7666, 76};
     * >>> var search_hndl = new IthElementSearch<Integer>(random_array, 1);
     * >>> System.out.println(search_hndl.findIthElement(4));
     * @author: Milad Avazbeigi
     */
    private T[] input_array;
    private int search_index; // left median in for array with even number of members

    public int getSearchIndex() {
        return this.search_index;
    }
    public void setSearchIndex(int search_index) {
        this.search_index = search_index;
    }

    // --------------------
    // Region: Constructors
    // --------------------
    public IthElementSearch(T[] input_array){
        this.input_array = input_array;
    }
    // ------------------------
    // End Region: Constructors
    // ------------------------

    public T findIthElement(int i) throws Exception {
        if(i<this.input_array.length && i>=0){
            this.setSearchIndex(i);
        }else{
            throw new Exception("The search index is out of range.");
        }
        return this.findIthElement(this.input_array, 0, this.input_array.length-1);
    }

    private T findIthElement(T[] array, int start, int end){
        if(start==end) {
            return array[start];
        }else {
            int pivot_location = partition(array, start, end);
            if(pivot_location == this.getSearchIndex()){
                return array[this.getSearchIndex()];
            }
            if (this.getSearchIndex() < pivot_location) {
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
}
