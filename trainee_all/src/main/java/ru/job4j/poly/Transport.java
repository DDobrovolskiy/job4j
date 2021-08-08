package ru.job4j.poly;

public interface Transport {
    void run();

    void boarding(int count);

    double refuel(int litres);
}
