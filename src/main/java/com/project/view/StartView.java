package com.project.view;

import com.googlecode.lanterna.gui2.*;

public class StartView {
    private Panel panel;
    private WindowBasedTextGUI textGUI;

    public StartView(WindowBasedTextGUI textGUI) {
        this.textGUI = textGUI;
        createMenu();
    }

    public void createMenu() {
        panel = new Panel();
        panel.setLayoutManager(new LinearLayout(Direction.VERTICAL));

        panel.addComponent(new Label("\u25B6 Show your tasks"));
        panel.addComponent(new Label("\u25B6 Add new Task"));
        panel.addComponent(new Label("\u25B6 Tasks History"));
        panel.addComponent(new Label("\u25B6 Exit"));

        BasicWindow window = new BasicWindow("Menu");
        window.setComponent(panel);

        textGUI.addWindowAndWait(window);
    }

    public Panel getPanel() {
        return panel;
    }
}
