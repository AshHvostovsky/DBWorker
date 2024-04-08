package view;

import data.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class WindowFlight{
    private JPanel jPanel_buttons;
    private JButton button_add;
    private JButton button_delete;
    private JTable jTable;
    private JScrollPane jScrollPane;
    private TableModelFlight myTableModel;
    //private JPanel bottomPanel;
    private BorderLayout borderLayout;
    //Для поиска по id
    private JDialog dialog;

    public WindowFlight(JFrame jFrame, TableModelFlight myTableModel) {
        this.myTableModel = myTableModel;
        this.dialog = new JDialog(jFrame, "Таблица рейсов", true);

        this.dialog.setSize(1100, 200);
        //this.dialog.pack();
        this.dialog.setLocationRelativeTo(jFrame);
        init();
        initListeners();


    }
    public void init() {
        // Создаем компоновщик BorderLayout для окна
        this.borderLayout = new BorderLayout();
        this.dialog.setLayout(borderLayout);

        //Панель для кнопок
        this.jPanel_buttons = new JPanel();

        //Кнопка добавки рейса
        this.button_add = new JButton("Добавить рейс");
        this.button_add.setBackground(Color.BLACK);
        this.button_add.setForeground(Color.WHITE);

        //Кнопка удаления
        this.button_delete = new JButton("Удалить");
        this.button_delete.setBackground(Color.BLACK);
        this.button_delete.setForeground(Color.WHITE);

        //Инициализация таблицы
        this.jTable = new JTable();
        //this.myTableModel = new MyTableModel_v2(new Group());
        this.jTable.setModel(myTableModel);

        //Настройка рендера таблицы
        initRenderTable();

        //Инициализация скролла
        this.jScrollPane = new JScrollPane(jTable);



        //Устанавливаем видимость на кнопки
        this.button_add.setVisible(true);
        this.button_delete.setVisible(true);


        //Инициализируем слушателей на кнопках
        initListeners();

        //Устанавливаем кнопки и текстовое поле в панель для кнопок
        this.jPanel_buttons.add(button_add);
        this.jPanel_buttons.add(button_delete);

        //Устанавливаем цвет для панели кнопок
        this.jPanel_buttons.setBackground(Color.WHITE);

        //Устанавливаем панели по плану
        this.dialog.add(this.jPanel_buttons, BorderLayout.SOUTH);
        this.dialog.add(this.jScrollPane, BorderLayout.CENTER);

        //Устанавливаем видимость окна
        this.dialog.setVisible(true); //Видимость
    }

    public void initListeners() {
        button_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                new NewFlight(dialog);

                myTableModel.reload_table();
            }
        });
        button_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    myTableModel.delete(jTable.getSelectedRow());
                }
                catch (IndexOutOfBoundsException | SQLException ex) {
                    show_error("Пожалуйста, выделите рейс для удаления.");

                }
            }
        });
    }
    public void initRenderTable() {
        // Настройка рендерера заголовков колонок без создания нового класса
        TableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setForeground(Color.WHITE); // Установите желаемый цвет
                setBackground(Color.BLACK);
                return this;
            }
        };
        // Установка рендерера заголовков колонок для каждой колонки таблицы
        for (int i = 0; i < jTable.getColumnModel().getColumnCount(); i++) {
            this.jTable.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
    }
    public void show_error(String message) {
        new ErrorDialog(message, dialog);
    }
}