package com.company;

import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;

import java.time.LocalDate;

public class gui extends JFrame {
    private JPanel root;
    private JButton destButton;
    private JTextField sourceBox;
    private JTextField destBox;
    private JButton sourceButton;
    private JButton createBackupButton;
    private JLabel doneLabel;


    public gui() {
        // Setup
        add(root);
        setTitle("Android Full System Backup Tool");
        setSize(500, 300);
        setDefaultCloseOperation(gui.DISPOSE_ON_CLOSE);
        doneLabel.setVisible(false);

        // Get source directory
        sourceButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new java.io.File("."));
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileChooser.setAcceptAllFileFilterUsed(false);

                if (fileChooser.showOpenDialog(root) == JFileChooser.APPROVE_OPTION) {
                    sourceBox.setText((fileChooser.getSelectedFile().getPath()));
                }
            }
        });

        // Get target directory
        destButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new java.io.File("."));
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileChooser.setAcceptAllFileFilterUsed(false);

                if (fileChooser.showOpenDialog(root) == JFileChooser.APPROVE_OPTION) {
                    destBox.setText((fileChooser.getSelectedFile().getPath() + "\\" + LocalDate.now()));
                }

            }
        });

        // Copy source to target
        createBackupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update UI
                doneLabel.setVisible(true);
                doneLabel.setText("Working...");
                createBackupButton.setEnabled(false);
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

                // Copying
                File source = new File(sourceBox.getText());
                File dest = new File(destBox.getText());

                try {
                    FileUtils.copyDirectory(source, dest);
                } catch (IOException ioException) {
                    // log
                }

                createBackupButton.setEnabled(true);
                doneLabel.setText("Done!");
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });

    }
}