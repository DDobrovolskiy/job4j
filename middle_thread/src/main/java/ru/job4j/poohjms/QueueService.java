package ru.job4j.poohjms;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueService implements Service {
    private final Map<String, ConcurrentLinkedQueue<String>> queue = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        if (req.method().equals("POST")) {
            queue.putIfAbsent(req.theme(), new ConcurrentLinkedQueue<>());
            queue.get(req.theme()).add(req.text());
            return new Resp("OK", 201);
        } else {
            var q = queue.get(req.theme());
            if (q != null) {
                String answer = q.poll();
                if (answer == null) {
                    return new Resp("Not found answer", 404);
                } else {
                    return new Resp(answer, 200);
                }
            } else {
                return new Resp("Not found theme", 404);
            }

        }
    }
}