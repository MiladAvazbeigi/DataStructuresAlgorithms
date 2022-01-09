package data_structures;

import generic_classes.BidirectionalNode;

public class DoublyLinkedList<T extends Comparable<T>>{
    /**
     * Example  <br/>
     * ---------  <br/>
     * >>> DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>(); <br/>
     * >>> list.addToHead(0); <br/>
     * >>> System.out.println(list); <br/>
     * >>> list.addToHead(1); <br/>
     * >>> System.out.println(list); <br/>
     * >>> list.addToHead(2); <br/>
     * >>> System.out.println(list); <br/>
     * >>> list.addToTail(-1); <br/>
     * >>> System.out.println(list); <br/>
     * >>> list.addToTail(-2); <br/>
     * >>> System.out.println(list); <br/>
     * >>> list.dropHead(); <br/>
     * >>> System.out.println(list); <br/>
     * >>> list.dropTail(); <br/>
     * >>> System.out.println(list); <br/>

     */
    private BidirectionalNode<T> head = null;
    private BidirectionalNode<T> tail = null;

    public void addToHead(T x){
        if(this.head == null && this.tail == null){ // empty list
            this.head = new BidirectionalNode<T>(x);
            this.tail = this.head;
        }else{ // non-empty list
            BidirectionalNode<T> temp = this.head;
            this.head = new BidirectionalNode<T>(x);
            this.head.setNext(temp);
            this.head.getNext().setPrevious(this.head);
        }
    }

    public void addToTail(T x){
        if(this.head == null && this.tail == null){ // empty list
            this.head = new BidirectionalNode<T>(x);
            this.tail = this.head;
        }else{ // non-empty list
            BidirectionalNode<T> temp = this.tail;
            this.tail = new BidirectionalNode<T>(x);
            this.tail.setPrevious(temp);
            this.tail.getPrevious().setNext(this.tail);
        }
    }

    public void dropHead(){
        if(this.head != null && this.tail != null){ // list is not empty
            this.head = this.head.next;
            this.head.previous = null;
        }
    }

    public void dropTail(){
        if(this.head != null && this.tail != null){ // list is not empty
            this.tail = this.tail.previous;
            this.tail.next = null;
        }
    }

    public String toString(){
        String listString = "";
        if(this.head == null && this.tail == null){
            listString = "DoublyLinkedList{null <- () -> null}";
        }else{
            listString = "DoublyLinkedList{null <- " + this.head.getValue();
            BidirectionalNode<T> current = this.head.next;
            while(current != null){
                listString = listString + " <-> " + current.getValue();
                current = current.next;
            }
            listString = listString + " -> null}";
        }
        return listString;
    }
}
