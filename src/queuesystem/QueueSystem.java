package queuesystem;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 *
 * @author Earl John Liva
 */
public class QueueSystem {

 public static void processOutput(RequestQueue output)
    {
        final int MAXEXCEPTIONS = 10;
        
        Delay delayObject = new Delay(57369);  
        String data;
        int numExceptionsInARow = 0;
        
        // We will count the number of exceptions in a row and
        // when we get to MAXEXCEPTIONS, we will assume there is
        // no more work to be done and exit the loop.
        while(numExceptionsInARow < MAXEXCEPTIONS)
        {
            try
            {
                // Try to get a string from the output queue
                data = output.dequeue();
                // If we get one, we will output it and set the
                // number of exceptions in a row back to 0
                System.out.println(data);
                numExceptionsInARow = 0;
            }
            catch(Exception e)
            {
                // Nothing in the queue, so count the exception
                numExceptionsInARow++;
                // Wait for a while so when we check the queue 
                // there is a better chance there will be some 
                // work to do.
                delayObject.specificDelay(1000);
            }
        }
        System.out.println("Output processing is done");
    }
    
    public static void main(String[] args) 
    {
        MyRequestQueue rq = new MyRequestQueue();
        RequestQueue input = new RequestQueue() {
            @Override
            public void enqueue(String input) {
            rq.enqueue(input);
            
            }

            @Override
            public String dequeue() throws Exception {
                return rq.dequeue();
            }

            @Override
            public int getMaxLength() {
                return rq.getMaxLength();
            }
        };
        RequestQueue output = new RequestQueue() {
            @Override
            public void enqueue(String input) {
                rq.enqueue(input);
            }

            @Override
            public String dequeue() throws Exception {
                return rq.dequeue();
            }

            @Override
            public int getMaxLength() {
                return rq.getMaxLength();
            }
        };
        int requests, servers = 0;
        
        Scanner stdin = new Scanner(System.in);
        System.out.println("Please enter the number of request to be generated: ");
        requests = stdin.nextInt();
        System.out.println("Please enter the number of servers to process the requests: ");
        servers = stdin.nextInt();
        
/*
 * You need to create the RequestQueue objects specified above.
 * You also need to get user input to initialize the number of 
 * requests to be generated and the number of servers to process the requests
 *
 * NOTE: when the constructors have been added for the RequestServer and  
 * RequestGenerator classes, remove the //** from the beginning of the lines below.
 * Those 5 lines are commented out for now because they will generate compiler errors
 * until the class constructors have been added.       
*/        
        
        
        
        
        
        // The following code creates a pool of worker threads, one for 
        // each request server and one for the request generator
        ExecutorService executor = Executors.newFixedThreadPool(servers + 1);
        for (int i = 1; i <= servers; i++) {
            Runnable reqsrvr = new RequestServer(input, output, i);
            executor.execute(reqsrvr);
        }
        executor.execute(new RequestGenerator(input, requests));
        
        // Now that the system is up and running, we need to start processing
        // the output from the output queue. Call a function to handle that.
        processOutput(output);
        
        // Report on queue statistics - output the max lengths of the input and output queues
        System.out.printf("Queue Stats: Input - MaxLength = %d, Output - MaxLength = %d\n", input.getMaxLength(), output.getMaxLength());
        
        // Wait on all the threads to complete their work
        executor.shutdown();
        while (!executor.isTerminated()) {}
        System.out.println("Finished all threads");
    } 
    
}
