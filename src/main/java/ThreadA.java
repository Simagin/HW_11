package main.java;

public class ThreadA implements Runnable{
    private final Counter myCounter;

    ThreadA(Counter myCounter){
        this.myCounter = myCounter;
    }

    @Override
    public void run() {
        try {
            add();
        } catch (InterruptedException e) {
            new RuntimeException(e);
        }
    }

    private void add() throws InterruptedException {
        synchronized (myCounter) {
            while (myCounter.getCount() <= myCounter.getTime()){
                if (myCounter.fizz()) {
                    System.out.println("fizz");
                    myCounter.add();
                }
                else myCounter.pause();
            }
        }
    }
}
