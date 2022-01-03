package algorithms;

import java.util.concurrent.ThreadLocalRandom;

public class BinarySearch<T extends Comparable<T>>{
    private T[] search_array;

    // --------------------
    // Region: Constructors
    // --------------------
    public BinarySearch(T[] input_array){
        this.search_array = input_array;
    }
    // ------------------------
    // End Region: Constructors
    // ------------------------

    // ------------------------
    // Region: Search Algorithm
    // ------------------------
    public int search(T search_value){
        var binary_search = new BinarySearch<T>(this.search_array);
        return binary_search.search(search_value, 0, this.search_array.length-1);
    }

    public int search(T search_value, int start, int end){
        if(start <= end){
            int mid = (int)(end+start)/2;
            if(search_value.compareTo(this.search_array[mid]) == 0){
                return mid;
            }
            if(search_value.compareTo(this.search_array[mid]) < 0){
                return search(search_value, start, mid-1);
            }
            if(search_value.compareTo(this.search_array[mid]) > 0){
                return search(search_value, mid+1, end);
            }
        }
        return -1;
    }
    // ----------------------------
    // End Region: Search Algorithm
    // ----------------------------

    public static void main(String[] args){
        var search_array = new Integer[]{1,2,3, 4, 5, 6, 7};
        var binary_search = new BinarySearch<Integer>(search_array);
        System.out.println(binary_search.search(2, 0, search_array.length-1));
        System.out.println(binary_search.search(6, 0, search_array.length-1));
        System.out.println(binary_search.search(9, 0, search_array.length-1));
        System.out.println(binary_search.search(2));
        System.out.println(binary_search.search(6));
        System.out.println(binary_search.search(9));

        /**
         * Time Complexity Calculation
         */


        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        var random_search_array = new Integer[100];
        for (int i=0; i<100;i++){
            random_search_array[i] = ThreadLocalRandom.current().nextInt(0, 1000 + 1);
        }
        int a = 10;

    }
}
