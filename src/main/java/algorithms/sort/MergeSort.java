package algorithms.sort;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import java.util.concurrent.ThreadLocalRandom;


public class MergeSort <T extends Comparable<T>>{
    private T[] sort_array=null;

    public T[] getArray() {
        return sort_array;
    }
    public void setArray(T[] sort_array) {
        this.sort_array = sort_array;
    }

    // --------------------
    // Region: Constructors
    // --------------------
    public MergeSort(T[] sort_array){
        setArray(sort_array);
    }
    // ------------------------
    // End Region: Constructors
    // ------------------------

    public void sort() throws Exception {
        var merge_sort = new MergeSort<T>(this.sort_array);
        merge_sort.sort(0, this.sort_array.length-1);
    }

    public void sort(int start, int end) throws Exception {
        if(start<end) {
            int mid = (end+start) / 2;
            sort(start, mid);
            sort(mid + 1, end);
            merge(start, mid, end);
        }
    }

    public void merge(int start, int mid, int end) throws Exception {
        T[] left_array = (T[]) new Comparable[mid - start + 1];
        T[] right_array = (T[]) new Comparable[end - mid];
        for (int i = 0; i < mid - start + 1; ++i)
            left_array[i] = this.sort_array[start+i];
        for (int j = 0; j < end-mid; ++j)
            right_array[j] = this.sort_array[mid + 1 + j];
        int i = 0;
        int j = 0;
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

    public static void main(String[] args) throws Exception {
        // Random Array
        int experiments_number = 1000;
        double[] running_time = new double[experiments_number];
        double[] problem_size = new double[experiments_number];
        for(int i=0; i < experiments_number; i++){
            int array_len = 1000*(i+1);
            var random_array = new Integer[array_len];
            for (int j=0; j < array_len;j++){
                random_array[j] = ThreadLocalRandom.current().nextInt(-1000, 1000 + 1);
            }
            // Sorting
            long start = System.currentTimeMillis();
            var merge_sort = new MergeSort<Integer>(random_array);
            merge_sort.sort();
            long finish = System.currentTimeMillis();
            long timeElapsed = finish - start;
            problem_size[i] = array_len;
            running_time[i] = timeElapsed;
        }
        // ------------
        // Region: Plot
        // ------------
        XYChart chart = QuickChart.getChart("Running time", "n", "t", "t(n)", problem_size, running_time);
        new SwingWrapper(chart).displayChart();
        // ----------------
        // End Region: Plot
        // ----------------
        System.out.println("Experiments Finished!");
    }
}
