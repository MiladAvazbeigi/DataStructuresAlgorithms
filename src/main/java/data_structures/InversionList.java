package data_structures;

public class InversionList {
    /**
     * InversionList is an abstract data structure for representing union of intervals via bounds of the interval.
     * We simple need an even number of bounds. When the number of bounds is odd, the last interval will be treated as
     * an half open interval. Here are some examples:
     * {10, 20, 25, 45, 50, 55}) represents [10,19] U [25,44] U [50,54]
     * {Integer.MAX_VALUE} represents the singleton [2147483647]
     * {10, 20, 21, 30} represents [10,19] U [21,29]
     * {Integer.MIN_VALUE} represents [-2147483648,2147483647]
     * {Integer.MIN_VALUE, Integer.MAX_VALUE} represents [-2147483648,2147483646]
     * {5, 10, Integer.MAX_VALUE} represents [5,9] U [2147483647]
     * {15, 30, 35, 40, 60, 65} represents [15,29] U [35,39] U [60,64]
     * {65, 100} represents [65,99]
     *
     * The class provides complete set of operations for such objects including:
     * - complement
     * - union
     * - intersection
     * - subtraction
     * - subset method
     * - insert method
     * - contains method
     * - delete method
     */
    public int[] bounds = null; // Stores the inversion list in its original format, numbers are strictly increasing

    public InversionList() {
        this.bounds = null;
    }

    // Non-Empty Constructor
    public InversionList(int[] a) {
        if(a != null){
            this.bounds = a;
        }else{
            this.bounds = null;
        }
    }
    // Constructor from another Inversion List
    public InversionList(InversionList a) {
        this.bounds = a.bounds.clone(); // copying without using the pointer to a
    }

    /* Time Complexity of Cardinality:
        Given an inversion list of size n, first we convert the inversion list into a set of closed intervals
        and push all intervals into our customized stack of intervals. This procedure has time complexity of O(n).
        Then, we start from top, counting and adding the cardinality of each closed intervals (See while loop below).
        This proceudre is also of order of O(n). So, instance method cardinality() is of O(n).
    */
    public long cardinality(){
        long card = 0;
        if(!this.isEmpty()){// The inversion list is not empty
            Stack listStack = this.inversionlist2Stack(); // Converts the Inversion List to a Stack of closed intervals
            while(listStack.head != null){
                card = card + ((long)listStack.head.sValue-(long)listStack.head.fValue+1); // +1:[fValue,sValue] includes sValue-fValue+1 inetegrs
                listStack.pop();
            }
        }
        return card;
    }

    /* Time Complexity of insert(int value):
        Given the "value", first we represent the "value" with an inversion list. This procedure is of order O(1). The
        resulted Inversion List called "singletonList" has length of at most m = 2. Then, we simply find the union
        of two Inversion Lists "this" (size n) and "singletonList" (size m) using union method. The union method has time
        complexity of O(m+n) for two Inversion Lists of size m and n. But m = 2 or 1. So, insert(int value) has time
        complexity of O(m+n) = O(n+2) or O(n+1) which is O(n).
    */
    public void insert(int value){
        InversionList singletonList;
        // Represents the value with an Inversion List
        if(value == Integer.MAX_VALUE){
            // We need an odd inversion list including one integer namely Integer.MAX_VALUE
            singletonList = new InversionList(new int[] {Integer.MAX_VALUE});
        }else{
            // We need an even inversion list
            singletonList = new InversionList(new int[] {value, value+1});
        }
        // Finding union of the singletonList with "this" Inversion List and updating the bounds
        this.bounds = this.union(singletonList).bounds;
    }

    /* Time Complexity of delete(int value):
        Given the "value", first we represent the "value" with an inversion list. This procedure is of order O(1). The
        resulted Inversion List called "singletonList" has length of at most m = 2. Then, we simply find the difference
        of two inversion lists "this" (length n) and "singletonList" (length 2 or 1) i.e. this\singletonList.
        But, difference has time complexity of O(n+2) which is O(n).
    */
    public void delete(int value) {
        InversionList singletonList;
        // Represents the value with an inversion list
        if(value == Integer.MAX_VALUE){
            // We need an odd inversion list including one integer namely Integer.MAX_VALUE
            singletonList = new InversionList(new int[] {Integer.MAX_VALUE});
        }else{
            // We need an even inversion list
            singletonList = new InversionList(new int[] {value, value+1});
        }
        // Finding "this" - "singletonList"
        this.bounds = this.difference(singletonList).bounds;
    }

