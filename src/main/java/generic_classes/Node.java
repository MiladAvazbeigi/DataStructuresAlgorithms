package generic_classes;

public class Node<U>{
    private U value=null;
    public Node next=null;

    // --------------------
    // Region: Constructors
    // --------------------
    public Node(U value) {
        this.value = value;
    }
    // ------------------------
    // End Region: Constructors
    // ------------------------

    // -----------------------------
    // Region: getValue and setValue
    // -----------------------------
    public U getValue() {
        return this.value;
    }
    public void setValue(U value) {
        this.value = value;
    }
    // ---------------------------------
    // End Region: getValue and setValue
    // ---------------------------------

    // ----------------
    // Region: toString
    // ----------------
    public String toString() {
        return "Node{" +
                "value=" + this.value +
                '}';
    }
    // --------------------
    // End Region: toString
    // --------------------

    public static void main(String[] args){
        var integer_node = new Node<Integer>(12);
        System.out.println(integer_node.toString());
        System.out.println(integer_node);
        System.out.println(integer_node.getValue());
    }
}