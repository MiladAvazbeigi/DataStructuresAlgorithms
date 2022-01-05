package algorithms.search;

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
        if(this.search_array==null){
            return -1;
        }else{
            var binary_search = new BinarySearch<T>(this.search_array);
            return binary_search.search(search_value, 0, this.search_array.length-1);
        }
    }

    private int search(T search_value, int start, int end){
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
}
