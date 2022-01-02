package generic_classes;

public class BidirectionalNode<U>{
    private U value=null;
    public BidirectionalNode next=null;
    public BidirectionalNode previous=null;

    // --------------------
    // Region: Constructors
    // --------------------
    public BidirectionalNode(U value) {
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
        return "BidirectionalNode{" +
                "value=" + this.value +
                '}';
    }
    // --------------------
    // End Region: toString
    // --------------------

    public static void main(String[] args){
        var integer_node = new BidirectionalNode<Integer>(12);
        System.out.println(integer_node.toString());
        System.out.println(integer_node);
        System.out.println(integer_node.getValue());
    }
}