package org.flashcards.Gui;

import org.flashcards.Gui.Components.ButtonComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Edit extends JPanel {

    private final Initializer initializer;
    private final JTextArea nameTextArea = new JTextArea("Enter a title");
    JScrollPane scrollPane = new JScrollPane();
//    private final JTextArea termTextArea = new JTextArea("term");
//    private final JTextArea definitionTextArea = new JTextArea("definition");
    int height = 192;
    int flag = 0;
    public Edit(Initializer initializer) {
        this.initializer = initializer;
        setPreferredSize(new Dimension(960, 560));
        setBackground(new java.awt.Color(41, 41, 41));
        setLayout(null);
        getBackButton();
        getAddButton();
        getSaveButton();
        getNameRepository();
        getScrollPane();
    }

    private void getBackButton() {
        JButton backButton = new ButtonComponents().backButtonComponent(13,12);
        backButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.Add);
        });
        add(backButton);
    }

    private void getSaveButton() {
        JButton saveButton = new ButtonComponents().bigButtonComponent("Save", 601, 47);
        saveButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.ChooseMode);
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String areaText = nameTextArea.getText();
                System.out.println(areaText);
            }
        });
        add(saveButton);
    }

    private void getAddButton() {
        JButton addButton = new ButtonComponents().addButtonComponent(457,132);
        addButton.addActionListener(e -> {
            height += 50;
            flag++;
            getDoubleTextArea(height);
        });
        add(addButton);
    }

    private void getNameRepository() {
        nameTextArea.setFont(new Font("Arbutus", Font.PLAIN, 16));
        nameTextArea.setBounds(149, 47, 210, 65);

        add(nameTextArea);
    }

    private void getDoubleTextArea(int y) {
        JTextArea termTextArea = new JTextArea("term");
        JTextArea definitionTextArea = new JTextArea("definition");
        termTextArea.setFont(new Font("Arbutus", Font.PLAIN, 16));
        termTextArea.setBounds(254, y, 210, 35);
        definitionTextArea.setFont(new Font("Arbutus", Font.PLAIN, 16));
        definitionTextArea.setBounds(496, y, 210, 35);

        add(termTextArea);
        add(definitionTextArea);
    }

    private void getScrollPane() {
        scrollPane.setBackground(new Color(41, 41, 41));
        scrollPane.getViewport().setBackground(new Color (41, 41, 41));
        scrollPane.setBounds(193, 176, 570, 350);
        getDoubleTextArea(height);
        add(scrollPane);
    }

}
