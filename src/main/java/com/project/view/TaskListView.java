package com.project.view;

import com.project.controller.TaskListController;
import com.project.model.Task;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.table.Table;
import com.googlecode.lanterna.gui2.table.TableModel;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Separator;
import com.googlecode.lanterna.gui2.Window.Hint;
import com.googlecode.lanterna.gui2.DefaultWindowManager;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TaskListView {
    public void displayTasks() {
        try {
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
            TerminalScreen screen = new TerminalScreen(terminalFactory.createScreen());
            screen.startScreen();

            Panel panel = new Panel();
            panel.setLayoutManager(new GridLayout(6)); // 6 kolumn - dla każdego priorytetu

            // Kolor dla każdej kolumny
            TextColor[] colors = {
                    TextColor.ANSI.RED, TextColor.ANSI.YELLOW, TextColor.ANSI.BLUE,
                    TextColor.ANSI.GREEN, TextColor.ANSI.MAGENTA, TextColor.ANSI.CYAN
            };

            // Przykładowe zadania dla każdego priorytetu
            List<String> tasksUrgent = Arrays.asList("Task 1", "Task 2", "Task 3");
            List<String> tasksImportant = Arrays.asList("Task 4", "Task 5");
            List<String> tasksRoutine = Collections.emptyList(); // Brak zadań w tej kategorii w przykładzie

            // Utwórz kolumny dla każdego priorytetu
            Table<String> table = new Table<>("Urgent", "Important", "Routine", "Long-term", "Flexible", "Neutral");

            // Wypełnij kolumny zadaniami
            table.getTableModel().addRow(tasksUrgent);
            table.getTableModel().addRow(tasksImportant);
            table.getTableModel().addRow(tasksRoutine);

            // Ustaw kolory dla kolumn
            for (int i = 0; i < table.getColumnCount(); i++) {
                table.setColumnColor(i, colors[i]);
            }

            panel.addComponent(table);

            BasicWindow window = new BasicWindow("Task List");
            window.setComponent(panel);

            // Dodajemy podwójne linie oddzielające kolumny
            panel.addComponent(new Separator(Direction.HORIZONTAL).setLine(Character.valueOf('=')));
            panel.addComponent(new Separator(Direction.HORIZONTAL).setLine(Character.valueOf('=')));

            // Menu na dole okna
            Panel menuPanel = new Panel();
            menuPanel.setLayoutManager(new LinearLayout(Direction.HORIZONTAL));

            menuPanel.addComponent(new Button("Edit task"));
            menuPanel.addComponent(new Button("Delete task"));
            menuPanel.addComponent(new Button("Exit"));

            panel.addComponent(menuPanel);

            MultiWindowTextGUI textGUI = new MultiWindowTextGUI(screen);
            textGUI.addWindowAndWait(window);

            screen.stopScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
