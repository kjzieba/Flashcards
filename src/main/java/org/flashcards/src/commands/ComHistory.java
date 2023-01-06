package org.flashcards.src.commands;

import java.util.Stack;

public class ComHistory {
    private final Stack<Command> history = new Stack<>();

    public void push(Command command) {
        history.push(command);
    }

    public Command pop() {
        return history.pop();
    }

    public Command peek() {
        return history.peek();
    }

    public boolean isEmpty() {
        return history.isEmpty();
    }
}
