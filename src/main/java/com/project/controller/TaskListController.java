package com.project.controller;

import com.project.view.TaskListView;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;

public class TaskListController {
    private TaskListView taskListView;
    private int selectedRow = 0;
    private int selectedColumn = 0;

    public TaskListController(TaskListView taskListView) {
        this.taskListView = taskListView;
    }

    public void handleInput(KeyStroke keyStroke, WindowBasedTextGUI textGUI) {
        if (keyStroke.getKeyType() == KeyType.ArrowUp) {
            moveSelection(-1, 0);
        } else if (keyStroke.getKeyType() == KeyType.ArrowDown) {
            moveSelection(1, 0);
        } else if (keyStroke.getKeyType() == KeyType.ArrowLeft) {
            moveSelection(0, -1);
        } else if (keyStroke.getKeyType() == KeyType.ArrowRight) {
            moveSelection(0, 1);
        } else if (keyStroke.getKeyType() == KeyType.Enter) {
            Component selectedComponent = taskListView.getPanel().getComponent(selectedRow * 7 + selectedColumn);
            if (selectedComponent instanceof Button) {
                String buttonText = ((Button) selectedComponent).getLabel();
                handleButtonAction(buttonText);
            }
        }
    }

    private void moveSelection(int rowDelta, int columnDelta) {
        int newRow = selectedRow + rowDelta;
        int newColumn = selectedColumn + columnDelta;

        if (newRow >= 0 && newRow < 3 && newColumn >= 0 && newColumn < 6) {
            selectedRow = newRow;
            selectedColumn = newColumn;

            Panel panel = taskListView.getPanel();
            panel.setFocusedInteractable((Interactable) panel.getComponent(selectedRow * 7 + selectedColumn));
        }
    }

    private void handleButtonAction(String buttonText) {
        switch (buttonText) {
            case "Edit task":
                // Przeniesienie do widoku edycji zadania (EditTaskView.java)
                // ...
                break;
            case "Delete task":
                confirmDeleteTask();
                break;
            case "Exit":
                TaskListController taskListController = new TaskListController();
                taskListController.handleExitButton(); // Wywołanie metody obsługującej 'Exit' z kontrolera
                break;
            default:
                // Przeniesienie do widoku szczegółów zadania (TaskDetailView.java)
                // ...
                break;
        }
    }

    private void confirmDeleteTask() {
        DialogWindow dialog = new DialogWindow("Delete Task", "Are you sure you want to delete this task?");
        dialog.setHints(com.googlecode.lanterna.gui2.Window.Hint.CENTERED);

        Panel panel = new Panel();
        panel.setLayoutManager(new LinearLayout(Direction.HORIZONTAL));

        Button confirmButton = new Button("Confirm");
        confirmButton.addListener((button) -> {
            // Usunięcie zadania
            // ...
            textGUI.removeWindow(dialog);
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.addListener((button) -> textGUI.removeWindow(dialog));

        panel.addComponent(confirmButton);
        panel.addComponent(cancelButton);

        dialog.setComponent(panel);
        textGUI.addWindow(dialog);
    }
}

