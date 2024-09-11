package ui;

import javax.swing.*;
import java.awt.*;

// Dialog class that is used to generate dialog at the end of the application
public class Dialog extends JFrame {
    private String message;

    // EFFECTS: Create a dialog with given text
    public Dialog(String message) {
        this.message = message;
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
        initializeUI();
    }

    // MODIFIES: this
    // EFFECTS: Create a dialog with given text
    private void initializeUI() {
        setTitle("Dialog");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel(message);
        getContentPane().add(label);

        setVisible(true);
    }
}