
package org.flashcards.gui;

import org.flashcards.App;
import org.flashcards.ImgCard;
import org.flashcards.commands.FlashcardImgHistory;
import org.flashcards.gui.components.ButtonComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class AddImg extends JPanel implements KeyListener {
    private final Initializer initializer;
    private final JTextField nameTextField = new JTextField("Enter a title");
    private final JLabel undoLabel = new JLabel("Press U to undo changes");
    private final JScrollPane scrollPane = new JScrollPane();
    private final FlashcardImgHistory flashcardImgHistory = new FlashcardImgHistory();
    private boolean titleSet = false;
    private final ArrayList<Long> idCards = new ArrayList<>();
    private final JPanel content = new JPanel(new GridLayout(0, 3));

    private Map<Long, String> Names = new HashMap<>();

    private int itemsDeleted = 0;

    public AddImg(Initializer initializer) {
        this.initializer = initializer;
        setPreferredSize(new Dimension(960, 560));
        setBackground(GUInitializer.backgroundColor);
        setLayout(null);
        getBackButton();
        getSaveButton();
        getAddButton();
        getNameRepository();
        getScrollPane();
        getUndoLabel();
        undoLabel.setVisible(false);
        this.setFocusable(true);
        this.requestFocusInWindow();
        addKeyListener(this);
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
            flashcardImgHistory.clear();
        });

        add(backButton);
    }


    private Component getDeleteButton(ImgCard card, Component component, Component component2) {
        JButton deleteButton = new JButton("delete");
        deleteButton.setBackground(Color.gray);
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
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, trashIcon);
                if (option == 0) {
                    undoLabel.setVisible(true);
                    content.remove(component);
                    content.remove(component2);
                    content.remove(deleteButton);
                    content.repaint();
                    content.revalidate();
                    idCards.remove(card.getId());
                    App.getInstance().saveImgToMemento(flashcardImgHistory, card);
                    itemsDeleted = itemsDeleted + 1;
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
            ImgCard imgCard = App.getInstance().createEmptyImgCard();
            Component component = getImg(imgCard);
            Component component2 = getDefinitionTextArea(imgCard);
            content.add(component);
            content.add(component2);
            content.add(getDeleteButton(imgCard, component, component2));
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
            App.getInstance().setIdRepo(App.getInstance().getIdRepo() + 1);
            content.removeAll();
            content.repaint();
            content.revalidate();
            scrollPane.repaint();
            scrollPane.revalidate();
            getScrollPane();
            titleSet = false;
            flashcardImgHistory.clear();
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
                if (!titleSet) {
                    nameTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                App.getInstance().changeImgTitle(nameTextField.getText());
                titleSet = true;
            }
        });
        add(nameTextField);
    }

    private Component getDefinitionTextArea(ImgCard card) {
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
                if (!(idCards.contains(card.getId()) && !Objects.equals(definitionTextArea.getText(), "definition"))) {
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
                    App.getInstance().addImgCard(card);
                }
            }
        });
        return add(definitionTextArea);
    }

    private Component getImg(ImgCard card) {
        JButton imgButton = new JButton("Upload Image");
        imgButton.setBackground(GUInitializer.buttonColor);
        imgButton.setForeground(Color.white);
        imgButton.setOpaque(true);
        imgButton.setFont(new Font("Arbutus", Font.PLAIN, 16));
        imgButton.setBounds(496, 192, 210, 65);
        imgButton.addActionListener(e -> {
            if(e.getSource() == imgButton) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int res = fileChooser.showOpenDialog(null);
                if(res == JFileChooser.APPROVE_OPTION) {
                    File filePath = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    Path fileName = filePath.toPath().getFileName();
                    App.getInstance().changeImage(card,filePath.toString());
                    imgButton.setText(String.valueOf(fileName));
                    if (idCards.contains(card.getId())) {
                        App.getInstance().changeImage(card, filePath.toString());
                        Names.remove(card.getId());
                        Names.put(card.getId(), String.valueOf(fileName));
                    } else {
                        App.getInstance().changeImage(card, filePath.toString());
                        idCards.add(card.getId());
                        App.getInstance().addImgCard(card);
                        Names.put(card.getId(), String.valueOf(fileName));
                    }
                }
            }
        });
        return add(imgButton);
    }

    private void getScrollPane() {
        scrollPane.getViewport().setBackground(GUInitializer.backgroundColor);
        scrollPane.setBounds(193, 176, 570, 350);
        scrollPane.setAutoscrolls(true);
        scrollPane.createVerticalScrollBar();
        scrollPane.getVerticalScrollBar().setUI(null);
        ImgCard imgCard = App.getInstance().createEmptyImgCard();
        Component component = getImg(imgCard);
        Component component2 = getDefinitionTextArea(imgCard);
        content.add(component);
        content.add(component2);
        content.add(getDeleteButton(imgCard, component, component2));
        scrollPane.setViewportView(content);
        add(scrollPane);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();
        if (c == 'U' || c == 'u') {
            if (!(itemsDeleted == 0)) {
                ImgCard imgCard = App.getInstance().restoreImgFromMemento(flashcardImgHistory);
                idCards.add(imgCard.getId());
                App.getInstance().addImgCard(imgCard);
                Component component = getImg2(imgCard);
                Component component2 = getDefinitionTextArea2(imgCard);
                content.add(component);
                content.add(component2);
                content.add(getDeleteButton(imgCard, component, component2));
                content.repaint();
                content.revalidate();
                itemsDeleted = itemsDeleted - 1;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (itemsDeleted == 0) {
            undoLabel.setVisible(false);
        }
    }

    private Component getDefinitionTextArea2(ImgCard card) {
        JTextField definitionTextArea = new JTextField(card.getAnswer());
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
            }

            @Override
            public void focusLost(FocusEvent e) {
                App.getInstance().changeAnswer(card, definitionTextArea.getText());
            }
        });
        return add(definitionTextArea);
    }

    private Component getImg2(ImgCard card) {
        JButton imgButton = new JButton(Names.get(card.getId()));
        imgButton.setBackground(GUInitializer.buttonColor);
        imgButton.setForeground(Color.white);
        imgButton.setOpaque(true);
        imgButton.setFont(new Font("Arbutus", Font.PLAIN, 16));
        imgButton.setBounds(496, 192, 210, 65);
        imgButton.addActionListener(e -> {
            if(e.getSource() == imgButton) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int res = fileChooser.showOpenDialog(null);
                if(res == JFileChooser.APPROVE_OPTION) {
                    File filePath = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    Path fileName = filePath.toPath().getFileName();
                    App.getInstance().changeImage(card,filePath.toString());
                    imgButton.setText(String.valueOf(fileName));
                    App.getInstance().changeImage(card, filePath.toString());
                }
            }
        });
        return add(imgButton);
    }

    private void getUndoLabel() {
        undoLabel.setFont(new Font("Arbutus", Font.PLAIN, 15));
        undoLabel.setForeground(Color.red);
        undoLabel.setBounds(369, 12, 229, 37);
        add(undoLabel);
    }


}
