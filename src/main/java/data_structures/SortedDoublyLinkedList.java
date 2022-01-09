package data_structures;

import generic_classes.BidirectionalNode;

public class SortedDoublyLinkedList <T extends Comparable<T>>{
    /**
     * Example  <br/>
     * ---------  <br/>
     * >>>  SortedDoublyLinkedList<Integer> list = new SortedDoublyLinkedList<Integer>(); <br/>
     * >>>  int[] nums = new int[]{1,6,32, 8, 0, -700, -700, 0, 9, 9, 100, -65, 1000, 1000, -548};  <br/>
     * >>>  for (int num : nums)  <br/>
     * >>>      list.insert(num);  <br/>
     * >>>  System.out.println(list);  <br/>
     * // SortedDoublyLinkedList{null <- -700 <-> -700 <-> -548 <-> -65 <-> 0 <-> 0 <-> 1 <-> 6 <-> 8 <-> 9 <-> 9 <-> 32 <-> 100 <-> 1000 <-> 1000 -> null}  <br/>
     * >>>  list.delete(1000); <br/>
     * >>>  System.out.println(list); <br/>
     * >>>  list.delete(-701000); <br/>
     * >>>  System.out.println(list); <br/>
     * >>>  list.delete(-700); <br/>
     * >>>  System.out.println(list); <br/>
     */
    private BidirectionalNode<T> head = null;
    private BidirectionalNode<T> tail = null;

    public void insert(T x){
        if(this.head == null && this.tail == null){ // empty list
            this.head = new BidirectionalNode<T>(x);
            this.tail = this.head;
        }else{ // non-empty list
            if(this.head.getValue().compareTo(x) >= 0){ // x <= head.value: update head
                BidirectionalNode<T> temp = this.head;
                this.head = new BidirectionalNode<T>(x);
                this.head.setNext(temp);
                this.head.getNext().setPrevious(this.head);
            }else if(this.tail.getValue().compareTo(x) <= 0){ // tail.value < x: update tail
                BidirectionalNode<T> temp = this.tail;
                this.tail = new BidirectionalNode<T>(x);
                this.tail.setPrevious(temp);
                this.tail.getPrevious().setNext(this.tail);
            }else{ // find the right position
                BidirectionalNode<T> current = this.head;
                while(current != null){
                    if(current.getValue().compareTo(x)>=0){
                        BidirectionalNode new_node = new BidirectionalNode<T>(x);
                        BidirectionalNode<T> temp = current.getPrevious();
                        new_node.setNext(current);
                        new_node.setPrevious(current.getPrevious());
                        current.setPrevious(new_node);
                        temp.setNext(new_node);
                        break;
                    }else{
                        current = current.next;
                    }
                }
            }
        }
    }

    public void delete(T x){
        if(this.head != null && this.tail != null){ // list is not empty
            if(this.head.getValue().compareTo(x) == 0){ // head has value x
                while((this.head != null) && (this.head.getValue().compareTo(x) == 0)){ // update head
                    this.head = this.head.next;
                    this.head.previous = null;
                }
            }
            else if(this.tail.getValue().compareTo(x) == 0){ // tail has value x
                while((this.tail != null) && (this.tail.getValue().compareTo(x) == 0)){ // update tail
                    this.tail = this.tail.previous;
                    this.tail.next = null;
                }
            }else{ // Finding the right position
                if(this.head.next != null){
                    BidirectionalNode<T> current = this.head.next;
                    while(current != null){
                        if(current.getValue().compareTo(x) == 0){ // delete
                            current.next.previous = current.previous;
                            current.previous.next = current.next;
                        }
                        current = current.next;
                    }
                }
            }
        }
    }

    public String toString(){
        String listString = "";
        if(this.head == null && this.tail == null){
            listString = "SortedDoublyLinkedList{null <- () -> null}";
        }else{
            listString = "SortedDoublyLinkedList{null <- " + this.head.getValue();
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
