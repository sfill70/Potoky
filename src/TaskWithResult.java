/**
 * Created by Sfill on 10.01.2017.
 * Интерфейс Runnable представляет отдельную задачу, которая выполняет некоторую работу, но не возвращает значения.
 * Если вы хотите, чтобы задача возвращала значение, реализуйте интерфейс Callable вместо интерфейса Runnable.
 */

import java.util.concurrent.*;
import java.util.*;

class TaskWithResult implements Callable<String> {
    private int id;
    public TaskWithResult(int id) {
        this.id = id;
    }
    public String call() {
        return "result of TaskWithResult " + id;
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList<Future<String>>();
        for(int i = 0; i < 10; i++)
        { results.add(exec.submit(new TaskWithResult(i)));}

        for(Future<String> fs : results)
            try {
                // Вызов get() блокируется до завершения;:
                System.out.println(fs.get());
            } catch(InterruptedException e) {
                System.out.println(e);
                return;
            } catch(ExecutionException e) {
                System.out.println(e);
            } finally {
                exec.shutdown();
            }
        ExecutorService exec1 = Executors.newFixedThreadPool(5);// В аргументе конструктора передается количество потоков:
    }


}


