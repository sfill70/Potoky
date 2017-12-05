/**
 * Created by Sfill on 28.01.2017.
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Клубок
1. Создай 5 различных своих нитей c отличным от Thread типом:
1.1. нить 1 должна бесконечно выполняться;
1.2. нить 2 должна выводить "InterruptedException" при возникновении исключения InterruptedException;
1.3. нить 3 должна каждые полсекунды выводить "Ура";
1.4. нить 4 должна реализовать интерфейс Message, при вызове метода showWarning нить должна останавливаться;
1.5. нить 5 должна читать с консоли цифры пока не введено слово "N", а потом вывести в консоль сумму введенных цифр.
2. В статическом блоке добавь свои нити в List<Thread> threads в перечисленном порядке.
3. Нити не должны стартовать автоматически.
Подсказка: Нить 4 можно проверить методом isAlive()
*/

public class Solution {
    public static List<Thread> threads = new ArrayList<Thread>(5);

    /*static {
        threads.add(new TheardInfinity());
        threads.add(new TheardIntExcept());
        threads.add(new TheardUra());
        threads.add(new TheardMessage());
        threads.add(new TheardReadNum());

    }*/

    public static class TheardInfinity extends Thread implements Runnable {
        public TheardInfinity(){super();}
        public void run () {
            while (true){}
        }
    }


    public static class TheardIntExcept extends Thread implements Runnable {
        public TheardIntExcept() {super();}
        private static boolean intEx=false;
        public static boolean setIntEx (){
            intEx=true;
            return intEx; }
        public void run () {

            try
            {
                while (!intEx) {
                    System.out.println (Thread.currentThread() + "---- "+ this );
                    Thread.sleep(100);
                }
            }
            catch (InterruptedException e)
            {
                System.out.println("InterruptedException");
                //e.printStackTrace();
            }

        }
    }



   public static class TheardUra extends Thread  {

        public TheardUra(){super();}
        public void run (){

            try
            {
                while (true){
                    System.out.println("Ура");
                    Thread.sleep(500);
                }
            }
            catch (InterruptedException e)
            {
                //e.printStackTrace();
            }
        }
    }
    public static class TheardMessage extends Thread implements Message    {
        public TheardMessage(){
            super();
        }


         public     boolean isSt=false;
         private static Thread current = Thread.currentThread();

        public   void showWarning(){
            this.interrupt();
             try{
                //Thread.currentThread().join();
                Thread.sleep(10);
            } catch (Exception e){
                // Thread.currentThread().interrupt();
            }

            // System.out.println("svdsvd");
            System.out.println(this.isAlive());
            System.out.println(this.getClass());
            // System.out.println("drntynjtynj");
                isSt=true;

        }

        public void run(){


            while (! isSt && !interrupted()  ){
                System.out.println("RunMessage"+"--"+getName());

            }
        }
    }




    private static class TheardReadNum extends Thread implements Runnable
    {
        public TheardReadNum(){super();}
        public static volatile BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        private String st;
        private int sum;

        public void run()
        {


            while (true)
            {
                try
                {
                    st = reader.readLine();
                    if (st.contains("N"))
                    {
                        System.out.println(sum);
                        return;
                    } else
                    {
                        sum = sum + Integer.parseInt(st);
                    }

                }
                catch (IOException e)
                {
                    // System.out.println(sum);
                    //  e.printStackTrace();
                }


            }
        }
    }

    /*public static void main(String[] args) throws InterruptedException
    {



        Thread t =threads.get(3);
      //  Thread s =threads.get(3);
    // TheardMessage *//*Thread*//* s = new TheardMessage();
        TheardMessage *//*Thread*//* d = new TheardMessage();
       // System.out.println(t.equals(s));
        Message message = (Message) t;
        System.out.println(t.isAlive());
       t.start();
      //  t.sleep (100);
      //  t.join();
    //   s.start();
        Thread.sleep (1);
       // t.join();
        d.start();
       // d.sleep(100);


      //  d.join(s);

      // TheardMessage.current.interrupt();
      //  System.out.println(t.isAlive()+"--"+s.isAlive());
       Thread.sleep(10);
      //  TheardMessage.isSt=true;
      //  s.isSt=true;

    //   d.isSt=true;
       // d.join();

         message.showWarning();
        d.showWarning();
      //  s.showWarning();
       // System.out.println(s.getClass().getSuperclass());
       // System.out.println(s instanceof Thread);

       // System.out.println(TheardMessage.current.isInterrupted()+"---" + s.isSt);


        System.out.println(t.isAlive());


   //     System.out.println(s.isAlive());




    }*/

    /*public static void main(String[] args) {
        System.out.println(3+5);
    }*/
}
