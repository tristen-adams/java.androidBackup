package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

	SwingUtilities.invokeLater(() -> {
	    gui window = new gui();
	    window.setVisible(true);
    });
    }
}
