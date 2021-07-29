package ru.job4j.poohjms;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TopicService implements Service {
    private final
    Map<String, Map<Integer, ConcurrentLinkedQueue<String>>> queue = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        queue.putIfAbsent(req.theme(), new ConcurrentHashMap<>());
        if (req.method().equals("POST")) {
            var m = queue.get(req.theme());
            for (Map.Entry<Integer, ConcurrentLinkedQueue<String>> map: m.entrySet()) {
                map.getValue().add(req.text());
            }
            return new Resp("OK", 201);
        } else {
            var q = queue.getOrDefault(req.theme(), new ConcurrentHashMap<>());
            q.putIfAbsent(req.id(), new ConcurrentLinkedQueue<>());
            String answer = q.get(req.id()).poll();
            if (answer == null) {
                return new Resp("Not found answer", 404);
            } else {
                return new Resp(answer, 200);
            }
        }
    }
}