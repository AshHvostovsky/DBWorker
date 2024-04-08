
package view;

import data.Group;
import data._MyTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;


public class MainWindow extends JFrame {
    /*
    private JPanel jPanel_1;
    private JButton button_add_steamboat;
    private JButton button_add_icebreaker;
    private JButton button_add_sailboat;
    private JButton button_delete;
    private JTable jTable;
    private JScrollPane jScrollPane;

    private _MyTableModel myTableModel;

    private JPanel bottomPanel;
    private JPanel centerPanel;
    private BorderLayout borderLayout;

    //Для поиска по id
    private JButton search;
    private TextField searchField;

    //Кнопка отмены поиска
    private JButton button_back;
    public MainWindow() {
        super("Title"); //Титл
        this.getRootPane().setBorder(BorderFactory.createLineBorder(new Color(10,10,50), 2)); // Создаем границу синего цвета толщиной 2 пикселя
        setSize(1920, 300); //Размер
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //Закрытие окна
        this.setLocationRelativeTo(null); //Окно посередине
        init();
    }

    public void init() {
        // Создаем компоновщик BorderLayout для главного окна
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);
        // Центральная панель с таблицей
        JPanel centerPanel = new JPanel();
        JPanel jPanel_1 = new JPanel();
        JButton button_add_steamboat = new JButton("Добавить параход");
        button_add_steamboat.setBackground(Color.BLACK);
        button_add_steamboat.setForeground(Color.WHITE);
        JButton button_add_icebreaker = new JButton("Добавить ледокол");
        button_add_icebreaker.setBackground(Color.BLACK);
        button_add_icebreaker.setForeground(Color.WHITE);
        JButton button_add_sailboat = new JButton("Добавить парусник");
        button_add_sailboat.setBackground(Color.BLACK);
        button_add_sailboat.setForeground(Color.WHITE);
        JButton button_delete = new JButton("Удалить корабль");
        button_delete.setBackground(Color.RED);
        button_delete.setForeground(Color.WHITE);

        //Кнопка поиска
        JButton search = new JButton("Искать по ID");
        search.setBackground(Color.BLACK);
        search.setForeground(Color.WHITE);

        //Поле
        TextField searchField = new TextField();

        JButton button_back = new JButton("Отмена");
        button_back.setBackground(Color.BLACK);
        button_back.setForeground(Color.WHITE);
        jTable = new JTable();
        _MyTableModel myTableModel = new _MyTableModel(new Group());
        jTable.setModel(myTableModel);
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
            jTable.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        JScrollPane jScrollPane = new JScrollPane(jTable);
        centerPanel.add(jScrollPane); // Пример создания пустой таблицы 5x3
        button_add_steamboat.setVisible(true);
        button_add_icebreaker.setVisible(true);
        button_add_sailboat.setVisible(true);
        button_delete.setVisible(true);
        search.setVisible(true);
        searchField.setVisible(true);
        button_back.setVisible(false);
        button_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    myTableModel.delete(jTable.getSelectedRow());
                }
                catch (IndexOutOfBoundsException ex) {
                    show_error("Пожалуйста, выделите корабль для удаления.");

                }

            }
        });
        button_add_steamboat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSteamboat();
                myTableModel.sort();
                myTableModel.reload_table();
            }
        });
        button_add_sailboat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSailboat();
                myTableModel.sort();
                myTableModel.reload_table();
            }
        });
        button_add_icebreaker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addIcebreaker();
                myTableModel.sort();
                myTableModel.reload_table();
            }
        });
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean tmp_bl = false;
                String temp_index =  searchField.getText();
                int ID;
                try {
                    ID = Integer.parseInt(temp_index);
                }
                catch (NumberFormatException ex) {
                    show_error("Некорректный ввод ID");
                    tmp_bl = true;
                    return;
                }
                if (ID < 0) {
                    show_error("ID не может быть отрицательным");
                    tmp_bl = true;
                }
                else {
                    _MyTableModel.setIndex(ID);
                    if (!Group.searchMethod(ID)) {
                        show_error("Корабль с заданным ID не найден");
                        _MyTableModel.setIndex(-1);
                        tmp_bl = true;
                    }
                    myTableModel.reload_table();

                }
                if (tmp_bl) {

                    button_delete.setEnabled(true);
                    button_add_sailboat.setEnabled(true);
                    button_add_steamboat.setEnabled(true);
                    button_add_icebreaker.setEnabled(true);
                    button_back.setVisible(false);
                }
                else {
                    button_delete.setEnabled(false);
                    button_add_sailboat.setEnabled(false);
                    button_add_steamboat.setEnabled(false);
                    button_add_icebreaker.setEnabled(false);
                    button_back.setVisible(true);
                }
            }
        });
        button_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _MyTableModel.setIndex(-1);
                myTableModel.sort();
                myTableModel.reload_table();

                //Изменение видимости кнопок
                button_back.setVisible(false);
                button_delete.setEnabled(true);
                button_add_sailboat.setEnabled(true);
                button_add_steamboat.setEnabled(true);
                button_add_icebreaker.setEnabled(true);
            }
        });
        jPanel_1.add(button_add_steamboat);
        jPanel_1.add(button_add_icebreaker);
        jPanel_1.add(button_add_sailboat);
        jPanel_1.add(button_delete);
        jPanel_1.setBackground(Color.WHITE);

        jPanel_1.add(search);
        jPanel_1.add(searchField);
        jPanel_1.add(button_back);

        this.add(jPanel_1, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);
        this.add(jScrollPane);
        setVisible(true); //Видимость
    }
    public void show_error(String message) {
        new ErrorDialog(message, this);
    }
    public void addSteamboat() {
        new NewSteamboat(this);
    }
    public void addSailboat() {
        new NewSailboat(this);
    }
    public void addIcebreaker() {
        new NewIcebreaker(this);
    }
    */
}
