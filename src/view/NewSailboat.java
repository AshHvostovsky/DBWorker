package view;

import data.Group;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewSailboat {
    public NewSailboat(JDialog jDialog) {
        JDialog dialog = new JDialog(jDialog, "Добавление парусника", true);
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(jDialog);

        // Создаем и добавляем текстовые поля для ввода
        JTextField Field_name = new JTextField();
        JTextField Field_id = new JTextField();
        Field_id.setText(Group.getNextAvailableId());
        Field_id.setEditable(false); // Запретить редактирование текстового поля
        Field_id.setFocusable(false); // Запретить фокусировку на текстовом поле
        JTextField Field_3 = new JTextField();
        JTextField Field_4 = new JTextField();

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Название:"));
        panel.add(Field_name);
        panel.add(new JLabel("ID:"));
        panel.add(Field_id);
        panel.add(new JLabel("Тип паруса:"));
        panel.add(Field_3);
        panel.add(new JLabel("Количество парусов:"));
        panel.add(Field_4);

        // Создаем кнопки "Добавить" и "Отмена"
        JButton addButton = new JButton("Добавить");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = Field_name.getText();
                String idText = Field_id.getText();
                String type_of_sails = Field_3.getText();
                String Textamount_sails = Field_4.getText();

                // Проверка на пустые поля
                if (Group.str_is_null(name) || Group.str_is_null(type_of_sails)  || Group.str_is_null(Textamount_sails)) {
                    new ErrorDialog("Пожалуйста, заполните все данные.", dialog);
                    return;
                }

                int id, amount_sails;
                try {
                    id = Integer.parseInt(idText);
                    amount_sails = Integer.parseInt(Textamount_sails);
                } catch (NumberFormatException ex) {
                    new ErrorDialog("Неверно введены численные данные.", dialog);
                    return;
                }

                // Проверка на уникальность id
                if (!data.Group.addSailboat(name, id, type_of_sails, amount_sails)) {
                    new ErrorDialog("Корабль с таким id существует.", dialog);
                    return;
                }

                // Закрываем диалоговое окно после добавления
                dialog.dispose();
            }

        });

        JButton cancelButton = new JButton("Отмена");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose(); // Закрываем диалоговое окно без добавления
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        // Добавляем панель с полями ввода и панель с кнопками на диалоговое окно
        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // Устанавливаем диалоговое окно видимым
        dialog.setVisible(true);
    }
}