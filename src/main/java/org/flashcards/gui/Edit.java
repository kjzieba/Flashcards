
package org.flashcards.gui;

import org.flashcards.TxtCard;
import org.flashcards.collection.FlashcardCollectionInterface;
import org.flashcards.collection.TxtFlashcardCollection;
import org.flashcards.gui.components.ButtonComponents;
import org.flashcards.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

public class Edit extends JPanel {
    private final Initializer initializer;

    private final JTextField nameTextField = new JTextField("Enter a title");

    JScrollPane scrollPane = new JScrollPane();

    private boolean titleSet = false;

    private ArrayList<Long> idCards = new ArrayList<>();
    private JPanel content = new JPanel(new GridLayout(0, 2));

    public Edit(Initializer initializer) {
        this.initializer = initializer;
        setPreferredSize(new Dimension(960, 560));
        setBackground(GUInitializer.backgroundColor);
        setLayout(null);
        getBackButton();
        getSaveButton();
        getAddButton();
        getNameRepository();
        getScrollPane();
    }

    private void getBackButton() {
        JButton backButton = new ButtonComponents().backButtonComponent(13, 12);
        backButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.Add);
            App.getInstance().deleteRepo();
            content.removeAll();
            content.repaint();
            content.revalidate();
            scrollPane.repaint();
            scrollPane.revalidate();
        });

        add(backButton);
    }

    private void getAddButton() {
        JButton addButton = new ButtonComponents().addButtonComponent(457, 130);
        addButton.addActionListener(e -> {
            TxtCard txtCard = App.getInstance().createEmptyTxtCard();
            content.add(getTermTextArea(txtCard));
            content.add(getDefinitionTextArea(txtCard));

        });
        add(addButton);
    }

    private void getSaveButton() {
        JButton saveButton = new ButtonComponents().bigButtonComponent("Save", 601, 47);
        saveButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.ChooseMode);
            nameTextField.setText("Enter a title");
            System.out.println(App.getInstance().getAllCards());
            App.getInstance().setIdRepo(App.getInstance().getIdRepo() + 1);
            content.removeAll();
            content.repaint();
            content.revalidate();
            scrollPane.repaint();
            scrollPane.revalidate();
            titleSet = false;

        });
        add(saveButton);
    }


    private void getNameRepository() {
        nameTextField.setFont(new Font("Arbutus", Font.PLAIN, 16));
        nameTextField.setBackground(GUInitializer.buttonColor);
        nameTextField.setForeground(Color.white);
        nameTextField.setBounds(149, 47, 210, 65);
        nameTextField.setHorizontalAlignment(JTextField.CENTER);
        nameTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (titleSet) {

                } else {
                    nameTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                FlashcardCollectionInterface cardsRepo = App.getInstance().getAllCards().query(App.getInstance().getIdRepo());
                TxtFlashcardCollection txtCardRepo = (TxtFlashcardCollection) cardsRepo;
                txtCardRepo.setTitle(nameTextField.getText());
                titleSet = true;
            }
        });
        add(nameTextField);
    }

    private Component getTermTextArea(TxtCard card) {
        JTextArea termTextArea = new JTextArea("term");
        termTextArea.setBackground(new java.awt.Color(67, 69, 74));
        termTextArea.setForeground(Color.white);
        termTextArea.setFont(new Font("Arbutus", Font.PLAIN, 16));
        termTextArea.setBounds(254, 192, 210, 35);
        termTextArea.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        termTextArea.setBorder(BorderFactory.createCompoundBorder(
                termTextArea.getBorder(),
                BorderFactory.createEmptyBorder(10, 3, 10, 0)));
        termTextArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (idCards.contains(card.getId())) {

                } else {
                    termTextArea.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (idCards.contains(card.getId())) {
                    card.setTextQuestion(termTextArea.getText());
                } else {
                    card.setTextQuestion(termTextArea.getText());
                    idCards.add(card.getId());
                    App.getInstance().addTxtCard(card);
                }
            }
        });

        return add(termTextArea);
    }

    private Component getDefinitionTextArea(TxtCard card) {
        JTextArea definitionTextArea = new JTextArea("definition");
        definitionTextArea.setBackground(new java.awt.Color(67, 69, 74));
        definitionTextArea.setForeground(Color.white);
        definitionTextArea.setFont(new Font("Arbutus", Font.PLAIN, 16));
        definitionTextArea.setBounds(496, 192, 210, 85);
        definitionTextArea.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        definitionTextArea.setBorder(BorderFactory.createCompoundBorder(
                definitionTextArea.getBorder(),
                BorderFactory.createEmptyBorder(10, 3, 10, 0)));
        definitionTextArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (idCards.contains(card.getId())) {

                } else {
                    definitionTextArea.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (idCards.contains(card.getId())) {
                    card.setAnswer(definitionTextArea.getText());
                } else {
                    card.setAnswer(definitionTextArea.getText());
                    idCards.add(card.getId());
                    App.getInstance().addTxtCard(card);
                }
            }
        });
        return add(definitionTextArea);
    }

    private void getScrollPane() {
        scrollPane.getViewport().setBackground(GUInitializer.backgroundColor);
        scrollPane.setBounds(193, 176, 570, 350);
        scrollPane.setAutoscrolls(true);
        scrollPane.createVerticalScrollBar();
        TxtCard txtCard = App.getInstance().createEmptyTxtCard();
        content.add(getTermTextArea(txtCard));
        content.add(getDefinitionTextArea(txtCard));
        scrollPane.setViewportView(content);
        add(scrollPane);
    }
}