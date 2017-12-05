/**
 * Created by Sfill on 17.01.2017.
 */

/*Программный поток представляет некоторую задачу или операцию, поэтому нам понадобятся средства для описания этой задачи.
Их предоставляет интерфейс Runnable. Чтобы определить задачу, реализуйте Runnable и напишите метод run(),
 содержащий код выполнения нужных действий.*/

/*Исполнители (executors), появившиеся в библиотеке java.util.concurrent в Java SE5,
упрощают многозадачное программирование за счет автоматизации управления объектами Thread.*/


import java.util.concurrent.*;
import java.util.*;

public class LiftOff implements Runnable{

    protected int countDown = 10; // Значение по умолчанию
    private static int taskCount = 0;
    private final int id = taskCount++;
    public LiftOff() {super();}
    public LiftOff(int countDown) {
        this.countDown = countDown;
    }
    public String status() {
        return "#" + id + "(" +
                (countDown > 0 ? countDown : "Liftoff!") + "), ";
    }
    public void run() {
        while(countDown-- > 0) {
            System.out.print(status());
            Thread.yield();
        }
    }

    public static void main(String[] args) throws InterruptedException{
       /* LiftOff launch = new LiftOff();
        launch.run();*/
        /*Thread t = new Thread(new LiftOff(15));
        t.start();*/

        for(int i = 0; i < 5; i++){
            new Thread(new LiftOff()).start();

        }


        System.out.println("Waiting for LiftOff");
        Thread.sleep(3000);
        System.out.println("qwertyyuu");

        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0; i < 5; i++)
        {  exec.execute(new LiftOff());}
        exec.shutdown();
        Thread.sleep(3000);
        System.out.println("qwertyyuu");

        ExecutorService exec1 = Executors.newFixedThreadPool(5);// В аргументе конструктора передается количество потоков:
        for(int i = 0; i < 5; i++)
            exec1.execute(new LiftOff());
        exec1.shutdown();
        Thread.sleep(3000);
        System.out.println("qwertyyuu");

        ExecutorService exec2 =Executors.newSingleThreadExecutor();//Если SingleThreadExecutor передается более одной задачи, эти задачи ставятся в очередь
        for(int i = 0; i < 5; i++)
            exec2.execute(new LiftOff());
        exec2.shutdown();


    }

}
