package com.project.controller;

import com.project.view.EditTaskView;
import com.project.view.TaskListView;
import com.project.view.TaskDetailView;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.input.KeyType;

public class TaskDetailController {
    private TaskDetailView taskDetailView;
    private int selectedItemIndex = 0;

    public TaskDetailController(TaskDetailView taskDetailView) {
        this.taskDetailView = taskDetailView;
        initializeMenu();
    }

    public void initializeMenu() {
        Panel panel = taskDetailView.getPanel();

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

        Panel panel = taskDetailView.getPanel();
        int maxIndex = panel.getComponentCount() - 1;

        if (selectedItemIndex < 0) {
            selectedItemIndex = 0;
        } else if (selectedItemIndex > maxIndex) {
            selectedItemIndex = maxIndex;
        }

        panel.setFocusedInteractable((Interactable) panel.getComponent(selectedItemIndex));
    }

    private void handleEnter() {
        Panel panel = taskDetailView.getPanel();
        Component selectedComponent = panel.getComponent(selectedItemIndex);

        if (selectedComponent instanceof Button) {
            Button selectedButton = (Button) selectedComponent;
            String buttonText = selectedButton.getLabel();

            switch (buttonText) {
                case "Edit task":
                    // Przejście do widoku edycji zadania (EditTaskView.java)
                    EditTaskView editTaskView = new EditTaskView();
                    break;
                case "Exit":
                    // Powrót do poprzedniego widoku (TaskListView.java)
                    TaskListView taskListView = new TaskListView();
                    break;
                default:
                    break;
            }
        }
    }
    public static void goToEditTask() {
        // Tutaj umieść logikę przechodzenia do widoku edycji zadania (EditTaskView.java)
        EditTaskView editTaskView = new EditTaskView();
        // Pamiętaj, że jeśli to potrzebne, możesz przekazać informacje o zadaniu do widoku edycji
        editTaskView.displayEditTaskForm();
    }

    public static void goBackToTaskList() {
        // Tutaj umieść logikę powrotu do poprzedniego widoku (TaskListView.java)
        TaskListView taskListView = new TaskListView();
        taskListView.displayTasks();
    }
}



