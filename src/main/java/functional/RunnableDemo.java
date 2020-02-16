package functional;

class Task implements Runnable{
    @Override
    public void run() {
        int count = 10;
        while(count-- >0){
            try {
                Thread.sleep(100);
                System.out.println("TASK");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();
        }
    }
}

public class RunnableDemo {
    public static void main(String[] args) {
        Thread task = new Thread(new Task());
        task.start();
        System.out.println("RUSZYL WATEK");
        Thread wait = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("KONIEC");
        });
        wait.start();
    }
}
