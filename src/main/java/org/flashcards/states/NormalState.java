package org.flashcards.states;

import javax.swing.*;

public class NormalState implements State {

    @Override
    public void action(JButton button) {
        button.setBorderPainted(false);
    }

    @Override
    public String toString() {
        return "normal";
    }
}
