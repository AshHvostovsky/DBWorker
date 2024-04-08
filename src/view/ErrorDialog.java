package view;

import javax.swing.*;

public class ErrorDialog {
    public ErrorDialog(String message, JDialog jDialog) {
        JOptionPane.showMessageDialog(jDialog, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }
    public ErrorDialog(String message, JFrame jFrame) {
        JOptionPane.showMessageDialog(jFrame, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }

}

