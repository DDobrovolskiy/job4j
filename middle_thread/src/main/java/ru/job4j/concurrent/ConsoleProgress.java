package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        String[] process = {"\\", "/"};
        int i = 0;
        System.out.println("Start loading...");
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.print("\r load: " + process[i]);
                Thread.sleep(500);
                if (++i >= process.length) {
                    i = 0;
                }
            } catch (InterruptedException e) {
                //Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(5000); /* симулируем выполнение параллельной задачи в течение 1 секунды. */
        progress.interrupt();
        //System.out.println(progress.getState());
    }
}
