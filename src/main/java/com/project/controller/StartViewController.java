package com.project.controller;
import com.project.view.StartView;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;



public class StartViewController {
    private final StartView startView;
    private int selectedItemIndex = 0;
    private final List<Component> components = new ArrayList<>();

    public StartViewController(StartView startView) {
        this.startView = startView;
        initializeMenu();
    }

    public void initializeMenu() {
        Panel panel = startView.getPanel();

        components.add(new Label("\u25B6 Show your tasks"));
        components.add(new Label("\u25B6 Add new Task"));
        components.add(new Label("\u25B6 Tasks History"));
        components.add(new Label("\u25B6 Exit"));

        panel.addComponent(components.get(selectedItemIndex));

        handleInput(panel);
    }

    private void handleInput(Panel panel) {
        // Obsługa wejścia z klawiatury
        KeyStroke keyStroke;
        try {
            while (true) {
                keyStroke = startView.getTextGUI().processKeyStroke();
                // Działania na podstawie otrzymanego klawisza
                if (keyStroke != null) {
                    if (keyStroke.getKeyType() == KeyType.Escape) {
                        // Obsługa wciśnięcia klawisza Escape
                        break; // Przerwanie pętli
                    } else {
                        // Obsługa innych klawiszy
                    }
                }
            }
        } catch (IOException e) {
            // Obsługa wyjątków wejścia/wyjścia
            e.printStackTrace();
        }
    }

    private void moveSelection(int delta, Panel panel) {
        selectedItemIndex += delta;

        int maxIndex = components.size() - 1;
        selectedItemIndex = Math.max(0, Math.min(selectedItemIndex, maxIndex));

        panel.removeAllComponents();
        panel.addComponent(components.get(selectedItemIndex));
    }

    private void handleEnter() {
        Label selectedLabel = (Label) components.get(selectedItemIndex);
        String selectedOption = selectedLabel.getText();

        switch (selectedOption) {
            case "\u25B6 Show your tasks":
                // Przekierowanie do widoku TaskListView
                break;
            case "\u25B6 Add new Task":
                // Przekierowanie do widoku AddTaskView
                break;
            case "\u25B6 Tasks History":
                // Przekierowanie do widoku HistoryView
                break;
            case "\u25B6 Exit":
                // Zamknięcie aplikacji
                MessageDialog.showMessageDialog(startView.getTextGUI().getActiveWindow(), "Info", "Exiting the application");
                System.exit(0);
                break;
            default:
                System.out.println("Wybrano: " + selectedLabel.getText());
                break;
        }
    }
}
