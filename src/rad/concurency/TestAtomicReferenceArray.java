package rad.concurency;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Stream;

import javax.activity.InvalidActivityException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TestAtomicReferenceArray {

	
	  private static final long ONE_SECOND = Duration.ofSeconds(5).toMillis();
	    private static final long ONE_MINUTE = Duration.ofMinutes(5).toMillis();
	    
	private ConcurrentBoundedBufferArray<Razak> b;

	


	@Test
	public void testAtomicArray() throws InvalidActivityException {
		
		b = new ConcurrentBoundedBufferArray<Razak>(60);
		 Supplier<Long> time=System::currentTimeMillis;
		ExecutorService es = Executors.newFixedThreadPool(3);
		

	  for (int i = 0; i < 60; i++) {

	      es.submit(new Runnable() {

	        @Override

	        public void run() {
	        	
	          for(int i=0;i<60;i++) {
	        	  Supplier<Long> time=System::currentTimeMillis;
	      	          try {
						b.add(new Razak(20.1,time.get()));
					
	      	 	      } catch (InvalidActivityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	
	          }     
	        }

	      });
	     
	    }
	   b.add(new Razak(28.1,Instant.now().toEpochMilli()));
	   b.add(new Razak(29.1,Instant.now().minusMillis(ONE_SECOND).toEpochMilli()));
	   /*b.add(new Razak(29.1,Instant.now().plusMillis(ONE_MINUTE).toEpochMilli()));*/
	   
	   es.shutdown();
	

	    try {
	      es.awaitTermination(5000, TimeUnit.SECONDS);
	
	    } catch (InterruptedException e) {

	      e.printStackTrace();

	    }
	    List<Object> li = Arrays.asList(b.getArray());
	   Stream.of(li).forEach(e->System.out.println(e.toString()));
	    Assert.assertEquals(60, li.size());
	   /* Assert.assertEquals(1, (int)li.get(0));*/
	    

	  }
				
		
	
}
