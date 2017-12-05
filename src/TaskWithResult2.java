

/**
 * Created by Sfill on 11.07.2017.
 */

       import java.util.*;
        import java.util.List;
        import java.util.concurrent.*;

/*
Осваиваем Callable
*/
public class TaskWithResult2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<Future<String>> futures = new ArrayList<Future<String>>();
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 1000; i <= 1010; i++) {
            futures.add(executor.submit(getTask(i)));
        }

        for (int i = 2000; i <= 2010; i++) {
            futures.add(executor.submit(getTask(i)));
        }


        for(Future<String> future : futures) {
            System.out.println(future.get());
        }
        System.out.println("-/-/-/-/-/-/-/-/-");
        System.out.println("Обект - "+futures.get(2)+",  Значение String : "+futures.get(2).get());

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

/* output
500500
501501
502503
503506
504510
505515
506521
507528
508536
509545
510555
50000005000000
*/
    }

    public static Callable<String> getTask(final int i) {
        return
                new  Callable<String> (){

                    public String call(){
                        long summ=((long) i*((long) i+  1 ))/ 2;

              /* long summ=0;
               for (long j = 0; j <=i ; j++) {
                   summ+=j;
               }*/


                        return Long.toString(summ);

                    }
                };
    }
}
