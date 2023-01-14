
package org.flashcards.gui;

import org.flashcards.TxtCard;
import org.flashcards.commands.FlashcardTxtHistory;
import org.flashcards.gui.components.ButtonComponents;
import org.flashcards.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;

public class AddTxt extends JPanel {
    private final Initializer initializer;

    private final JTextField nameTextField = new JTextField("Enter a title");

    private final JScrollPane scrollPane = new JScrollPane();

    private final FlashcardTxtHistory flashcardTxtHistory = new FlashcardTxtHistory();
    private boolean titleSet = false;

    private final ArrayList<Long> idCards = new ArrayList<>();
    private final JPanel content = new JPanel(new GridLayout(0, 3));

    public AddTxt(Initializer initializer) {
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
            initializer.update(GUInitializer.Panel.Menu);
            App.getInstance().deleteRepo("T");
            content.removeAll();
            content.repaint();
            content.revalidate();
            scrollPane.repaint();
            scrollPane.revalidate();
            getScrollPane();
            flashcardTxtHistory.clear();
        });

        add(backButton);
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
                ImageIcon trashIcon = new ImageIcon("src/main/resources/img/trashIcon.png");
                int option = JOptionPane.showConfirmDialog(null, "Are you sure?", "Select an Option...",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.OK_CANCEL_OPTION, trashIcon);
                if (option == 0) {
                    content.remove(component);
                    content.remove(component2);
                    content.remove(deleteButton);
                    content.repaint();
                    content.revalidate();
                    idCards.remove(card.getId());
                    App.getInstance().saveTxtToMemento(flashcardTxtHistory, card);
                } else {

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


    private void getAddButton() {
        JButton addButton = new ButtonComponents().addButtonComponent(457, 130);
        addButton.addActionListener(e -> {
            TxtCard txtCard = App.getInstance().createEmptyTxtCard();
            Component component = getTermTextArea(txtCard);
            Component component2 = getDefinitionTextArea(txtCard);
            content.add(component);
            content.add(component2);
            content.add(getDeleteButton(txtCard, component, component2));
            content.repaint();
            content.revalidate();
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
            getScrollPane();
            titleSet = false;
            flashcardTxtHistory.clear();
            App.getInstance().getAllCards().saveList(App.getInstance().getIdRepo() - 1);
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
                App.getInstance().changeTxtTitle(nameTextField.getText());
                titleSet = true;
            }
        });
        add(nameTextField);
    }

    private Component getTermTextArea(TxtCard card) {
        JTextField termTextArea = new JTextField("term");
        termTextArea.setHorizontalAlignment(JTextField.CENTER);
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
                    App.getInstance().changeQuestion(card, termTextArea.getText());
                } else {
                    App.getInstance().changeQuestion(card, termTextArea.getText());
                    idCards.add(card.getId());
                    App.getInstance().addTxtCard(card);
                }
            }
        });

        return add(termTextArea);
    }

    private Component getDefinitionTextArea(TxtCard card) {
        JTextField definitionTextArea = new JTextField("definition");
        definitionTextArea.setHorizontalAlignment(JTextField.CENTER);
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
                    App.getInstance().changeAnswer(card, definitionTextArea.getText());
                } else {
                    App.getInstance().changeAnswer(card, definitionTextArea.getText());
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
        scrollPane.getVerticalScrollBar().setUI(null);
        TxtCard txtCard = App.getInstance().createEmptyTxtCard();
        Component component = getTermTextArea(txtCard);
        Component component2 = getDefinitionTextArea(txtCard);
        content.add(component);
        content.add(component2);
        content.add(getDeleteButton(txtCard, component, component2));
        scrollPane.setViewportView(content);
        add(scrollPane);
    }
}