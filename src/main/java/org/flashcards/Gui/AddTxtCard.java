package org.flashcards.Gui;

import org.flashcards.src.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class AddTxtCard extends JPanel {
    private final Initializer initializer;

    public AddTxtCard(Initializer initializer) {
        this.initializer = initializer;
        setPreferredSize(new Dimension(960, 560));
        setBackground(GUInitializer.backgroundColor);
        setLayout(null);
        addFlashCard();
        getCustomTitle();
    }

    private void getCustomTitle() {
        JLabel title = new JLabel("Add Next Flashcard");
        title.setFont(new Font("Arbutus", Font.PLAIN, 40));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setForeground(Color.white);
        title.setBounds(130, 120, 700, 50);
        add(title);
    }

    private void addFlashCard() {
        JButton addTextButton = new JButton("Save");
        JTextField answer = new JTextField("Enter an answer");
        JTextField question = new JTextField("Enter a question");
        JButton addSaveButton = new JButton("Done");
        addTextButton.setFont(new Font("Arbutus", Font.PLAIN, 16));
        addTextButton.setBounds(618, 239, 210, 65);

        addTextButton.addActionListener(e -> {
            App.getInstance().addTxtCard();
            initializer.update(GUInitializer.Panel.AddTxtCard);
            answer.setText("");
            question.setText("");
        });
        add(addTextButton);

        answer.setFont(new Font("Arbutus", Font.PLAIN, 16));
        answer.setHorizontalAlignment(JTextField.CENTER);
        answer.setBounds(375, 239, 210, 65);
        answer.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                App.getInstance().txtCard.setAnswer(answer.getText());
            }
        });
        add(answer);

        question.setFont(new Font("Arbutus", Font.PLAIN, 16));
        question.setHorizontalAlignment(JTextField.CENTER);
        question.setBounds(132, 239, 210, 65);
        question.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                App.getInstance().txtCard.setTextQuestion(question.getText());
            }
        });
        add(question);

        addSaveButton.setFont(new Font("Arbutus", Font.PLAIN, 16));
        addSaveButton.setBounds(375, 340, 210, 65);
        addSaveButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.Edit);
            answer.setText("");
            question.setText("");
        });
        add(addSaveButton);
    }
}