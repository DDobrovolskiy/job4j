package ru.job4j.pool;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class EmailNotification {
    private ExecutorService service =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    //Public
    public void emailTo(User user) {
        service.submit(getRunnableSendEmailTo(user));
    }

    public  void close() {
        service.shutdown();
        while (!service.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //Private
    private void send(String subject, String body, String email) {
        System.out.printf("%s/%s/%s%n", email, subject, body);
    }

    private String getSubject(User user) {
        return String.format("Notification %s to email %s.",
                user.getUsername(),
                user.getEmail());
    }

    private String getBody(User user) {
        return String.format("Add a new event to %s",
                user.getUsername());
    }

    private Runnable getRunnableSendEmailTo(User user) {
        return () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                send(getSubject(user), getBody(user), user.getEmail());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };
    }

    public static void main(String[] args) throws InterruptedException {
        List<User> users = new LinkedList<>();
        users.add(new User("Dima", "dima@com.ru"));
        users.add(new User("Stas", "stas@com.ru"));
        users.add(new User("Petr", "petr@com.ru"));

        var emailSender = new EmailNotification();

        users.forEach(emailSender::emailTo);

        emailSender.close();
        //TimeUnit.SECONDS.sleep(2);
        System.out.println("Email send");
    }
}
