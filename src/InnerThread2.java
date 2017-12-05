/**
 * Created by Sfill on 18.01.2017.
 */

// Используем безымянный внутренний класс::
class InnerThread2 {
    private int countDown = 5;
    private Thread t;

    public InnerThread2(String name) {
        t = new Thread(name) {
            public void run() {
                try {
                    while (true) {
                        System.out.print(this);
                        if (--countDown == 0) return;
                        sleep(10);
                    }
                } catch (InterruptedException e) {
                    System.out.println("sleep() interrupted");
                }
            }

           public String toString() {
                return getName() + ": " + countDown + ";   ";
            }
        };
        t.start();
        System.out.println(t instanceof Thread);
    }

    public static void main(String[] args) {
        for(int i = 0; i < 5; i++)
             new InnerThread2(""+i);
        //System.out.println(in2.getClass().getSuperclass());

    }
}
