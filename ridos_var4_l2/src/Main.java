public class Main {
    public static void main(String[] args) {
        int N = 4;
        int M = 4;

        for (int i = 0; i < N; i++) {
            Thread thread = new Thread(new SomeThread(M));
            thread.start();
        }
    }
}