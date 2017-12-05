/**
 * Created by Sfill on 18.01.2017.
 */
//: concurrency/ThreadVariations.java
// Создание потоков с использованием внутренних классов..
 import java.util.concurrent.*;

// Using a named inner class:
class InnerThread1 {
    private int countDown = 5;
    private Inner inner;
    private class Inner extends Thread {
        Inner(String name) {
            super(name);
            start();
        }
        public void run() {
            try {
                while(true) {
                    System.out.print(this);
                    if(--countDown == 0) return;
                    sleep(10);
                }
            } catch(InterruptedException e) {
                System.out.print("interrupted");
            }
        }
        public String toString() {
            return getName()+getClass().getSuperclass() + ":" + countDown + ";" + "  ";
        }
    }
    public InnerThread1(String name) {
        inner = new Inner(name);
    }

    public static void main(String[] args) {
        InnerThread1 in = new InnerThread1("15");
        System.out.println(in.getClass().getSuperclass());
        for(int i = 0; i < 5; i++)
            new InnerThread1(""+i);
       // System.out.println(this instanceof Thread);
    }
}


