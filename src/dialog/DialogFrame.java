package dialog;

import java.awt.Color;

import javax.swing.JFrame;

public class DialogFrame extends JFrame {

    public DialogFrame() {
        getContentPane().setBackground(Color.DARK_GRAY);
        setTitle("Input Dialog in Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(400, 300);
    }
}
