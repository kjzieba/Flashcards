
package org.flashcards.gui;

import org.flashcards.App;
import org.flashcards.ImgCard;
import org.flashcards.commands.FlashcardImgHistory;
import org.flashcards.gui.components.ButtonComponents;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class AddImg extends JPanel {
    private final Initializer initializer;

    private final JTextField nameTextField = new JTextField("Enter a title");

    private final JScrollPane scrollPane = new JScrollPane();

    private final FlashcardImgHistory flashcardImgHistory = new FlashcardImgHistory();

    private boolean titleSet = false;

    private final ArrayList<Long> idCards = new ArrayList<>();
    private final JPanel content = new JPanel(new GridLayout(0, 3));

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
    }

    private void getBackButton() {
        JButton backButton = new ButtonComponents().backButtonComponent(13, 12);
        backButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.Menu);
            App.getInstance().deleteRepo("I");
            content.removeAll();
            content.repaint();
            content.revalidate();
            scrollPane.repaint();
            scrollPane.revalidate();
            getScrollPane();
        });

        add(backButton);
    }

    private Component getDeleteButton(ImgCard card, Component component, Component component2) {
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
                App.getInstance().saveImgToMemento(flashcardImgHistory, card);
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
                    App.getInstance().addImgCard(App.getInstance().restoreImgFromMemento(flashcardImgHistory));
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
            Component component = getAnswer(imgCard);
            Component component2 = getImg(imgCard);
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
            System.out.println(App.getInstance().getAllCards());
            App.getInstance().setIdRepo(App.getInstance().getIdRepo() + 1);
            content.removeAll();
            content.repaint();
            content.revalidate();
            scrollPane.repaint();
            scrollPane.revalidate();
            getScrollPane();
            titleSet = false;
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
                App.getInstance().changeImgTitle(nameTextField.getText());
                titleSet = true;
            }
        });
        add(nameTextField);
    }

    private Component getAnswer(ImgCard card) {
        JTextArea definitionTextArea = new JTextArea("definition");
        definitionTextArea.setBackground(GUInitializer.buttonColor);
        definitionTextArea.setForeground(Color.white);
        definitionTextArea.setFont(new Font("Arbutus", Font.PLAIN, 16));
        definitionTextArea.setBounds(254, 192, 210, 35);
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
                    App.getInstance().changeAnswer(card,definitionTextArea.getText());
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
                    System.out.println("!");
                    File filePath = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    App.getInstance().changeImage(card,filePath.toString());
                    Image backgroundImage;
                    try {
                        backgroundImage = ImageIO.read(filePath);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    imgButton.setBorder(BorderFactory.createEmptyBorder());
                    imgButton.setContentAreaFilled(false);
                    imgButton.setSelectedIcon(new ImageIcon(backgroundImage));
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
        ImgCard imgCard = App.getInstance().createEmptyImgCard();
        Component component = getImg(imgCard);
        Component component2 = getAnswer(imgCard);
        content.add(component);
        content.add(component2);
        content.add(getDeleteButton(imgCard, component, component2));
        scrollPane.setViewportView(content);
        add(scrollPane);
    }

}
