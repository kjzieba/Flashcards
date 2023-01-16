package org.flashcards.gui;

import org.flashcards.App;
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
            String currentType = App.getInstance().getAllCards().getTypeByID(App.getInstance().getCurrentRepo());
            if (currentType.equals("T")){
                initializer.update(GUInitializer.Panel.FlashCardsMode);
            } else if (currentType.equals("I")) {
                initializer.update(GUInitializer.Panel.FlashCardsImgMode);
            }
        });
        add(flashcardsButton);
    }

    private void getLearnButton() {
        JButton learnButton = new ButtonComponents().bigButtonComponent("learn", 392, 239);
        learnButton.addActionListener(e -> {
            String currentType = App.getInstance().getAllCards().getTypeByID(App.getInstance().getCurrentRepo());
            if (currentType.equals("T")){
                initializer.update(GUInitializer.Panel.LearnMode);
            } else if (currentType.equals("I")) {
                initializer.update(GUInitializer.Panel.LearnModeImg);
            }
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
