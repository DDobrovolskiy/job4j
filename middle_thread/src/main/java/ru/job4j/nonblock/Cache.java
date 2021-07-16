package ru.job4j.nonblock;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        BiFunction<Integer, Base, Base> function = (integer, base) -> {
            if (model.getVersion() != base.getVersion()) {
                throw new OptimisticException("Versions are not equal");
            }
            Base updateModel = new Base(model.getId(), model.getVersion() + 1);
            updateModel.setName(model.getName());
            return updateModel;
        };
        return Objects.equals(memory.computeIfPresent(model.getId(), function), model);
    }

    public Boolean delete(Base model) {
        return Objects.equals(memory.remove(model.getId()), model);
    }

    public Base get(Integer id) {
        Base result = memory.get(id);
        return result != null ? memory.get(id).clone() : null;
    }
}
