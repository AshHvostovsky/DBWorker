package view;

import data.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowSearch extends JFrame {


    private JTextField idField;
    private JTextField categoryField;
    private JTextField nameField;
    private JTextField parameter1Field;
    private JTextField parameter2Field;
    private JLabel jLabel_1;
    private JLabel jLabel_2;
    private JButton searchButton;
    private JButton exitButton;

        public WindowSearch() {
        super("Поиск корабля");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Центрирование окна

        initComponents();
        addComponents();
        addListeners();

        setVisible(true);
    }

    private void initComponents() {
        this.idField = new JTextField(10);
        this.categoryField = new JTextField(10);
        this.nameField = new JTextField(10);
        this.parameter1Field = new JTextField(10);
        this.parameter2Field = new JTextField(10);

        this.searchButton = new JButton("Искать");
        this.searchButton.setBackground(Color.BLACK);
        this.searchButton.setForeground(Color.WHITE);
        this.exitButton = new JButton("Выйти");
        this.exitButton.setBackground(Color.BLACK);
        this.exitButton.setForeground(Color.WHITE);
    }

    private void addComponents() {
        JPanel panel = new JPanel(new BorderLayout());

        // Создаем панель для полей вывода с GridLayout
        JPanel outputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        outputPanel.add(new JLabel("Тип корабля:"));
        categoryField.setEditable(false);
        outputPanel.add(categoryField);
        outputPanel.add(new JLabel("Название:"));
        nameField.setEditable(false);
        outputPanel.add(nameField);
        this.jLabel_1 = new JLabel("Хар-ка 1:");
        outputPanel.add(jLabel_1);
        parameter1Field.setEditable(false);
        parameter2Field.setEditable(false);
        outputPanel.add(parameter1Field);
        this.jLabel_2 = new JLabel("Хар-ка 2:");
        outputPanel.add(jLabel_2);
        outputPanel.add(parameter2Field);

        // Создаем панель для поля ввода ID и кнопок с FlowLayout
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idField);
        inputPanel.add(searchButton);
        inputPanel.add(exitButton);

        // Добавляем панели в основную панель
        panel.add(outputPanel, BorderLayout.NORTH);
        panel.add(inputPanel, BorderLayout.SOUTH);

        // Добавляем основную панель в окно
        getContentPane().add(panel, BorderLayout.CENTER);
    }

    private void addListeners() {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String temp_index = idField.getText();
                int ID;
                try {
                    ID = Integer.parseInt(temp_index);
                } catch (NumberFormatException ex) {
                    show_error("Некорректный ввод ID");

                    return;
                }
                if (ID < 0) {
                    show_error("ID не может быть отрицательным");

                } else {

                    if (!Group.searchMethod(ID)) {
                        show_error("Корабль с заданным ID не найден");
                    } else {

                        Boat b = Group.getBoatByID(ID);

                        nameField.setText(b.getName());
                        if (b instanceof Icebreaker) {
                            categoryField.setText("Ледокол");
                            jLabel_1.setText("Мощность");
                            parameter1Field.setText(Integer.toString(((Icebreaker) b).getPower()));
                            jLabel_2.setText("Макс. толщ. льда");
                            parameter2Field.setText(Integer.toString(((Icebreaker) b).getMax_thickness_of_ice()));
                        }
                        else if (b instanceof Sailboat) {
                            categoryField.setText("Парусник");
                            jLabel_1.setText("Тип паруса");
                            parameter1Field.setText((((Sailboat) b).getType_of_sails()));
                            jLabel_2.setText("Кол-во парусов");
                            parameter2Field.setText(Integer.toString(((Sailboat) b).getNumber_of_sails()));
                        }
                        else if (b instanceof Steamboat) {
                            categoryField.setText("Пароход");
                            jLabel_1.setText("Тип топлива");
                            parameter1Field.setText((((Steamboat) b).getType_of_fuel()));
                            jLabel_2.setText("Скорость");
                            parameter2Field.setText(Integer.toString(((Steamboat) b).getSpeed()));
                        }

                    }

                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Закрываем окно при нажатии кнопки
            }
        });
    }
    public void show_error(String message) {
        new ErrorDialog(message, this);
    }



}
