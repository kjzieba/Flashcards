package org.flashcards.gui;

import org.flashcards.App;
import org.flashcards.collection.ImgFlashcardCollection;
import org.flashcards.collection.TxtFlashcardCollection;
import org.flashcards.gui.components.ButtonComponents;

import javax.swing.*;
import java.awt.*;

public class QuestionsAmount extends JPanel {

    private final Initializer initializer;
    public static int amount = 1;
    private final JLabel questionsAmountTitle = new JLabel();

    public QuestionsAmount(Initializer initializer) {
        this.initializer = initializer;
        setPreferredSize(new Dimension(960, 560));
        setBackground(GUInitializer.backgroundColor);
        setLayout(null);
        getBackButton();
        getQuestionsAmountTitle();
        getPlusIcon();
        getMinusIcon();
        getStartButton();
    }

    private void getBackButton() {
        JButton backButton = new ButtonComponents().backButtonComponent(13, 12);
        backButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.ChooseMode);
        });
        add(backButton);
    }

    private void getQuestionsAmountTitle() {
        questionsAmountTitle.setText(amount + " questions");
        questionsAmountTitle.setFont(new Font("Arbutus", Font.PLAIN, 17));
        questionsAmountTitle.setForeground(Color.white);
        questionsAmountTitle.setBounds(428, 190, 700, 50);
        add(questionsAmountTitle);
    }

    private void getPlusIcon() {
        JButton plusIcon = new JButton();
        plusIcon.setIcon(new ImageIcon("src/main/resources/img/plusIcon.png"));
        plusIcon.setBounds(575, 190, 45, 45);
        plusIcon.setBorderPainted(false);
        plusIcon.setContentAreaFilled(false);
        plusIcon.setFocusPainted(false);
        plusIcon.addActionListener(e -> {
            String currentType = App.getInstance().getAllCards().getTypeByID(App.getInstance().getCurrentRepo());
            if (currentType.equals("T")){
                TxtFlashcardCollection t = (TxtFlashcardCollection) App.getInstance().getAllCards().getFlashcardList(App.getInstance().getCurrentRepo(), "T");
                int maxAmount = t.getList().size();
                if (amount < maxAmount) {
                    amount++;
                    questionsAmountTitle.setText(amount + " questions");
                }
            } else if (currentType.equals("I")) {
                ImgFlashcardCollection i = (ImgFlashcardCollection) App.getInstance().getAllCards().getFlashcardList(App.getInstance().getCurrentRepo(), "I");
                int maxAmount = i.getList().size();
                if (amount < maxAmount) {
                    amount++;
                    questionsAmountTitle.setText(amount + " questions");
                }
            }
        });
        add(plusIcon);
    }

    private void getMinusIcon() {
        JButton minusIcon = new JButton();
        minusIcon.setIcon(new ImageIcon("src/main/resources/img/minusIcon.png"));
        minusIcon.setBounds(358, 190, 45, 45);
        minusIcon.setBorderPainted(false);
        minusIcon.setContentAreaFilled(false);
        minusIcon.setFocusPainted(false);
        minusIcon.addActionListener(e -> {
            if (amount > 1) {
                amount--;
                questionsAmountTitle.setText(amount + " questions");
            }
        });
        add(minusIcon);
    }

    private void getStartButton() {
        JButton startButton = new ButtonComponents().smallButtonComponent("Start test", 412, 290);
        startButton.addActionListener(e -> {
            String currentType = App.getInstance().getAllCards().getTypeByID(App.getInstance().getCurrentRepo());
            if (currentType.equals("T")){
                initializer.update(GUInitializer.Panel.TestMode);
                TestMode.questionNumber.setText(TestMode.currentQuestion + "/" + QuestionsAmount.amount);
            } else if (currentType.equals("I")) {
                initializer.update(GUInitializer.Panel.TestModeImg);
                TestModeImg.questionNumber.setText(TestMode.currentQuestion + "/" + QuestionsAmount.amount);
            }
        });
        add(startButton);
    }

    public static void setAmount(int amount) {
        QuestionsAmount.amount = amount;
    }

    public void updateGuiAmount() {
        questionsAmountTitle.setText(amount + " questions");
    }
}