    /* Time Complexity of contains(int key): O(log(n))
        In order to find out whether an Inversion List of size n contains "key", we use binary search. At each step, we
        check whether "key" is larger than or smaller than number in position mid = (high+low)/2. If "key" is greater than
        the number in position mid, then we set low = mid. Otherwise we set high = mid.
        Such an algorithm has time complexity of O(log(n)). This can be proved using
        Master Theorem as T(n) = T(n/2) + O(1) which gives O(log(n)).
    */
    public boolean contains(int key) {
        if(this.isEmpty()){ // Empty set is empty and does not have any member.
            return false;
        }
        int low = 0;
        int high = this.bounds.length-1;
        // Taking care of the rightmost and leftmost bounds of an Inversion List
        if(this.bounds.length%2 != 0){ // Odd inversion list: Example is 10 12 18 20 30
            if(key >= this.bounds[this.bounds.length-1]){ // key is included in the right side of the inversion list: key>=30
                return true;
            }else if(key >= this.bounds[this.bounds.length-2]){ // key>=20 and key<30 which is not a part of odd inversion list
                return false;
            }else if(key < this.bounds[0]){ // key < 10: So, the inversion list does not contain the key
                return false;
            }else{
                // If we have made it so far, then we must check the inner intervals.
                // We discard the last element of the odd inversion list (here 30) and
                // the remaining is an even list 10 12 18 20. The high index must now point
                // to value 20. Thus, high = high -1.
                high = high - 1;
            }
        }else{ // Even inversion list: no need to change high. Example is 10 12 18 20
            if(key >= this.bounds[this.bounds.length-1]){ // Right Side:  x>=20 which is not a part of an even inversion list
                return false;
            }else if(key < this.bounds[0]){ // Left side: x<10 which is not a part of the list
                return false;
            }
        }
        boolean cnt = false; // false if the InversionList does not contain the key, otherwise true
        int mid = (low + high) / 2;
        // Checking the middle intervals of the remaining even list using the binary search
        while(high > low){
            if(high - low == 1){ // Termination condition: Only one interval left
                // In inversion list 10 12 18 20 30 40, if low = 2 and high = 3, then key>=18 and key<20 means that the key is contained in the list
                if(low%2 == 0 && key>= this.bounds[low] && key<this.bounds[high]){
                    cnt = true;
                    break;
                }else if ((low%2 != 0) && key>= this.bounds[low] && key<this.bounds[high]){ // Example: if low=1 and high=2, then key>=12 and key<18 means the key is not in the list
                    cnt = false;
                    break;
                }
            }else{// Recursive part
                if(key>= this.bounds[mid-1] && key<this.bounds[mid]){
                    if(mid%2 == 0){ // Given 10 12 18 20 30 40, mid = (int)(5+0)/2=2 is even
                        cnt = false; // if key>=12 and key<18, this means that the list does NOT contain the key
                        break;
                    }else{ // Given 10 12 18 20 30 45 50, mid = (int)(6+0)/2=3 is odd
                        cnt = true; // if key>=18 and key<20, this means that the list contains the key
                        break;
                    }
                }else if(key >= this.bounds[mid]){ // Given 10 12 18 20 30 40, if key>=18, then we discard left part and "mid" becomes the "low" index, "high" does not change
                    low = mid; // Continue with inversion list 18 20 30 40
                }else if(key < this.bounds[mid-1]){ // Given 10 12 18 20 30 40, if key<12, then we discard right part and "mid" becomes the "high" index, "low" does not change
                    high = mid; // Continue with inversion list 10 12
                }
                mid = (low + high) / 2;
            }
        }
        return cnt;
    }

