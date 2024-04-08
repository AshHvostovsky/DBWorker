package view;

import data.Group;
import data.GroupFlight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewFlight{
    public NewFlight(JDialog jDialog) {
        JDialog dialog = new JDialog(jDialog, "Добавление рейса", true);
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(jDialog);

        // Создаем и добавляем текстовые поля для ввода
        JTextField Field_id = new JTextField();
        JTextField Field_departing = new JTextField();
        JTextField Field_arrival = new JTextField();


        JComboBox Field_boat_id = new JComboBox(Group.getIdBoats());
        Field_id.setText(GroupFlight.getNextAvailableId());
        Field_id.setEditable(false); // Запретить редактирование текстового поля
        Field_id.setFocusable(false); // Запретить фокусировку на текстовом поле


        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("ID:"));
        panel.add(Field_id);
        panel.add(new JLabel("Место отправления:"));
        panel.add(Field_departing);
        panel.add(new JLabel("Место прибытия:"));
        panel.add(Field_arrival);
        panel.add(new JLabel("ID корабля:"));
        panel.add(Field_boat_id);

        // Создаем кнопки "Добавить" и "Отмена"
        JButton addButton = new JButton("Добавить");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String T_id = Field_id.getText();
                String T_departing = Field_departing.getText();
                String T_arrival = Field_arrival.getText();
                String T_boat_id;
                try {
                    T_boat_id = (Field_boat_id.getSelectedItem().toString());
                }
                catch (java.lang.NullPointerException ex) {
                    new ErrorDialog("Нет кораблей для осуществления рейса.", jDialog);
                    return;
                }


                // Проверка на пустые поля
                if (GroupFlight.str_is_null(T_id) || GroupFlight.str_is_null(T_departing) || GroupFlight.str_is_null(T_arrival) || GroupFlight.str_is_null(T_boat_id)) {
                    new ErrorDialog("Пожалуйста, заполните все данные.", jDialog);
                    return;
                }
                int I_id, I_boat_id;
                try {
                    I_id = Integer.parseInt(T_id);
                    I_boat_id = Integer.parseInt(T_boat_id);
                } catch (NumberFormatException ex) {
                    new ErrorDialog("Неверно введены численные данные.", dialog);
                    return;
                }

                // Проверка на уникальность id
                if (!data.GroupFlight.addFlight(I_id, T_departing, T_arrival, I_boat_id)) {
                    System.out.println(I_id);
                    new ErrorDialog("Рейс с таким id существует.", jDialog);
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