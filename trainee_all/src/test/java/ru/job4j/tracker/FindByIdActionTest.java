package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindByIdActionTest {

    @Test
    public void whenFindByIdTrue() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = new Item("FindById item");
        tracker.add(item);
        UserAction rep = new FindByIdAction(out);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(1);

        rep.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Find item by Id ====" + ln + item + ln));
    }

    @Test
    public void whenFindByIdFalse() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = new Item("FindById item");
        tracker.add(item);
        UserAction rep = new FindByIdAction(out);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(0);

        rep.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "=== Find item by Id ====" + ln
                + "Заявка с таким id не найдена" + ln));
    }
}