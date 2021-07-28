package ru.job4j.poohjms;

import java.util.regex.Pattern;

public class Req {
    private final static Pattern STR = Pattern.compile("(.*)\\n");
    private final static Pattern MODEMETHOD = Pattern.compile("/|\\s");
    private final String method;
    private final String mode;
    private final String theme;
    private final String text;
    private final Integer id;

    private Req(String method, String mode, String theme, String text, Integer id) {
        this.method = method;
        this.mode = mode;
        this.theme = theme;
        this.text = text;
        this.id = id;
    }

    public static Req of(String content) {
        String[] strings = STR.split(content);
        String[] modes = MODEMETHOD.split(strings[0]);
        int i = 0;
        if (modes[0].equals("GET") && modes[2].equals("topic")) {
            i = Integer.parseInt(modes[4]);
        }
        return new Req(modes[0], modes[2], modes[3], strings[strings.length - 1], i);
    }

    public Integer id() {
        return id;
    }

    public String mode() {
        return mode;
    }

    public String method() {
        return method;
    }

    public String theme() {
        return theme;
    }

    public String text() {
        return text;
    }
}