import java.util.ArrayList;
import java.util.List;

/* Заметки
1. Класс Note будет использоваться нитями.
2. Создай public static нить NoteThread (Runnable не является нитью),
которая в методе run 1000 раз (index = 0-999) сделает следующие действия:
2.1. используя метод addNote добавит заметку с именем [getName() + "-Note" + index], например, при index=4
"Thread-0-Note4"
2.2. используя метод removeNote удалит заметку
2.3. в качестве первого параметра в removeNote передай имя нити - метод getName()
*/

/**
 * Created by Sfill on 01.02.2017.
 */
public class SolutionNote {
    public static void main(String[] args) {
       new Note();

    }

    public static class Note
    {
        private int countDown = 0;
        public static final List<String> notes = new ArrayList<String>();
        public static Thread NoteThread;

        public Note()
        {

            NoteThread = new Thread() {
                public void run() {

                    while (true)
                        /*for (int i = 0; i < 100; i++)*/
                        {
                            addNote(Thread.currentThread().getName() + "-Note"+countDown );
                            removeNote(Thread.currentThread().getName());
                            System.out.println(Thread.currentThread().getName() + "-Note"+countDown);
                            if (++countDown == 1000) return;
                        }

                }

                /*public String toString() {
                    return getName() + ": " + countDown + ";   ";
                }*/
            };
            NoteThread.start();

        }

        public static void addNote(String note) {
            notes.add(0, note);
        }

        public static void removeNote(String threadName) {
            String note = notes.get(0);
                    notes.remove(0);
            if (note == null) {
                System.out.println("Другая нить удалила нашу заметку");
            } else if (note.startsWith(threadName)) {
                System.out.println("Нить [" + threadName + "] удалила чужую заметку [" + note + "]");
            }
        }
    }
}
