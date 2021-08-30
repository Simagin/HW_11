package main.java;

public class Main {
    private static Integer count;
    private static final int TIME = 17;

    public static void main(String[] args) {

        runTask1();                                   //task 1
        System.out.println("---------------------------");
        Counter Counter = new Counter(15);//task 2
        ThreadA threadA = new ThreadA(Counter);
        ThreadB threadB = new ThreadB(Counter);
        ThreadC threadC = new ThreadC(Counter);
        ThreadD threadD = new ThreadD(Counter);
        new Thread(threadA,"ThreadA").start();
        new Thread(threadB,"ThreadB").start();
        new Thread(threadC,"ThreadC").start();
        new Thread(threadD,"ThreadD").start();

    }

    private static void runTask1() {
        count = 0;
        Thread myThread = new Thread(() -> {
            synchronized (Thread.currentThread()) {
                while(count < TIME) {
                    try {
                        if (count % 5 == 0 && count != 0) System.out.println("Прошло 5 секунд");
                        Thread.currentThread().wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        myThread.start();
        while (count < TIME) {
            try {
                Thread.sleep(1000);
                System.out.println(++count);
                synchronized (myThread) {
                    myThread.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