    /* Time Complexity of subsetOf(InversionList b): O(m+n)
        Set A is a subset of set B iff union of A and B is equal set B. Assume that Inversion List b has length n and
        "this" has length m. To find union of b and "this", we use union method which has time complexity of order O(m+n).
        Then, we check whether the union Inverison List is equal to Inversion List b using equals() method. This method
        is also O(m+n). We conclude that subsetOf(InversionList b) is of order O(m+n).
    */
    public boolean subsetOf(InversionList b) {
        return this.union(b).equals(b);
    }

    /* Time Complexity of equals: O(m+n)
        Inversion Lists have one UNIQUE minimal representation i.e. we avoid cases such as {19, 22, 22, 40}.
        This Inversion List can be simply represented by {19, 40}. Thus, it suffices to simply compare the bounds.
        If the number of bounds differs, then they can not be equal. Otherwise, if "this" Inversion List has length
        m and "b" has length n (m = n), then we need at most m comparisons of bounds. This has time complexity
        of order O(m) = O(n) = O(MAX(m,n)) = O(m+n). In case m is not equal n, equals() is of order O(1).
        So, equals(InversionList b) is of order O(m+n).
    */
    public boolean equals(InversionList b) {
        boolean output = true;
        if(this.isEmpty() && b.isEmpty()){ // Two Empty sets are equal
            output = true;
        }else if((!this.isEmpty() && b.isEmpty()) || (this.isEmpty() && !b.isEmpty())){ // One set empty, the other non-empty
            output = false;
        }else{ // Both sets are non-empty
            if(this.bounds.length != b.bounds.length){ // The length of two Inversion Lists are are different. No need to continue
                output = false;
            }else{ // The two Inversion Lists have the same length
                int n = b.bounds.length; // or this.bounds.length
                for(int i = 0; i<n; i++){
                    if(b.bounds[i] != this.bounds[i]){
                        output = false;
                        break; // no need to continue checking, inversion lists are not equal
                    }
                }
            }
        }
        return output;
    }

