package com.project.controller;

import com.project.view.StartView;

import com.googlecode.lanterna.gui2.*;


public class StartViewController {
    private StartView startView;
    private int selectedItemIndex = 0;

    public StartViewController(StartView startView) {
        this.startView = startView;
        initializeMenu();
    }

    public void initializeMenu() {
        Panel panel = startView.getPanel();

        panel.setFocusedInteractable((Interactable) panel.getComponent(selectedItemIndex)); // Ustawienie fokusu na pierwszym elemencie

        panel.getKeyboard().addKeyListener(new KeyAdapter() {
            @Override
            public void onKeyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyType() == KeyType.ArrowDown) {
                    moveSelection(1); // Porusza wyborem w dół po naciśnięciu strzałki w dół
                } else if (keyEvent.getKeyType() == KeyType.ArrowUp) {
                    moveSelection(-1); // Porusza wyborem w górę po naciśnięciu strzałki w górę
                } else if (keyEvent.getKeyType() == KeyType.Enter) {
                    handleEnter(); // Obsługa wyboru po naciśnięciu Enter
                }
            }
        });
    }

    private void moveSelection(int delta) {
        selectedItemIndex += delta;

        Panel panel = startView.getPanel();
        int maxIndex = panel.getComponentCount() - 1;

        if (selectedItemIndex < 0) {
            selectedItemIndex = 0;
        } else if (selectedItemIndex > maxIndex) {
            selectedItemIndex = maxIndex;
        }

        panel.setFocusedInteractable((Interactable) panel.getComponent(selectedItemIndex));
    }

    private void handleEnter() {
        Panel panel = startView.getPanel();
        Component selectedComponent = panel.getComponent(selectedItemIndex);
        
        if (selectedComponent instanceof Label) {
            Label selectedLabel = (Label) selectedComponent;
            String selectedOption = selectedLabel.getText();
    
            switch (selectedOption) {
                case "\u25B6 Show your tasks":
                    // Przekierowanie do widoku TaskListView
                    TaskListView taskListView = new TaskListView();
                    taskListView.displayTasks();
                    break;
                case "\u25B6 Add new Task":
                    // Przekierowanie do widoku AddTaskView
                    AddTaskView addTaskView = new AddTaskView();
                    addTaskView.displayAddTaskForm();
                    break;
                case "\u25B6 Tasks History":
                    // Przekierowanie do widoku HistoryView
                    HistoryView historyView = new HistoryView();
                    historyView.displayHistory();
                    break;
                case "\u25B6 Exit":
                    // Zamknięcie aplikacji
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wybrano: " + selectedLabel.getText());
                    break;
            }
        }
    }
    
    
}
        

