package org.flashcards.gui;


import org.flashcards.TxtCard;
import org.flashcards.collection.FlashcardCollectionInterface;
import org.flashcards.collection.TxtFlashcardCollection;
import org.flashcards.commands.Command;
import org.flashcards.commands.FlashcardTxtHistory;
import org.flashcards.gui.components.ButtonComponents;
import org.flashcards.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Objects;

public class EditTextRepo extends JPanel {
    private final Initializer initializer;

    private final ArrayList<Long> idCards = new ArrayList<>();

    private final JTextField nameTextField = new JTextField("Enter a title");

    private JScrollPane scrollPane = new JScrollPane();
    private final JTextArea termTextArea = new JTextArea("term");
    private final JTextArea definitionTextArea = new JTextArea("definition");

    private final FlashcardTxtHistory flashcardTxtHistory = new FlashcardTxtHistory();

    private TxtFlashcardCollection txtFlashcardCollection = new TxtFlashcardCollection("", new ArrayList<>(), 0L);

    private final JPanel content = new JPanel(new GridLayout(0, 3));


    public EditTextRepo(Initializer initializer) {
        this.initializer = initializer;
        setPreferredSize(new Dimension(960, 560));
        setBackground(GUInitializer.backgroundColor);
        setLayout(null);
        getBackButton();
        getSaveButton();
        getAddButton();
    }

    private void getAddButton() {
        JButton addButton = new ButtonComponents().addButtonComponent(457, 130);
        addButton.addActionListener(e -> {
            TxtCard txtCard = App.getInstance().createEmptyTxtCard();
            Component component = getTermTextArea2(txtCard);
            Component component2 = getDefinitionTextArea2(txtCard);
            content.add(component);
            content.add(component2);
            content.add(getDeleteButton(txtCard, component, component2));
            content.repaint();
            content.revalidate();
        });
        add(addButton);
    }

    private Component getDeleteButton(TxtCard card, Component component, Component component2) {
        JButton deleteButton = new JButton("delete");
        deleteButton.setBackground(GUInitializer.buttonColor);
        deleteButton.setForeground(Color.white);
        deleteButton.setFont(new Font("Arbutus", Font.PLAIN, 16));
        deleteButton.setBounds(254, 192, 210, 35);
        deleteButton.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        deleteButton.setBorder(BorderFactory.createCompoundBorder(
                deleteButton.getBorder(),
                BorderFactory.createEmptyBorder(10, 3, 10, 0)));
        deleteButton.addActionListener(e -> {
            if (idCards.contains(card.getId())) {
                App.getInstance().saveTxtToMemento(flashcardTxtHistory, card);
                int option = JOptionPane.showConfirmDialog(null, "Are you sure?", "Select an Option...",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                if (option == 0) {
                    content.remove(component);
                    content.remove(component2);
                    content.remove(deleteButton);
                    content.repaint();
                    content.revalidate();
                } else {
                    idCards.remove(card.getId());
                    App.getInstance().addTxtCard(App.getInstance().restoreTxtFromMemento(flashcardTxtHistory));
                }
            } else {
                content.remove(component);
                content.remove(component2);
                content.remove(deleteButton);
                content.repaint();
                content.revalidate();
            }
        });
        return add(deleteButton);
    }

    public void setRepo() {
        FlashcardCollectionInterface flashcardCollectionInterface = App.getInstance().getAllCards().getFlashcardList(App.getInstance().getCurrentRepo(), "T");
        txtFlashcardCollection = (TxtFlashcardCollection) flashcardCollectionInterface;
    }

    private void getBackButton() {
        JButton backButton = new ButtonComponents().backButtonComponent(13, 12);
        backButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.Menu);
            content.removeAll();
            content.repaint();
            content.revalidate();
            scrollPane.repaint();
            scrollPane.revalidate();
        });
        add(backButton);
    }


    public void getSaveButton() {
        JButton saveButton = new ButtonComponents().bigButtonComponent("Save", 601, 47);
        saveButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.ChooseMode);
            content.removeAll();
            remove(nameTextField);
            content.repaint();
            content.revalidate();
            scrollPane.repaint();
            scrollPane.revalidate();
            App.getInstance().deleteRepo("T");
            App.getInstance().saveEditedTxtRepo(txtFlashcardCollection);
            App.getInstance().getAllCards().saveList(App.getInstance().getCurrentRepo());

        });
        add(saveButton);
    }


    public void getNameRepository() {
        nameTextField.setFont(new Font("Arbutus", Font.PLAIN, 16));
        nameTextField.setBackground(GUInitializer.buttonColor);
        nameTextField.setForeground(Color.white);
        nameTextField.setBounds(149, 47, 210, 65);
        nameTextField.setHorizontalAlignment(JTextField.CENTER);
        nameTextField.setText(txtFlashcardCollection.getTitle());
        nameTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                App.getInstance().changeTxtTitle(nameTextField.getText());
            }
        });
        add(nameTextField);
    }

    private Component getTermTextArea(TxtCard card) {
        JTextArea termTextArea = new JTextArea(card.getTextQuestion());
        termTextArea.setBackground(GUInitializer.buttonColor);
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
            }


            @Override
            public void focusLost(FocusEvent e) {
                    App.getInstance().changeQuestion(card, termTextArea.getText());

                }

        });

        return add(termTextArea);
    }

    private Component getDefinitionTextArea(TxtCard card) {
        JTextArea definitionTextArea = new JTextArea(card.getAnswer());
        definitionTextArea.setBackground(GUInitializer.buttonColor);
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
            }

            @Override
            public void focusLost(FocusEvent e) {
                    App.getInstance().changeAnswer(card, definitionTextArea.getText());
            }
        });
        return add(definitionTextArea);
    }

    public void getScrollPane() {
        scrollPane.getViewport().setBackground(GUInitializer.backgroundColor);
        scrollPane.setBounds(193, 176, 570, 350);
        scrollPane.setAutoscrolls(true);
        scrollPane.createVerticalScrollBar();
        scrollPane.getVerticalScrollBar().setUI(null);
        for (TxtCard card : txtFlashcardCollection.getList()) {
            idCards.add(card.getId());
            Component component = getTermTextArea(card);
            Component component2 = getDefinitionTextArea(card);
            content.add(component);
            content.add(component2);
            content.add(getDeleteButton(card,component,component2));
        }
        scrollPane.setViewportView(content);
       add(scrollPane);
    }

    private Component getTermTextArea2(TxtCard card) {
        termTextArea.setBackground(GUInitializer.buttonColor);
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
                if (idCards.contains(card.getId()) && !Objects.equals(termTextArea.getText(), "term")) {

                } else {
                    termTextArea.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (idCards.contains(card.getId())) {
                    App.getInstance().changeQuestion(card,termTextArea.getText());
                } else {
                    App.getInstance().changeQuestion(card,termTextArea.getText());
                    idCards.add(card.getId());
                    App.getInstance().addTxtCard(card);

                }
            }
        });

        return add(termTextArea);
    }

    private Component getDefinitionTextArea2(TxtCard card) {
        definitionTextArea.setBackground(GUInitializer.buttonColor);
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
                if (idCards.contains(card.getId()) && !Objects.equals(definitionTextArea.getText(), "definition")) {

                } else {
                    definitionTextArea.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (idCards.contains(card.getId())) {
                    App.getInstance().changeAnswer(card,definitionTextArea.getText());
                } else {
                    App.getInstance().changeAnswer(card,definitionTextArea.getText());
                    idCards.add(card.getId());
                    App.getInstance().addTxtCard(card);
                }
            }
        });
        return add(definitionTextArea);
    }

}