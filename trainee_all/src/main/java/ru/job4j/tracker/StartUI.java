package ru.job4j.tracker;

import java.util.LinkedList;
import java.util.List;

public class StartUI {
    private final Output out;

    public StartUI(Output out) {
        this.out = out;
    }

    public void init(Input input, Store tracker, List<UserAction> actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Select: ");
            if (select < 0 || select >= actions.size()) {
                out.println("Wrong input, you can select: 0 .. " + (actions.size() - 1));
                continue;
            }
            UserAction action = actions.get(select);
            run = action.execute(input, tracker);
        }
    }

    private void showMenu(List<UserAction> actions) {
        out.println("Menu.");
        for (int index = 0; index < actions.size(); index++) {
            out.println(index + ". " + actions.get(index).name());
        }
    }

    public static void main(String[] args) {
        try (Store tracker = new SqlTracker(ConnectionSQL.get())) {
            Output output = new ConsoleOutput();
            Input input = new ValidateInput(output, new ConsoleInput());
            List<UserAction> actions = new LinkedList<>();
            actions.add(new CreateAction(output));
            actions.add(new ShowAction(output));
            actions.add(new EditAction(output));
            actions.add(new DeleteAction(output));
            actions.add(new FindByIdAction(output));
            actions.add(new FindByName(output));
            actions.add(new ExitAction());
            new StartUI(output).init(input, tracker, actions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
