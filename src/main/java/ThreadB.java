package main.java;

public class ThreadB implements Runnable{
    private final Counter myCounter;

    ThreadB (Counter myCounter){
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
            while (myCounter.getCount() <= myCounter.getTime()) {
                if (myCounter.buzz()) {
                    System.out.println("buzz");
                    myCounter.add();
                }
                else myCounter.pause();
            }
        }
    }
}
