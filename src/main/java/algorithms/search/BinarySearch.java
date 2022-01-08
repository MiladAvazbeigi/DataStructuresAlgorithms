package algorithms.search;

public class BinarySearch<T extends Comparable<T>>{
    /**
     * Description <br/>
     * (Wikipedia: https://en.wikipedia.org/wiki/Binary_search_algorithm) <br/>
     * ----------- <br/>
     * In computer science, binary search, also known as half-interval search, logarithmic search, or binary chop, is
     * a search algorithm that finds the position of a target value within a sorted array. Binary search compares the
     * target value to the middle element of the array. If they are not equal, the half in which the target cannot lie
     * is eliminated and the search continues on the remaining half, again taking the middle element to compare to the
     * target value, and repeating this until the target value is found. If the search ends with the remaining half
     * being empty, the target is not in the array.
     *
     * Binary search runs in logarithmic time in the worst case, making O( log n ) comparisons, where n is the number
     * of elements in the array. Binary search is faster than linear search except for small arrays. However, the
     * array must be sorted first to be able to apply binary search. There are specialized data structures designed
     * for fast searching, such as hash tables, that can be searched more efficiently than binary search. However,
     * binary search can be used to solve a wider range of problems, such as finding the next-smallest or next-largest
     * element in the array relative to the target even if it is absent from the array. <br/>
     *
     * Pseudocode<br/>
     * --------------<br/>
     * BinarySearch(A[], search_value, start, end) <br/>
     *      if(start <= end){
     *          int mid = (int)(end+start)/2; <br/>
     *          if(A[mid] == search_value){ <br/>
     *              return mid; <br/>
     *          } <br/>
     *          if(search_value < A[mid]){ <br/>
     *              return search(A[], search_value, start, mid-1); <br/>
     *          } <br/>
     *          if(search_value > A[mid]){ <br/>
     *              return search(A[], search_value, mid+1, end); <br/>
     *          } <br/>
     *      }
     *      return -1; <br/>
     */
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
