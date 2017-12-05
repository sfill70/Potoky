/**
 * Created by Sfill on 31.01.2017.
 */

/* Отдебажим все на свете
Разобраться, что делает програма.
Почитать про UncaughtExceptionHandler - это важно.
Еще раз внимательно посмотреть программу.
Разобраться - продебажить - почему наш OurUncaughtExceptionHandler не срабатывает.
Исправить ошибку, т.е. все должно работать. :)

Ожидаемый результат в произвольном порядке:
Нить 1: My exception message
Нить 2: My exception message
*/

public class SolutExpes {
    public static Thread.UncaughtExceptionHandler handler = new OurUncaughtExceptionHandler();

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(handler);
        TestedThread commonThread = new TestedThread(handler);

        Thread threadA = new Thread(commonThread, "Нить 1");
        Thread threadB = new Thread(commonThread, "Нить 2");

        threadA.start();
        threadB.start();

        threadA.interrupt();
        threadB.interrupt();
    }

    public static class TestedThread extends Thread {
        public TestedThread(UncaughtExceptionHandler handler) {
            setUncaughtExceptionHandler(handler);
           start();
        }
        @Override
        public void run() {

            try {

                Thread.sleep(3000);
            } catch (InterruptedException x) {
                throw new RuntimeException ("My exception message");

            }
            //throw new RuntimeException ("My exception message");
        }
    }

    public static class OurUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

        Thread.UncaughtExceptionHandler oldHandler;
        public OurUncaughtExceptionHandler() {oldHandler = Thread.getDefaultUncaughtExceptionHandler();} // сохраним ранее установленный обработчик

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println(t.getName() + ": " + e.getMessage());
            // oldHandler.uncaughtException(t,e); // Вызов обработчика исключенй по умолчанию
            if(oldHandler != null) {oldHandler.uncaughtException(t,e);}// или так, но не понятно если есть ранее установленный... (!= или ==) не понятно
            // ...вызовем его
        }
    }
}
