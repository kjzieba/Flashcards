package org.flashcards.Gui;

import org.flashcards.src.GuiApp;
import org.flashcards.src.repositories.TxtCardRepo;

import javax.swing.*;
import java.awt.*;

public class Edit extends JPanel {
    private final Initializer initializer;

    private JButton jButton;

    public Edit(Initializer initializer) {
        this.initializer = initializer;
        setPreferredSize(new Dimension(960, 560));
        setBackground(new java.awt.Color(41, 41, 41));
        setLayout(null);
        getBackButton();
        getSaveButton();
        getAddCardButton();
    }

    private void getBackButton() {
        JButton backButton = new JButton();
        backButton.setIcon(new ImageIcon("src/main/resources/img/backButton.png"));
        backButton.setBounds(13, 12, 30, 30);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.Add);
            GuiApp.getInstance().getApp().deleteRepo();
            remove(jButton);
        });
        add(backButton);
    }
    private void getSaveButton() {
        JButton addTextButton = new JButton("Save");
        addTextButton.setFont(new Font("Arbutus", Font.PLAIN, 16));
        addTextButton.setBounds(618, 47, 210, 65);
        addTextButton.addActionListener(e -> {
            GuiApp.getInstance().getApp().setId(GuiApp.getInstance().getApp().getId() + 1);
            initializer.update(GUInitializer.Panel.ChooseMode);
            remove(jButton);
        });
        add(addTextButton);
    }

    public void getNameRepository() {
        System.out.println(GuiApp.getInstance().getApp().getTitle());
        JButton addTextButton = new JButton(GuiApp.getInstance().getApp().getTitle());
            addTextButton.setFont(new Font("Arbutus", Font.PLAIN, 16));
            addTextButton.setBounds(132, 47, 210, 65);
            addTextButton.addActionListener(e -> {
                GuiApp.getInstance().getApp().printOne();
            });
            jButton = addTextButton;
            add(jButton);

    }


    private void getAddCardButton() {
        JButton addTextButton = new JButton("Add Flashcard");
        addTextButton.setFont(new Font("Arbutus", Font.PLAIN, 16));
        addTextButton.setBounds(375, 47, 210, 65);
        addTextButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.AddTxtCard);
        });
        add(addTextButton);
    }
}
