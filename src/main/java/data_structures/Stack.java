package data_structures;

import generic_classes.Node;
import generic_classes.Result;

public class Stack{
    /**
    Description
    -----------
    The class provides an implementation of stack using class Node. The class allows every node to contain a different
    type of variable. This is achieved by passing instances of class Node to push method.

    Log
    ---

    Developer(s)
    ------------
    Milad Avazbeigi
     **/
    private Node head = null;

    // -------------------
    // Region: Constructor
    // -------------------
    public Stack(Node head){
        this.head = head;
    }
    // -----------------------
    // End Region: Constructor
    // -----------------------

    // ---------------------------
    // Region: getHead and setHead
    // ---------------------------
    public Node getHead() {
        return head;
    }
    public void setHead(Node head) {
        this.head = head;
    }
    // -------------------------------
    // End Region: getHead and setHead
    // -------------------------------

    // -------------------
    // Region: Method push
    // -------------------
    public void push(Node node){
        if(this.head==null){
            this.head = node;
        }else{
            node.next = this.head;
            this.head = node;
        }
    }
    // -----------------------
    // End Region: Method push
    // -----------------------

    // ------------------
    // Region: Method pop
    // ------------------
    public Node pop(){
        if(this.head==null){
            throw new NullPointerException();
        }else{
            Node head = this.head;
            this.head = this.head.next;
            return head;
        }
    }
    // -----------------------
    // End Region: Method push
    // -----------------------

    // -------------------
    // Region: Method peek
    // -------------------
    public Result peek(){
        if(this.head==null){
            throw new NullPointerException();
        }else{
            return new Result(this.head.getValue());
        }
    }
    // -----------------------
    // End Region: Method peek
    // -----------------------

    // -----------------------
    // Region: Method toString
    // -----------------------
    public String toString(){
        StringBuilder output_srt = new StringBuilder();
        if(this.head==null){
            output_srt = new StringBuilder("'Stack' class instance: empty stack!");
        }else{
            var current_node = this.head;
            output_srt.append("========\n");
            output_srt.append(current_node.getValue());
            while(current_node.next != null){
                current_node = current_node.next;
                output_srt.append("\n^\n"+current_node.getValue());
            }
            output_srt.append("\n========");
        }
        return output_srt.toString();
    }
    // ---------------------------
    // End Region: Method toString
    // ---------------------------

    public static void main(String[] args){
        var head = new Node<Integer>(12);
        var stack_object = new Stack(head);
        var new_node1 = new Node<Double>(12.12);
        var new_node2 = new Node<Character>('a');
        var new_node3 = new Node<String>("Asddas");
        stack_object.push(new_node1);
        System.out.println(stack_object.peek());
        stack_object.push(new_node2);
        stack_object.push(new_node3);
        System.out.println(stack_object);
        stack_object.pop();
        stack_object.pop();
        System.out.println(stack_object);
    }
}
