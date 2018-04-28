package queuesystem;

/**
 *
 * @author Earl John Liva
 */
public class QueueTester {

     public static void main(String args[]) {
        
        //This test the enqueue and also the get maxlength method
        try {
            MyRequestQueue q = new MyRequestQueue();
            q.enqueue("one");
            q.enqueue("two");
            q.enqueue("three");
            q.enqueue("four");
            q.enqueue("five");
            q.enqueue("six");
            q.enqueue("seven");
            System.out.println(q.getMaxLength());
        }catch (Exception e) {
            System.out.println(e);
            }
        
        // Testing more of the getMaxLength()
        try {
            MyRequestQueue q = new MyRequestQueue();
            q.enqueue("one");
            q.enqueue("two");
            q.enqueue("three");
            q.enqueue("four");
            q.enqueue("five");
            //q.enqueue("six");
            //q.enqueue("seven");
            System.out.println(q.getMaxLength());
        } catch (Exception e) {
            System.out.println(e);
        }
        
        //This test dequeue() Exception...
        try {
            MyRequestQueue q = new MyRequestQueue();
            //q.enqueue("one");
            //q.enqueue("two");
            //q.enqueue("three");
            //q.enqueue("four");
            //q.enqueue("five");
            //q.enqueue("six");
            //q.enqueue("seven");
            q.dequeue();
            System.out.println(q.getMaxLength());
        } catch (Exception e) {
            System.out.println(e);
        }
        // This tests dequeue Method and the getMaxMethod
        try {
            MyRequestQueue q = new MyRequestQueue();
            q.enqueue("one");
            q.enqueue("two");
            q.enqueue("three");
            q.enqueue("four");
            q.enqueue("five");
            q.enqueue("seven");
            System.out.println(q.dequeue());
            q.enqueue("eight");
            System.out.println(q.dequeue());
            q.enqueue("two");
            q.enqueue("three");
            q.enqueue("four");
            q.enqueue("five");
            q.enqueue("seven");
            System.out.println(q.dequeue());
            System.out.println(q.getMaxLength());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
