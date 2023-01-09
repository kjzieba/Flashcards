
package org.flashcards.gui;

import org.flashcards.gui.components.ButtonComponents;
import org.flashcards.App;
import org.flashcards.repositories.CardsRepo;
import org.flashcards.repositories.TxtCardRepo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Edit extends JPanel {
    private final Initializer initializer;

    private final JTextField nameTextField = new JTextField("Enter a title");

    JScrollPane scrollPane = new JScrollPane();
    private JPanel content = new JPanel(new GridLayout(0, 2));

    public Edit(Initializer initializer) {
        this.initializer = initializer;
        setPreferredSize(new Dimension(960, 560));
        setBackground(GUInitializer.backgroundColor);
        setLayout(null);
        getBackButton();
        getSaveButton();
        getAddButton();
        getAddCardButton();
        getNameRepository();
        getScrollPane();
    }

    private void getBackButton() {
        JButton backButton = new ButtonComponents().backButtonComponent(13, 12);
        backButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.Add);
            App.getInstance().deleteRepo();
        });
        add(backButton);
    }

    private void getAddButton() {
        JButton addButton = new ButtonComponents().addButtonComponent(457, 130);
        addButton.addActionListener(e -> {
            content.add(getTermTextArea());
            content.add(getDefinitionTextArea());
        });
        add(addButton);
    }

    private void getSaveButton() {
        JButton saveButton = new ButtonComponents().bigButtonComponent("Save", 601, 47);
        saveButton.addActionListener(e -> {
            App.getInstance().setId(App.getInstance().getId() + 1);
            initializer.update(GUInitializer.Panel.ChooseMode);
            nameTextField.setText("Enter a title");
            System.out.println(App.getInstance().getAllCards());
        });
        add(saveButton);
    }


    private void getAddCardButton() {
        JButton addTextButton = new JButton("Add Flashcard");
        addTextButton.setFont(new Font("Arbutus", Font.PLAIN, 16));
        addTextButton.setBounds(375, 47, 210, 65);
        addTextButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.AddTxtCard);
        });
        add(addTextButton);
    }

    private void getNameRepository() {
        nameTextField.setFont(new Font("Arbutus", Font.PLAIN, 16));
        nameTextField.setBackground(GUInitializer.buttonColor);
        nameTextField.setForeground(Color.white);
        nameTextField.setBounds(149, 47, 210, 65);
        nameTextField.setHorizontalAlignment(JTextField.CENTER);
        nameTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {}

            @Override
            public void focusLost(FocusEvent e) {
                CardsRepo cardsRepo = App.getInstance().getAllCards().query(App.getInstance().getId());
                TxtCardRepo txtCardRepo = (TxtCardRepo) cardsRepo;
                txtCardRepo.setTitle(nameTextField.getText());
            }
        });
        add(nameTextField);
    }

    private Component getTermTextArea() {
        JTextArea termTextArea = new JTextArea("term");
        termTextArea.setBackground(new java.awt.Color(67, 69, 74));
        termTextArea.setForeground(Color.white);
        termTextArea.setFont(new Font("Arbutus", Font.PLAIN, 16));
        termTextArea.setBounds(254, 192, 210, 35);
        termTextArea.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        termTextArea.setBorder(BorderFactory.createCompoundBorder(
                termTextArea.getBorder(),
                BorderFactory.createEmptyBorder(10, 3, 10, 0)));

        return add(termTextArea);
    }

    private Component getDefinitionTextArea() {
        JTextArea definitionTextArea = new JTextArea("definition");
        definitionTextArea.setBackground(new java.awt.Color(67, 69, 74));
        definitionTextArea.setForeground(Color.white);
        definitionTextArea.setFont(new Font("Arbutus", Font.PLAIN, 16));
        definitionTextArea.setBounds(496, 192, 210, 85);
        definitionTextArea.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        definitionTextArea.setBorder(BorderFactory.createCompoundBorder(
                definitionTextArea.getBorder(),
                BorderFactory.createEmptyBorder(10, 3, 10, 0)));

        return add(definitionTextArea);
    }

    private void getScrollPane() {
        scrollPane.getViewport().setBackground(new Color(41, 41, 41));
        scrollPane.setBounds(193, 176, 570, 350);
        scrollPane.setAutoscrolls(true);
        scrollPane.createVerticalScrollBar();
        content.add(getTermTextArea());
        content.add(getDefinitionTextArea());
        scrollPane.setViewportView(content);
        add(scrollPane);
    }
}