    /*
    Time Complexity of isEmpty(): We simply check if the bounds of the Inversion List
    is empty or not. This has time complexity of O(1).
    */
    public boolean isEmpty() {
        if(this.bounds == null){
            return true;
        }else{
            return false;
        }
    }
    /*
    Time Complexity of complement(): O(n)
        First, we convert the inversion list of length n to a stack of closed
        intervals. This has time complexity of O(n). As the stack has at most [n/2]+1
        intervals, we have to calculate [n/2]+2 intervals to find the complement stack.
        This is done using a while loop below (while(listS.tail != null)). As each iteration
        has time complexity of O(1), this operation is also O(n). Finally,
        we convert the complement stack to an inversion list. This operaion is also O(n).
        In total, complement() is O(n).
    */
    public InversionList complement() {
        if(this.isEmpty()){ // List is empty, complement is the whole space
            return new InversionList(new int[] {Integer.MIN_VALUE});  // An odd Inversion List with one element
        }else{// List is NOT empty
            Stack listS = this.inversionlist2Stack(); // Inversion list is converted to a stack of closed intervals.
            Stack complementS = new Stack(); // Store the complement stack: stacks of closed intervals
            int prev = 0;
            // Checking for Integer.MIN_VALUE: Left complement of [x, ...] when x>Integer.MIN_VALUE is [Integer.MIN_VALUE, x-1]
            // However, if x = Integer.MIN_VALUE, left complement of [Integer.MIN_VALUE, ...] is the empty set.
            if(listS.tail.fValue != Integer.MIN_VALUE){ // listS.tail is the first closed interval in "this" inversion list
                complementS.push(Integer.MIN_VALUE, listS.tail.fValue-1); // Left complement of [listS.tail.fValue, ...] is [Integer.MIN_VALUE, list.tail.fValue-1]
            }
            prev = listS.tail.sValue;
            listS.kick(); // removing the first interval from the stack of "this" inversion list
            // Complements of middle intervals
            while(listS.tail != null){ // ListS is stil not empty
                complementS.push(prev+1, listS.tail.fValue-1);
                prev = listS.tail.sValue;
                listS.kick(); // removing the first interval from the stack of "this" inversion list
            }
            // Checking for Integer.MAX_VALUE: Right complement of [..., x] when x<Integer.MAX_VALUE is [x+1, Integer.MAX_VALUE]
            // However, if x = Integer.MAX_VALUE, right complement of [..., Integer.MAX_VALUE] is empty set.
            if(prev != Integer.MAX_VALUE){
                complementS.push(prev+1, Integer.MAX_VALUE);
            }
            return complementS.stack2InversionList();
        }
    }
    /*
    Time Complexity: O(m+n)
    In order to find the union of two inversion lists with length m and n respectively, first we convert
    each to a stack of closed intervals using inversionlist2Stack(). This has time complexity of O(m)
    and O(n) respectively and in total O(m+n). Then, we combine the two stacks into one stack called "common" stack w.r.t.
    first interval bounds i.e. [2,4] then [3,8], then [9,12] and so on (2<=3<=9, see example below). This also has
    time complexity of O(m+n) as common has m+n elements. Finally, we find the union by merging those intervals which
    intersect and simply pushing those that do not intersect with previously added intervals.
    This has obviously time complexity of O(m+n) too. In total, union has time complexity of O(m+n).
    Stack methods: remove an item from the head: pop(), remove an item from the tail: kick(),
    add an item to the head: push(), add an item to the tail: Queue().
    */
    public InversionList union(InversionList b) {
        if(b.isEmpty() && this.isEmpty()){
            return new InversionList(); // Union of two empty inversion lists is an empty inversion list
        }else if(!b.isEmpty() && this.isEmpty()){
            return b; // (b) U (this) = b, as "this" is empty
        }else if(b.isEmpty() && !this.isEmpty()){
            return this; // (b) U (this) = "this", as b is empty
        }else{// Both are non-empty and we need to calculate the union
            Stack bStack = b.inversionlist2Stack();
            Stack thisStack = this.inversionlist2Stack();
            // Pushing all intervals from thisStack and bStack into one stack called "common"
            // in an ascending order w.r.t. first interval bound (-2147483648, 20, 30, 40, 45, 55, 65). An example is given below:

            // bStack: top to bottom
            // [65,2147483647]
            // [40,59]
            // [30,34]
            // [-2147483648,14]

            // This stack: top to bottom
            // [55,69]
            // [45,49]
            // [20,24]
            // [-2147483648,9]

            // Common stack: top to bottom
            // [-2147483648,9]
            // [-2147483648,14]
            // [20,24]
            // [30,34]
            // [40,59]
            // [45,49]
            // [55,69]
            // [65,2147483647]
            Stack common = new Stack();
            while(!thisStack.isEmpty() || !bStack.isEmpty()){ // m + n elements at most
                // thisStack is empty, we add all remaining intervals from bStack
                if(thisStack.isEmpty() && !bStack.isEmpty()){
                    common.queue(bStack.tail.fValue, bStack.tail.sValue); // take from bStack tail and add to tail of common
                    bStack.kick();
                    // bStack is empty, we add all remaining intervals from thisStack
                }else if(!thisStack.isEmpty() && bStack.isEmpty()){
                    common.queue(thisStack.tail.fValue, thisStack.tail.sValue); // take from thisStack
                    thisStack.kick();
                    // Both are non-empty, we need to compare first bounds of intervals
                    // For example [2, 8] is pushed before [3, 7] as 2 <= 3
                }else if(!thisStack.isEmpty() && !bStack.isEmpty()){
                    if(thisStack.tail.fValue <= bStack.tail.fValue){
                        common.queue(thisStack.tail.fValue, thisStack.tail.sValue); // take from thisStack
                        thisStack.kick();
                    }else{ // thisStack.tail.fValue > bStack.tail.fValue)
                        common.queue(bStack.tail.fValue, bStack.tail.sValue); // take from bStack
                        bStack.kick();
                    }
                }
            }
            // Finding union
            // Common stack: top to bottom
            // [-2147483648,9]
            // [-2147483648,14]
            // [20,24]
            // [30,34]
            // [40,59]
            // [45,49]
            // [55,69]
            // [65,2147483647]
            // Here, first we remove [-2147483648,9] from common stack and add to the top of union stack. Then, as -2147483648<=9,
            // there might be an intersection. As 14>=9, we update 9 to 14. Now, only [-2147483648,14] is in
            // the union stack. We also remove [-2147483648,14] from Common stack. Next, as 20>14,
            // we simply remove [20,24] from common stack and add it to the top of common stack and so on.
            Stack unionStack = new Stack(); // stack of closed intervals containing the union of all intervals
            unionStack.push(common.head.fValue, common.head.sValue);
            common.pop(); // Delete the interval from common stack
            while(!common.isEmpty()){ // common stack is not empty with m+n elements
                if(unionStack.head.sValue >= common.head.fValue){ // Union of [x, y] and [z, q] when z <= y
                    if(unionStack.head.sValue <= common.head.sValue){ // union of [2,6] and [3,7] is [3,7] as 6<=7
                        unionStack.head.sValue = common.head.sValue;
                        common.pop();
                    }else{ // Union of [2,10] and [3,8] is still [2,10]. No need to update unionStack.head.sValue (here 10)
                        common.pop();
                    }
                }else{ // Union of [x, y] and [z, q] when z > y, [x, y] from unionStack and [z, q] from common
                    if((common.head.fValue - unionStack.head.sValue) == 1){ // Union of [z, x] and [x+1, q] is [z,q]
                        unionStack.head.sValue = common.head.sValue; // x is updated to q
                        common.pop();
                    }else{// No intersection, simply push the interval from top of common stack to top of union stack
                        unionStack.push(common.head.fValue, common.head.sValue);
                        common.pop();
                    }
                }
            }
            return unionStack.stack2InversionList();
        }
    }

