package com.project;

import com.project.controller.StartViewController;
import com.project.view.StartView;

import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

public class Main {
    public static void main(String[] args) {
        try {
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
            TerminalScreen screen = terminalFactory.createScreen();
            screen.startScreen();

            MultiWindowTextGUI textGUI = new MultiWindowTextGUI(screen);
            StartView startView = new StartView(textGUI);
            StartViewController startViewController = new StartViewController(startView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

