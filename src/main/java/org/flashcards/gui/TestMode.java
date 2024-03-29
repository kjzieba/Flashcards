package org.flashcards.gui;

import org.flashcards.App;
import org.flashcards.TxtCard;
import org.flashcards.collection.TxtFlashcardCollection;
import org.flashcards.gui.components.ButtonComponents;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class TestMode extends JPanel {

    private final Initializer initializer;
    public static JLabel questionNumber = new JLabel();
    private final JTextArea answerTextArea = new JTextArea();
    private final JLabel cardWord = new JLabel();
    public static int currentQuestion = 1;
    private TxtFlashcardCollection list;
    private ArrayList<TxtCard> questions;

    public static int rightAnswers = 0;
    public static int wrongAnswers = 0;

    public TestMode(Initializer initializer) {
        this.initializer = initializer;
        setPreferredSize(new Dimension(960, 560));
        setBackground(GUInitializer.backgroundColor);
        setLayout(null);
        getBackButton();
        getAnswerTextArea();
        getWordLabel();
        getQuestionNumber();
        getNextButton();
    }

    private void getBackButton() {
        JButton backButton = new ButtonComponents().backButtonComponent(13, 12);
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

    private void getWordLabel() {
        cardWord.setFont(new Font("Arbutus", Font.PLAIN, 25));
        cardWord.setForeground(Color.white);
        cardWord.setBounds(275, 157, 409, 50);
        add(cardWord);
    }

    public void setWordLabel() {
        cardWord.setText(questions.get(currentQuestion - 1).getTextQuestion());
        cardWord.setHorizontalAlignment(SwingConstants.CENTER);
        cardWord.setVerticalAlignment(SwingConstants.CENTER);
    }

    private void getAnswerTextArea() {
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
            if (currentQuestion < QuestionsAmount.amount) {
                if (currentQuestion + 1 == QuestionsAmount.amount) {
                    nextButton.setText("Finish");
                }

                String answer = answerTextArea.getText().toLowerCase();
                if (answer.equals(questions.get(currentQuestion - 1).getAnswer())) {
                    rightAnswers += 1;
                } else {
                    wrongAnswers += 1;
                }


                answerTextArea.setText("");
                currentQuestion++;
                questionNumber.setText(currentQuestion + "/" + QuestionsAmount.amount);
                setWordLabel();
            } else if (currentQuestion == QuestionsAmount.amount) {
                String answer = answerTextArea.getText().toLowerCase();
                if (answer.equals(questions.get(currentQuestion - 1).getAnswer())) {
                    rightAnswers += 1;
                } else {
                    wrongAnswers += 1;
                }


                currentQuestion = 1;
                answerTextArea.setText("");
                GUInitializer.flag = true;
                initializer.update(GUInitializer.Panel.Result);
                nextButton.setText("Next");
            }
        });
        add(nextButton);
    }

    public void setList() {
        list = (TxtFlashcardCollection) App.getInstance().getAllCards().getFlashcardList(App.getInstance().getCurrentRepo(), "T");
        questions = new ArrayList<>(list.getList());
        Collections.shuffle(questions);
    }

    public int getRightAnswers() {
        return rightAnswers;
    }

    public int getWrongAnswers() {
        return wrongAnswers;
    }

    public void setRightAnswers(int rightAnswers) {
        TestMode.rightAnswers = rightAnswers;
    }

    public void setWrongAnswers(int wrongAnswers) {
        TestMode.wrongAnswers = wrongAnswers;
    }

    public void updateLabel() {
        questionNumber.setText(currentQuestion + "/" + QuestionsAmount.amount);
    }
}
