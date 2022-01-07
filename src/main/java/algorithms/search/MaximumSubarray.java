package algorithms.search;

import java.util.Hashtable;

public class MaximumSubarray<T extends Number & Comparable<T>>{
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

    // --------------------
    // Region: Constructors
    // --------------------
    public MaximumSubarray(T[] input_array) throws Exception {
        this.input_array = input_array;
    }
    // ------------------------
    // End Region: Constructors
    // ------------------------

    public Hashtable search() throws Exception {
        return this.search(this.input_array, 0, this.input_array.length-1);
    }

    private Hashtable search(T[] array, int start, int end){
        if(start<=end){
            if(start==end){
                Hashtable output = new Hashtable();
                output.put("Start", start);
                output.put("End", end);
                output.put("Sum", this.input_array[start].floatValue());
                return output;
            }else {
                int pivot_location = (start+end)/2;
                Hashtable output = new Hashtable();
                Hashtable left_max_subarray = this.search(array, start, pivot_location - 1);
                Hashtable right_max_subarray = this.search(array, pivot_location + 1, end);
                Hashtable crossing_max_subarray = this.maximumCrossingSubarray(start, pivot_location, end);
                if((float)crossing_max_subarray.get("Sum")>(float)right_max_subarray.get("Sum") && (float)crossing_max_subarray.get("Sum")>(float)left_max_subarray.get("Sum")){
                    output = crossing_max_subarray;
                }else{
                    if((float)left_max_subarray.get("Sum")>(float)right_max_subarray.get("Sum")){
                        output = left_max_subarray;
                    }
                    if((float)left_max_subarray.get("Sum")<=(float)right_max_subarray.get("Sum")){
                        output = right_max_subarray;
                    }
                }
                return output;
            }
        }else{
            Hashtable output = new Hashtable();
            output.put("Start", start);
            output.put("End", end);
            output.put("Sum", -Float.MAX_VALUE);
            return output;
        }
    }

    private Hashtable maximumCrossingSubarray(int start, int pivot_location, int end){
        int i = pivot_location;
        int j = pivot_location+1;
        int istar=pivot_location;
        int jstar=pivot_location+1;
        float lef_maxsum = 0f;
        float lef_sum = 0f;
        while(i>=start){
            lef_sum += this.input_array[i].floatValue();
            if (lef_sum >= lef_maxsum){
                istar = i;
                lef_maxsum = lef_sum;
            }
            i--;
        }
        float right_maxsum = 0f;
        float right_sum = 0f;
        while(j<=end){
            right_sum += this.input_array[j].floatValue();
            if (right_sum >= right_maxsum){
                jstar = j;
                right_maxsum= right_sum;
            }
            j++;
        }
        Hashtable output = new Hashtable();
        output.put("Start", istar);
        output.put("End", jstar);
        output.put("Sum", lef_maxsum+right_maxsum);
        return output;
    }
}
