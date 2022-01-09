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

    // ---------------------------
    // Region: getters and setters
    // ---------------------------
    public U getValue() {
        return this.value;
    }
    public void setValue(U value) {
        this.value = value;
    }

    public void setNext(BidirectionalNode next) {
        this.next = next;
    }
    public BidirectionalNode getNext() {
        return next;
    }

    public void setPrevious(BidirectionalNode previous) {
        this.previous = previous;
    }
    public BidirectionalNode getPrevious() {
        return previous;
    }
    // -------------------------------
    // End Region: getters and setters
    // -------------------------------

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