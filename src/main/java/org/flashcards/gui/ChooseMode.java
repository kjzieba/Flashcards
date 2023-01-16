package org.flashcards.gui;

import org.flashcards.gui.components.ButtonComponents;

import javax.swing.*;
import java.awt.*;

public class ChooseMode extends JPanel {

    private final Initializer initializer;
    ChooseMode(Initializer initializer) {
        this.initializer = initializer;
        setPreferredSize(new Dimension(960, 560));
        setBackground(GUInitializer.backgroundColor);
        setLayout(null);
        getBackButton();
        getFlashCardsButton();
        getTestButton();
        getLearnButton();
    }

    private void getBackButton() {
        JButton backButton = new ButtonComponents().backButtonComponent(13,12);
        backButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.Menu);
        });
        add(backButton);
    }

    private void getFlashCardsButton() {
        JButton flashcardsButton = new ButtonComponents().bigButtonComponent("flashcards", 149, 239);
        flashcardsButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.FlashCardsImgMode);
        });
        add(flashcardsButton);
    }

    private void getLearnButton() {
        JButton learnButton = new ButtonComponents().bigButtonComponent("learn", 392, 239);
        learnButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.LearnMode);
        });
        add(learnButton);
    }

    private void getTestButton() {
        JButton testButton = new ButtonComponents().bigButtonComponent("test", 635, 239);
        testButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.QuestionsAmount);
        });
        add(testButton);
    }
}
