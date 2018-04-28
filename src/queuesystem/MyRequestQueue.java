package queuesystem;

/**
 * @author Earl John Liva
 */
public class MyRequestQueue implements RequestQueue {
     public class Node{
        public String input;
        public Node next;
        
        public Node(String input){
            this.input = input;
            this.next = null;
        } // end of constructor
    } // end of Node class
    
    int count; // count variable to keep track of the maximum number of the queue.
    Node front; // member variable
    Node back; // member variable
    
    /**
     * This method adds the string value to the back of the queue
     * @param input a string value
     */
    @Override
    public void enqueue(String input)
    {
        if(front == null){
          front = new Node(input);
          back = front;
        }else{
            while(front.next != null)
                back = front.next;
        }
        count++;
    }
    /**
     * 
     * @return the string value from the front of the list.
     * @throws Exception if the queue is empty
     */

    @Override
    public String dequeue() throws Exception {
        
        if(front == null){
            throw new Exception("The list is empty.");
        }else{
        String in = front.input;
        front = front.next;
        count--;
        if(count == 0)
            back = null;
        return in;
        }
    }

    /**
     * This method returns the maximum length of the list.
     * @return the max number of items 
     */
    @Override
    public int getMaxLength() {
        return count;
    } // end of getMaxLength
}