    /* Time Complexity of A intersect B: O(m+n)
        Assume that we have two inversion lists A and B with length m and n respectively.
        First, we find the complement of each inversion lists. This has O(m) of A and O(n) for B. So, in
        total O(m+n). Then, we find the union of the two complements. This has time complexity of
        O(m+n) as A complement has length at most m+1 and B complement has length at most n+1.
        Finally, we find the complement of union of A complement and B complement. This also is O(m+n).
        So, in total intersection(InversionList b) has time complexity of O(m+n).
    */
    public InversionList intersection(InversionList b) {
        InversionList unionList = this.complement().union(b.complement());
        return unionList.complement();
    }
    /* Time Complexity of difference A\B: O(m+n)
        Assume that we have two inversion lists A and B with length m and n respectively. To find A\B,
        first we find the complement of B. This is O(n). B complement is an inversion list with at most
        length n+1. Then, we find the intersection of A and complement of B which is O(m+n).
        In total, difference(InversionList b) has time complexity of O(m+n).
    */
    public InversionList difference(InversionList b){
        return this.intersection(b.complement());
    }

    /*
    Time Complexity of toString(): O(n)
    First, we convert the inversion list to a stack of closed intervals. If inversion list has length n, this has
    time complexity of O(n). Then, we keep adding each interval to the string of inversion list. As there
    are at most [n/2]+1 intervals(n/2 intervals for even inversion lists and [n/2]+1 for odd inversion lists),
    this operation has time complexity of O(n) (While loop below, each loop is O(1)). So, in total toString()
    has time complexity O(n).
    */
    public String toString(){
        String stackStr = "";
        if(!this.isEmpty()){ // Stack of closed intervals is not empty
            Stack thisStack = this.inversionlist2Stack(); // Stack representation of inversion list
            while(thisStack.tail != null){
                if(thisStack.tail.previous != null){
                    if(thisStack.tail.sValue == thisStack.tail.fValue){ // The closed interval [a,a] is printed as [a]
                        stackStr = stackStr + "[" + thisStack.tail.fValue + "] U ";
                    }else{
                        stackStr = stackStr + "[" + thisStack.tail.fValue + "," + thisStack.tail.sValue + "] U ";
                    }
                }else{ // We have reached the end of stack .i.e after the kick stack will be empty
                    if(thisStack.tail.sValue == thisStack.tail.fValue){ // The closed interval [a,a] is printed as [a]
                        stackStr = stackStr + "[" + thisStack.tail.fValue + "]";
                    }else{
                        stackStr = stackStr + "[" + thisStack.tail.fValue + "," + thisStack.tail.sValue + "]";
                    }
                }
                thisStack.kick(); // Delete from tail of stack
            }
        }else{ // Empty stack
            stackStr = "empty";
        }
        return stackStr;
    }

