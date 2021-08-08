package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindByNameTest {

    @Test
    public void whenFindByINameTrue() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        String name = "FindByName item";
        Item item = new Item(name);
        tracker.add(item);
        UserAction rep = new FindByName(out);

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn(name);

        rep.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Find items by name ====" + ln + item + ln));
    }

    @Test
    public void whenFindByNameFalse() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        String name = "FindByName item";
        Item item = new Item(name);
        tracker.add(item);
        UserAction rep = new FindByName(out);

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn("Another name");

        rep.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "=== Find items by name ====" + ln
                        + "Заявки с таким именем не найдены" + ln));
    }
}