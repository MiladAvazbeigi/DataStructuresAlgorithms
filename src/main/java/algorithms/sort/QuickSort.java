package algorithms.sort;

import algorithms.BinarySearch;

import java.util.concurrent.ThreadLocalRandom;

public class QuickSort<T extends Comparable<T>> {
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
        T pivot_value = array[end]; // convention: assume the last element is the pivots
        int i = start -1; // the numbers with index less than equal i are less than pivot i.e. arr[i+1]>pivor_value
        // ------------
        // Region: Swap
        // ------------
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
        var swap = array[end];
        array[end] = array[i+1];
        array[i+1] = swap;
        return i+1;
        // --------------------------
        // End Region: Pivot Location
        // --------------------------
    }

    public static void main(String[] args) throws Exception {
        // Random Array
        int array_len = 1000;
        var random_array = new Integer[array_len];
        for (int i=0; i<array_len;i++){
            random_array[i] = ThreadLocalRandom.current().nextInt(-1000, 1000 + 1);
        }
        // Sorting
        var quick_sort = new QuickSort<Integer>(random_array);
        quick_sort.sort();

        // Testing
        for (int i=0; i<array_len-1;i++){
            if(random_array[i]>random_array[i+1]){
                throw new Exception();
            }
        }
        System.out.println("Test passed!");
    }
}