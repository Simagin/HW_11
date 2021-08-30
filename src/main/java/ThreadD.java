package main.java;

public class ThreadD implements Runnable{
    private final Counter myCounter;

    ThreadD (Counter myCounter){
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
                if (!myCounter.fizz()&&!myCounter.buzz()&&!myCounter.fizzbuzz()) {
                    myCounter.number();
                    myCounter.add();
                }
                else myCounter.pause();
            }
        }
    }
}
