package ru.job4j.oop;

public class Jukebox {
    public void music(int position) {
        String  song = "Песня не найдена";
        if (position == 1) {
            song = "Пусть бегут неуклюже";
        }
        if (position == 2) {
            song = "Спокойной ночи";
        }
        System.out.println(song);
    }

    public static void main(String[] args) {
        Jukebox jukebox = new Jukebox();
        jukebox.music(1);
        jukebox.music(2);
        jukebox.music(3);
    }
}
