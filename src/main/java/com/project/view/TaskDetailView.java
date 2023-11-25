package com.project.view;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Separator;
import com.googlecode.lanterna.gui2.Window.Hint;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.gui2.DefaultWindowManager;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;

public class TaskDetailView {
    public void displayTaskDetails(String title, String description, String priority, String dueDate, String status) {
        try {
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
            TerminalScreen screen = new TerminalScreen(terminalFactory.createScreen());
            screen.startScreen();

            Panel panel = new Panel();
            panel.setLayoutManager(new LinearLayout(Direction.VERTICAL));

            panel.addComponent(new Label("Title: " + title));
            panel.addComponent(new Label("Description: " + description));
            panel.addComponent(new Label("Priority: " + priority));
            panel.addComponent(new Label("Due Date: " + dueDate));
            panel.addComponent(new Label("Status: " + status));

            BasicWindow window = new BasicWindow("Task Details");
            window.setComponent(panel);

            // Dodajemy podwójne linie oddzielające część górną od menu
            panel.addComponent(new Separator(Direction.HORIZONTAL).setLine(Character.valueOf('=')));
            panel.addComponent(new Separator(Direction.HORIZONTAL).setLine(Character.valueOf('=')));

            Panel menuPanel = new Panel();
            menuPanel.setLayoutManager(new LinearLayout(Direction.HORIZONTAL));

            Button editButton = new Button("Edit task");
            editButton.addListener((button) -> {
                // Przejście do widoku edycji zadania (EditTaskView.java)
                // ...
            });

            Button exitButton = new Button("Exit");
            exitButton.addListener((button) -> {
                // Powrót do poprzedniego widoku
                // ...
            });

            menuPanel.addComponent(editButton);
            menuPanel.addComponent(exitButton);

            panel.addComponent(menuPanel);

            MultiWindowTextGUI textGUI = new MultiWindowTextGUI(screen);
            textGUI.addWindowAndWait(window);

            screen.stopScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
