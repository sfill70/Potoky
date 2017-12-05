/**
 * Created by Sfill on 17.01.2017.
 *
 * Потоки-демоны
 */
import java.util.concurrent.*;
//import static net.mindview.util.Print.*;

public class SimpleDaemons implements Runnable {
    public SimpleDaemons () { super () ; }
    /*int priority;
    public SimpleDaemons(int priority) {
        this.priority = priority;
    }*/
    public void run() {
        try {
            while(true) {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println (Thread.currentThread() + " "+ this );
            }
        } catch(InterruptedException e) {
            System.out.println("sleep() interrupted");
        }
    }
    public static void main(String[] args) throws Exception {
        for(int i = 0; i < 10; i++) {
            Thread daemon = new Thread(new SimpleDaemons());
            daemon.setDaemon(true); // Необходимо вызвать перед start()
            daemon.start();
        }
        System.out.println("All daemons started");
        TimeUnit.MILLISECONDS.sleep(150);
    }
}