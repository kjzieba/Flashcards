package org.flashcards.gui;

import org.flashcards.gui.components.ButtonComponents;

import javax.swing.*;
import java.awt.*;

public class TestMode extends JPanel {

    private final Initializer initializer;
    static JLabel questionNumber = new JLabel();
    static int actualWord = 0;
    public TestMode(Initializer initializer) {
        this.initializer = initializer;
        setPreferredSize(new Dimension(960, 560));
        setBackground(GUInitializer.backgroundColor);
        setLayout(null);
        getBackButton();
        getAnswerTextArea();
        getWordFromRepository("kocham jave");
        getQuestionNumber();
        getNextButton();

    }

    private void getBackButton() {
        JButton backButton = new ButtonComponents().backButtonComponent(13,12);
        backButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.ChooseMode);
        });
        add(backButton);
    }

    private void getQuestionNumber() {
        questionNumber.setFont(new Font("Arbutus", Font.PLAIN, 15));
        questionNumber.setForeground(Color.white);
        questionNumber.setBounds(675, 105, 700, 50);
        add(questionNumber);
    }

    private void getWordFromRepository(String word) {
        JLabel cardWord = new JLabel(word);
        cardWord.setFont(new Font("Arbutus", Font.PLAIN, 25));
        cardWord.setForeground(Color.white);
        cardWord.setBounds(388, 157, 700, 50);
        add(cardWord);
    }

    private void getAnswerTextArea() {
        JTextArea answerTextArea = new JTextArea();
        answerTextArea.setBackground(GUInitializer.buttonColor);
        answerTextArea.setForeground(Color.white);
        answerTextArea.setFont(new Font("Arbutus", Font.PLAIN, 16));
        answerTextArea.setBounds(275, 231, 409, 46);
        answerTextArea.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        answerTextArea.setBorder(BorderFactory.createCompoundBorder(
                answerTextArea.getBorder(),
                BorderFactory.createEmptyBorder(10, 3, 10, 0)));
        add(answerTextArea);
    }

    private void getNextButton() {
        JButton nextButton = new ButtonComponents().smallButtonComponent("Next", 548, 304);
        nextButton.addActionListener(e -> {
            actualWord++;
            questionNumber.setText(actualWord + "/" + QuestionsAmount.amount);
        });
        add(nextButton);
    }
}