    // The instance method converts an Inversion List to stack of closed intervals
	/*
	Time Complexity: For an inversion list of length n, we need at most [n/2]+1 iterations with O(1) which is of order O(n).
	These are shown in for-loops below. For example, for an even inversion list such as {10, 20, 25, 45, 50, 55},
	the output is a stack as follows:
	[50, 54]
	[25, 44]
	[10, 19]
	For an odd inversion list such as {10, 20, 25}, the output is a stack as follows:
	[25, 2147483647]
	[10, 19]
	*/
    public Stack inversionlist2Stack() {
        Stack listStack = new Stack();
        if(!this.isEmpty()){
            if(this.bounds.length%2!=0){// This is an odd inversion list
                for(int i = 0; 2*i < (this.bounds.length-1); i++){
                    listStack.push(this.bounds[2*i], this.bounds[2*i+1]-1);
                }
                listStack.push(this.bounds[this.bounds.length-1], Integer.MAX_VALUE);
            }else{// This is an even inversion list
                for(int i = 0; 2*i < this.bounds.length; i++){
                    listStack.push(this.bounds[2*i], this.bounds[2*i+1]-1);
                }
            }
        }
        return listStack;
    }

    // ================ Stack of closed intervals: Each memeber of the stack is an item which
    // represents a closed interval. Each item is connected to the next and the previous item.
    // Stack has both head and tail. We can remove items from either the head using pop() method or
    // the tail using kick() method. We can also add items to either the head using push() or the tail
    // using queue() method.
    private class Stack{
        public Item head = null;
        public Item tail = null;
        public int length = 0; // length of stack
        // Add to the top
        public void push(int f, int s){ // f for the first bound of the interval and s for the second one
            this.length = this.length + 1;
            Item next = new Item(f,s, null, null);
            if(this.head == null){ // Stack is empty: head = tail = next
                this.head = next;
                this.tail = next;
            }else{
                next.next = this.head;
                this.head.previous = next;
                this.head = next; // updating the head
            }
        }
        // Add to the end
        public void queue(int f, int s){ // f for the first bound of the interval and s for the second one
            this.length = this.length + 1;
            Item next = new Item(f,s, null, null);
            if(this.head == null){ // Stack is empty: head = tail = next
                this.head = next;
                this.tail = next;
            }else{
                next.previous = this.tail;
                this.tail.next = next;
                this.tail = next; // updating tail
            }
        }
        public int[] pop(){ // delete from head and return the interval
            if(this.head != null){ // Stack is not empty
                this.length = this.length - 1;
                int[] interval = new int[]{this.head.fValue, this.head.sValue};
                if(this.head.next == null){ // Stack will be empty after deletion
                    this.head = null;
                    this.tail = null;
                }else{
                    this.head = this.head.next;
                    this.head.previous = null;
                }
                return interval;
            }else{ // Stack is empty
                return null;
            }
        }
        public int[] kick(){ // delete from tail and return the interval
            if(this.tail != null){ // Stack is not empty
                this.length = this.length - 1;
                int[] interval = new int[] {this.tail.fValue, this.tail.sValue};
                if(this.tail.previous == null){ // Stack will be empty after deletion
                    this.head = null;
                    this.tail = null;
                }else{
                    this.tail = this.tail.previous;
                    this.tail.next = null;
                }
                return interval;
            }else{ // Stack is empty
                return null;
            }
        }
        // Checks if the stack of intervals is empty or not
        public boolean isEmpty(){
            if(this.head == null){
                return true;
            }else{
                return false;
            }
        }

