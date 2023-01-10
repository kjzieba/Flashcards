package org.flashcards.gui;

import org.flashcards.gui.components.ButtonComponents;
import org.flashcards.App;

import javax.swing.*;
import java.awt.*;

public class ChooseActionForList extends JPanel {

    private final Initializer initializer;

    public ChooseActionForList(Initializer initializer) {
        this.initializer = initializer;
        setPreferredSize(new Dimension(960, 560));
        setBackground(GUInitializer.backgroundColor);
        setLayout(null);
        getCustomTitle();
        getBackButton();
        getEditButton();
        getDeleteButton();
        getFlashCardsButton();
        getLearnButton();
        getTestButton();
    }

    private void getCustomTitle() {
        JLabel title = new JLabel("Choose action");
        title.setFont(new Font("Arbutus", Font.PLAIN, 30));
        title.setForeground(Color.white);
        title.setBounds(180, 400, 700, 50);
        add(title);
    }

    private void getBackButton() {
        JButton backButton = new ButtonComponents().backButtonComponent(13, 12);
        backButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.Menu);
        });
        add(backButton);
    }

    private void getEditButton() {
        JButton addEditButton = new ButtonComponents().bigButtonComponent("Edit", 132, 239);
        addEditButton.addActionListener(e -> {
//            initializer.update(GUInitializer.Panel.AddTxt);
        });
        add(addEditButton);
    }

    private void getDeleteButton() {
        JButton getDeleteButton = new ButtonComponents().bigButtonComponent("Delete", 300, 239);
        getDeleteButton.addActionListener(e -> {
//            initializer.update(GUInitializer.Panel.AddTxt);
        });
        add(getDeleteButton);
    }

    private void getFlashCardsButton() {
        JButton flashcardsButton = new ButtonComponents().bigButtonComponent("flashcards", 149, 47);
        flashcardsButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.FlashCardsMode);
        });
        add(flashcardsButton);
    }

    private void getLearnButton() {
        JButton learnButton = new ButtonComponents().bigButtonComponent("learn", 392, 47);
        learnButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.LearnMode);
        });
        add(learnButton);
    }

    private void getTestButton() {
        JButton testButton = new ButtonComponents().bigButtonComponent("test", 635, 47);
        testButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.QuestionsAmount);
        });
        add(testButton);
    }

}