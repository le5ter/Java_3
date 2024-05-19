package  Comparison;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Comparison {

    /**
     * Запускает тестирование производительности для указанного количества операций
     *
     * @param num Указывает количество операций
     */
    public static void start(int num) {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        Thread arrayListThread = new Thread(() -> {
            synchronized (System.out) {
                Long addTime = time(() -> testAdd(arrayList, num));
                Long getTime = time(() -> testGet(arrayList, num));
                Long deleteTime = time(() -> testDelete(arrayList, num));
                System.out.println("Array\tIterations \tTime(ms)");
                System.out.printf("%-10s%-15s%-10s%n", "add:", num, addTime);
                System.out.printf("%-10s%-15s%-10s%n", "get:", num, getTime);
                System.out.printf("%-10s%-15s%-10s%n", "delete:", num, deleteTime);
            }
        });

        Thread linkedListThread = new Thread(() -> {
            synchronized (System.out) {
                Long addTime = time(() -> testAdd(linkedList, num));
                Long getTime = time(() -> testGet(linkedList, num));
                Long deleteTime = time(() -> testDelete(linkedList, num));
                System.out.println("Linked\tIterations \tTime(ms)");
                System.out.printf("%-10s%-15s%-10s%n", "add:", num, addTime);
                System.out.printf("%-10s%-15s%-10s%n", "get:", num, getTime);
                System.out.printf("%-10s%-15s%-10s%n", "delete:", num, deleteTime + "\n");
            }
        });

        arrayListThread.start();
        linkedListThread.start();
        try {
            arrayListThread.join();
            linkedListThread.join();
        } catch (InterruptedException x) {
            x.printStackTrace();
        }
    }

    /**
     * Измеряет время, затраченное на выполнение указанной задачи.
     *
     * @param task задача, которую нужно измерить.
     * @return продолжительность задачи в миллисекундах.
     */
    public static Long time(Runnable task) {
        long start = System.nanoTime();
        task.run();
        long end = System.nanoTime();
        return (end - start) / 1_000_000;
    }

    /**
     * Выполняет операции добавления за указанное количество итераций.
     *
     * @param list  список для выполнения операций
     * @param num   чило операций
     */
    public static void testAdd(List<Integer> list, int num) {
        for (int i = 0; i < num; i++)
            list.add(i);
    }

    /**
     * Выполняет операции получения за указанное количество итераций
     *
     * @param list список для выполнения операций
     * @param num число операций
     */
    public static void testGet(List<Integer> list, int num) {
        for (int i = 0; i < num; i++)
            list.get(i);
    }

    /**
     * Выполняет операции удаления за указанное количество итераций
     *
     * @param list список для выполнения операций
     * @param num чило операций
     */
    public static void testDelete(List<Integer> list, int num) {
        for (int i = num - 1; i >= 0; i--)
            list.remove(i);
    }
}