        // Converts a stack of closed intervals into an inversion list with correct formatting, taking into
        // account whether the inversion list is odd or even.
        public InversionList stack2InversionList(){
            if(this.isEmpty()){ // Stack empty, so is the InversionList
                return new InversionList();
            }else{
                Item temp = this.tail;
                int[] nums;
                if(this.head.sValue == Integer.MAX_VALUE){ // Odd inversion list
                    nums = new int[2*this.length-1]; // No need to add last entry in an odd inversion list
                }else{
                    nums = new int[2*this.length];
                }
                int index = 0;
                while(temp != null){
                    if(temp.sValue != Integer.MAX_VALUE){ // temp.sValue < Integer.MAX_VALUE
                        nums[2*index] = temp.fValue;
                        nums[2*index+1] = temp.sValue + 1; // Format correction
                        index++;
                    }else{
                        nums[2*index] = temp.fValue;
                        index++;
                    }
                    temp = temp.previous;
                }
                return new InversionList(nums);
            }
        }
        public String toString(){ // Create a string of stack from top to bottom
            String strStack = "Stack view: top to bottom\n";
            Item temp = new Item(0, 0, null, null);
            if(this.head != null){ // Stack is not empty
                temp = this.head;
                while(temp != null){
                    strStack = strStack + "[" + temp.fValue + "," + temp.sValue + "]\n";
                    temp = temp.next;
                }
            }else{ // Stack is empty
                strStack = strStack + "The stack is empty";
            }
            return strStack;
        }
    }
    private class Item{ // A member of the stack: [fValue, sValue] with links to the previous and to the next element
        public int fValue; // First bound of a closed interval
        public int sValue; // Second bound of a closed interval
        public Item next;
        public Item previous;
        private Item(int f, int s, Item next, Item previous){
            this.fValue = f; this.sValue = s; this.next= next; this.previous = previous;
        }
    }

