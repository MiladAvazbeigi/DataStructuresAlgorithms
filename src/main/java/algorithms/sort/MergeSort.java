package algorithms.sort;

public class MergeSort <T extends Comparable<T>>{
    private T[] sort_array=null;

    // -----------------------------
    // Region: getArray and setArray
    // -----------------------------
    public T[] getArray() {
        return sort_array;
    }
    public void setArray(T[] sort_array) {
        this.sort_array = sort_array;
    }
    // -----------------------------
    // Region: getArray and setArray
    // -----------------------------

    // --------------------
    // Region: Constructors
    // --------------------
    public MergeSort(T[] sort_array, boolean copy){
        if(copy){
            setArray(sort_array.clone());
        }else{
            setArray(sort_array);
        }
    }
    // ------------------------
    // End Region: Constructors
    // ------------------------

    public void sort() throws Exception {
        this.sort(0, this.sort_array.length-1);
    }

    private void sort(int start, int end) throws Exception {
        if(start<end) {
            int mid = (end+start) / 2;
            sort(start, mid);
            sort(mid + 1, end);
            merge(start, mid, end);
        }
    }

    private void merge(int start, int mid, int end) throws Exception {
        /*
          merge combines two sorted arrays into one in O(n)
         */
        // ----------------------
        // Region: copying arrays
        // ----------------------
        T[] left_array = (T[]) new Comparable[mid - start + 1];
        T[] right_array = (T[]) new Comparable[end - mid];
        for (int i = 0; i < mid - start + 1; ++i)
            left_array[i] = this.sort_array[start+i];
        for (int j = 0; j < end-mid; ++j)
            right_array[j] = this.sort_array[mid + 1 + j];
        // --------------------------
        // End Region: copying arrays
        // --------------------------
        int i = 0; // left_array index
        int j = 0; // right_array index
        int k = start;
        while(i<left_array.length && j<right_array.length){
            if(left_array[i].compareTo(right_array[j])<=0){
                this.sort_array[k] = left_array[i];
                i++;
            }else{
                this.sort_array[k] = right_array[j];
                j++;
            }
            k++;
        }
        // ------------------------
        // Region: Adding Remaining
        // ------------------------
        while(i<left_array.length) {
            this.sort_array[k] = left_array[i];
            i++;
            k++;
        }
        while(j<right_array.length) {
            this.sort_array[k] = right_array[j];
            j++;
            k++;
        }
        // ----------------------------
        // End Region: Adding Remaining
        // ----------------------------
    }
}
