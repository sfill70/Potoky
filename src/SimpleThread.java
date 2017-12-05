/**
 * Created by Sfill on 17.01.2017.
 * В очень простых случаях можно использовать альтернативное решение с прямым наследованием от Thread:
 *
 */
public class SimpleThread extends Thread {
    private int countDown = 5;
    private static int threadCount = 0;
    public SimpleThread() {
        // Store the thread name:

        super("Поток-" +Integer.toString(++threadCount));//Чтобы задать объектам Thread имена, вы вызываете соответствующий конструктор Thread

        start();
    }
    public String toString() {
        return "#" + getName() + "(" + countDown + "), "; } //Имя читается в методе toString() при помощи getName
    public void run() {
        while(true) {
            System.out.print(this);
            if(--countDown == 0)
                return;
        }
    }
    public static void main(String[] args) {
        for(int i = 0; i < 5; i++)
            new SimpleThread();
    }
}