    public static void main(String[] args){
        InversionList a = new InversionList(new int[] {10, 20, 25, 45, 50, 55});
        InversionList b = new InversionList(new int[] {Integer.MAX_VALUE});
        InversionList c = new InversionList(new int[] {10, 20, 21, 30});
        InversionList d = new InversionList(new int[] {Integer.MIN_VALUE});
        InversionList e = new InversionList(new int[] {Integer.MIN_VALUE, Integer.MAX_VALUE});
        InversionList f = new InversionList(new int[] {5, 10, Integer.MAX_VALUE});
        InversionList g = new InversionList(new int[] {15, 30, 35, 40, 60, 65});
        InversionList h = new InversionList(new int[] {65, 100});
        InversionList E = new InversionList();



        System.out.println("a is " + a);
        System.out.println("b is " + b);
        System.out.println("c is " + c);
        System.out.println("d is " + d);
        System.out.println("e is " + e);
        System.out.println("f is " + f);
        System.out.println("g is " + g);
        System.out.println("h is " + h);
        System.out.println("E is " + E);



        System.out.println("Cardinality of a is " + a.cardinality());
        System.out.println("Cardinality of b is " + b.cardinality());
        System.out.println("Cardinality of c is " + c.cardinality());
        System.out.println("Cardinality of d is " + d.cardinality());
        System.out.println("Cardinality of e is " + e.cardinality());
        System.out.println("Cardinality of f is " + f.cardinality());
        System.out.println("Cardinality of h is " + h.cardinality());
        System.out.println("Cardinality of E is " + E.cardinality());




        System.out.println("Union of a and b is " + a.union(b));
        System.out.println("Union of b and a is " + b.union(a));
        System.out.println("Union of a and c is " + a.union(c));
        System.out.println("Union of c and a is " + c.union(a));
        System.out.println("Union of g and a is " + g.union(a));
        System.out.println("Union of a and g is " + a.union(g));
        System.out.println("Union of d and e is " + d.union(e));
        System.out.println("Union of e and f is " + e.union(f));
        System.out.println("Union of f and g is " + f.union(g));
        System.out.println("Union of g and f is " + g.union(f));
        System.out.println("Union of a and d is " + a.union(d));
        System.out.println("Union of g and h is " + g.union(h));
        System.out.println("Union of E and h is " + E.union(h));





        InversionList empty = new InversionList();
        System.out.println("Complement of empty set is " + empty.complement());
        System.out.println("Complement of a is " + a.complement());
        System.out.println("Complement of b is " + b.complement());
        System.out.println("Complement of c is " + c.complement());
        System.out.println("Complement of d is " + d.complement());
        System.out.println("Complement of e is " + e.complement());
        System.out.println("Complement of f is " + f.complement());
        System.out.println("Complement of g is " + g.complement());


        System.out.println("Intersection of a and c is " + a.intersection(c));
        System.out.println("Intersection of c and a is " + c.intersection(a));
        System.out.println("Intersection of a and b is " + a.intersection(b));
        System.out.println("Intersection of b and a is " + b.intersection(a));
        System.out.println("Intersection of a and d is " + a.intersection(d));
        System.out.println("Intersection of b and c is " + b.intersection(c));
        System.out.println("Intersection of b and d is " + b.intersection(d));
        System.out.println("Intersection of g and a is " + g.intersection(a));
        System.out.println("Intersection of a and g is " + a.intersection(g));
        System.out.println("Intersection of a and E is " + a.intersection(E));




        System.out.println("a is a subset of b " + a.subsetOf(b));
        System.out.println("b is a subset of c " + b.subsetOf(c));
        System.out.println("d is a subset of d " + d.subsetOf(d));
        System.out.println("e is a subset of d " + e.subsetOf(d));
        System.out.println("b is a subset of d " + b.subsetOf(d));
        System.out.println("a is a subset of d " + a.subsetOf(d));
        System.out.println("E is a subset of d " + E.subsetOf(d));
        System.out.println("d is a subset of E " + d.subsetOf(E));





        a.insert(10);
        System.out.println("Insert 10 into a:"+ a);
        a.insert(22);
        System.out.println("Insert 22 into a:"+ a);

        System.out.println("a contains 10:"+ a.contains(10));
        System.out.println("a contains 19:"+ a.contains(19));
        System.out.println("a contains 22:"+ a.contains(22));
        System.out.println("a contains 25:"+ a.contains(25));
        System.out.println("a contains 44:"+ a.contains(44));
        System.out.println("a contains 50:"+ a.contains(50));
        System.out.println("a contains 54:"+ a.contains(54));
        System.out.println("a contains 5:"+ a.contains(55));
        System.out.println("a contains 30:"+ a.contains(30));
        System.out.println("a contains 50:"+ a.contains(30));
        System.out.println("b contains 2147483647:"+ b.contains(2147483647));
        System.out.println("d contains 2147483647:"+ d.contains(2147483647));
        System.out.println("e contains 2147483647:"+ e.contains(2147483647));
        System.out.println("c contains 2147483647:"+ c.contains(2147483647));
        System.out.println("g contains -10:"+ g.contains(-10));
        System.out.println("E contains 54:"+ E.contains(54));


        a.delete(22);
        System.out.println("Delete 22 from a:"+ a);
        E.delete(22);

        System.out.println("Delete 22 from E:"+ E);
        b.delete(Integer.MAX_VALUE);
        System.out.println("Delete MAX from b:"+ b);

        System.out.println("b\\b is " + b.difference(b));

        System.out.println("a contains 22:"+ a.contains(22));
        System.out.println("c contains 22:"+ c.contains(22));
        System.out.println("c contains 23:"+ c.contains(23));
        System.out.println("c contains 55:"+ c.contains(55));
        System.out.println("c contains 5:"+ c.contains(5));
        System.out.println("c contains 50:"+ c.contains(30));
        System.out.println("c contains 19:"+ c.contains(19));
        System.out.println("a\\b is " + a.difference(b));
        System.out.println("b\\a is " + b.difference(a));
        System.out.println("a\\g is " + a.difference(g));
        System.out.println("g\\a is " + g.difference(a));
        System.out.println("b\\b is " + b.difference(b));

        System.out.println("a\\E is " + a.difference(E));


        System.out.println("A union G is: "+a.union(g));
        System.out.println("A intersection G is: "+a.intersection(g));
        System.out.println("AUG = (A\\G)U(G and A)U(G\\A): "+ a.difference(g).union(g.intersection(a)).union(g.difference(a)));

        c.insert(20);
        System.out.println("Insert 20 into c:"+ c);
        c.delete(20);
        System.out.println("Delete 20 from c:"+ c);
        c.delete(25);
        System.out.println("Delete 25 from c:"+ c);
        c.insert(25);
        System.out.println("Insert 25 into c:"+ c);
        E.insert(25);
        System.out.println("Insert 25 into E:"+ E);
    }
}


