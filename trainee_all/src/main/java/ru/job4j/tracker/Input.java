package ru.job4j.tracker;

public interface Input {
    /**
     * В интерфейсе объявлен метод, который возвращает введенную строку от пользователя
     * @param question запрос
     * @return
     */
    String askStr(String question);

    /**
     *  Возвращает от пользователя число
     * @param question запрос
     * @return
     */
    int askInt(String question);
}
