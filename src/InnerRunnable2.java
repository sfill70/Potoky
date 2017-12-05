import java.util.concurrent.TimeUnit;

/**
 * Created by Sfill on 18.01.2017.
 */

// Используем анонимную реализацию Runnable:

class InnerRunnable2 {
    private int countDown = 5;
    private Thread t;
    public InnerRunnable2(String name) {
        t = new Thread(new Runnable() {
            public void run() {
                try {
                    while(true) {
                        System.out.print(this);
                        if(--countDown == 0) return;
                        TimeUnit.MILLISECONDS.sleep(10);
                    }
                } catch(InterruptedException e) {
                    System.out.println("sleep() interrupted");
                }
            }
            public String toString() {
                return Thread.currentThread().getName() +
                        ": " + countDown +";   ";
            }
        }, name);
        t.start();
    }
}
