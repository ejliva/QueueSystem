package queuesystem;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Earl John Liva
 */
public class RequestGenerator implements Runnable{
    private RequestQueue output;
    private int numRequests;
    
    public RequestGenerator(RequestQueue output, int numRequests){
        this.output = output;
        this.numRequests = numRequests;
    }
/*
 * Create a constructor that accepts a RequestQueue object
 * and a number of requests as parameters
 */

    @Override
    public void run() 
    {
        
        Delay delayObj = new Delay(39726397); //Delay object with a large integer seed       
        String data;
        String prefix = "";
        int n = 0;
        for(int i = 0; i < numRequests; i++)
        {
            prefix += "\t";
        }
        prefix += numRequests;
        while(n < numRequests)
        {
            try
            {
                
                data = output.dequeue(); 
                
                delayObj.randomDelay(200, 300); // random delay
                
                output.enqueue(prefix + data);
                
            }
            catch(Exception e)
            {
                ++n;
            }
            System.out.printf("Request # %d%n", numRequests);
        } //end of loop
    } // end of method}
}