/**
 * Вывод карусели потоков.
 * Пример:
 *      Thread-0 Number: 1
 *      Thread-1 Number: 1
 *      Thread-2 Number: 1
 *      Thread-3 Number: 1
 *      Thread-0 Number: 2
 *      Thread-1 Number: 2
 */
public class SomeThread implements Runnable {
    private final static Object lock = new Object();
    private static int numOfThreads;
    private static int currentThreadId;
    private final int threadId;
    private int M;

    public SomeThread(int M) {
        threadId = numOfThreads++;
        this.M = M;
    }

    @Override
    public void run() {
        for (int i = 1; i <= M; i++) {
            synchronized (lock) {
                while (currentThreadId != threadId) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        System.out.print(e.getMessage());
                    }
                }
                System.out.println(Thread.currentThread().getName() + " Number: " + i);
                currentThreadId = threadId == numOfThreads - 1 ? 0 : threadId + 1;
                lock.notifyAll();
            }
        }
    }
}
