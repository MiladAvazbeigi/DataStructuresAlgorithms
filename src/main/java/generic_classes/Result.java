package generic_classes;

public class Result <T>{
    /** class Result over generic type T
     * Description
     * -----------
     * The class provides a generic type that encapsulates any other class's instance. The class is useful when we
     * want to return a type which is to be determined during run-time.
     *
     * Methods
     * -------
     * - T getValue()
     * - void setValue(T value)
     * - String toString()
     */
    private T value = null;

    public Result(T value){
        this.value = value;
    }

    // ==========================
    // Region: Getter and Setters
    // ==========================
    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        this.value = value;
    }
    // ==============================
    // End Region: Getter and Setters
    // ==============================

    // ================
    // Region: toString
    // ================
    @Override
    public String toString() {
        return "Result{" +
                "value=" + this.value +
                '}';
    }
    // ====================
    // End Region: toString
    // ====================
}