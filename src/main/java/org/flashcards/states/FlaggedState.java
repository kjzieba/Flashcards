package org.flashcards.states;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class FlaggedState implements State {

    @Override
    public void action(JButton button) {
        button.setBorderPainted(true);
        button.setBorder(new LineBorder(Color.red, 3));
    }

    @Override
    public String toString() {
        return "flagged";
    }
}
