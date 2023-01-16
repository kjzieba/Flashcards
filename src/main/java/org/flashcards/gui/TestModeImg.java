package org.flashcards.gui;

import org.flashcards.App;
import org.flashcards.ImgCard;
import org.flashcards.collection.ImgFlashcardCollection;
import org.flashcards.gui.components.ButtonComponents;
import org.flashcards.gui.components.CardComponent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class TestModeImg extends JPanel {

    private final Initializer initializer;
    public static JLabel questionNumber = new JLabel();
    private final JTextArea answerTextArea = new JTextArea();
    private JButton cardImg;
    public static int currentQuestion = 1;
    private ImgFlashcardCollection list;
    private ArrayList<ImgCard> questions;


    public TestModeImg(Initializer initializer) {
        this.initializer = initializer;

        setPreferredSize(new Dimension(960, 560));
        setBackground(GUInitializer.backgroundColor);
        setLayout(null);
        getBackButton();
        getAnswerTextArea();
        getImageTest();
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

    private void getImageTest() {
        cardImg = new CardComponent().cardButtonImgComponent(318, 45, 323, 175, "");
        cardImg.setVisible(true);
        add(cardImg);
    }

    private void getQuestionNumber() {
        questionNumber.setFont(new Font("Arbutus", Font.PLAIN, 15));
        questionNumber.setForeground(Color.white);
        questionNumber.setBounds(675, 105, 700, 50);
        add(questionNumber);
    }

    public void setImgLabel() {
        cardImg.setIcon(FlashCardsImgMode.ByteArrayToImage(questions.get(currentQuestion - 1).getImageQuestion()));
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
                    TestMode.rightAnswers += 1;
                } else {
                    TestMode.wrongAnswers += 1;
                }

                answerTextArea.setText("");
                currentQuestion++;
                questionNumber.setText(currentQuestion + "/" + QuestionsAmount.amount);
                setImgLabel();
            } else if (currentQuestion == QuestionsAmount.amount) {
                String answer = answerTextArea.getText().toLowerCase();
                if (answer.equals(questions.get(currentQuestion - 1).getAnswer())) {
                    TestMode.rightAnswers += 1;
                } else {
                    TestMode.wrongAnswers += 1;
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

    public void setListImg() {
        list = (ImgFlashcardCollection) App.getInstance().getAllCards().getFlashcardList(App.getInstance().getCurrentRepo(), "I");
        questions = new ArrayList<>(list.getList());
        Collections.shuffle(questions);
    }

    public void updateLabel() {
        questionNumber.setText(currentQuestion + "/" + QuestionsAmount.amount);
    }

}
