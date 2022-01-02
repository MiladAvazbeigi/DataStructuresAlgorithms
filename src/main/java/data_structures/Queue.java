package data_structures;

import generic_classes.Node;
import generic_classes.Result;

public class Queue {
    /**
    Description
    -----------
    The class provides an implementation of queue using class Node. The class allows every node to contain a different
    type of variable. This is achieved by passing instances of class Node to queue method. Queues are LIFO systems
    which means the 'enqueue' adds to the tail of the queue and 'dequeue' removes the objects from the first.

    Log
    ---

    Developer(s)
    ------------
    Milad Avazbeigi
     **/
    private Node head = null;
    private Node tail = null;

    // -------------------
    // Region: Constructor
    // -------------------
    public Queue(Node head){
        this.head = head;
        this.tail = head;
    }
    // -----------------------
    // End Region: Constructor
    // -----------------------

    // ---------------------------
    // Region: getHead and getTail
    // ---------------------------
    public Node getHead() {
        return this.head;
    }
    public Node getTail() {
        return this.tail;
    }
    // -------------------------------
    // End Region: getHead and getTail
    // -------------------------------

    // -------------------
    // Region: Method push
    // -------------------
    public void enqueue(Node node){
        if(this.tail==null){
            this.head = node;
            this.tail = node;
        }else{
            this.tail.next = node;
            this.tail = node;
        }
    }
    // -----------------------
    // End Region: Method push
    // -----------------------

    // ------------------
    // Region: Method pop
    // ------------------
    public Node dequeue(){
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
            output_srt.append("Queue{");
            output_srt.append(current_node.getValue());
            while(current_node.next != null){
                current_node = current_node.next;
                output_srt.append("<="+current_node.getValue());
            }
            output_srt.append("}");
        }
        return output_srt.toString();
    }
    // ---------------------------
    // End Region: Method toString
    // ---------------------------

    public static void main(String[] args){
        var head = new Node<Integer>(12);
        var stack_object = new Queue(head);
        var new_node1 = new Node<Double>(234.65);
        var new_node2 = new Node<Character>('a');
        var new_node3 = new Node<String>("Asddas");
        System.out.println(stack_object);
        stack_object.enqueue(new_node1);
        System.out.println(stack_object);
        stack_object.enqueue(new_node2);
        System.out.println(stack_object);
        stack_object.enqueue(new_node3);
        System.out.println(stack_object);
        stack_object.dequeue();
        System.out.println(stack_object);
        stack_object.dequeue();
        System.out.println(stack_object);
    }
}
