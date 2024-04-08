package view;

import data.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FirstWindow extends JFrame {


    MyTableModel_v2 myTableModel;
    TableModelFlight myTableModelFlight;

    //Кнопочки
    JButton button_icebreaker;
    JButton button_sailboat;
    JButton button_steamboat;
    JButton button_search;
    JButton button_flight;
    //private List<Flight> flights;
    public FirstWindow() {
        super("Начальное окно"); //Титл
        this.getRootPane().setBorder(BorderFactory.createLineBorder(new Color(10,10,50), 2)); // Создаем границу синего цвета толщиной 2 пикселя
        setSize(300, 280); //Размер
        //pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //Закрытие окна
        this.setLocationRelativeTo(null); //Окно посередине
        init();
    }
    public void init() {
        // Инициализируем модель таблицы. Дублировались строки
        this.myTableModel = new MyTableModel_v2(new Group());

        //Инициализируем модель таблицы полетов.
        this.myTableModelFlight = new TableModelFlight(new GroupFlight());

        // Создаем панель с кнопками
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0); // Отступы между кнопками

        // Создаем кнопки
        this.button_steamboat = new JButton("Пароходы");
        this.button_steamboat.setBackground(Color.BLACK);
        this.button_steamboat.setForeground(Color.WHITE);
        buttonPanel.add(button_steamboat, gbc);

        this.button_icebreaker = new JButton("Ледоколы");
        this.button_icebreaker.setBackground(Color.BLACK);
        this.button_icebreaker.setForeground(Color.WHITE);
        buttonPanel.add(button_icebreaker, gbc);

        this.button_sailboat = new JButton("Парусники");
        this.button_sailboat.setBackground(Color.BLACK);
        this.button_sailboat.setForeground(Color.WHITE);
        buttonPanel.add(button_sailboat, gbc);

        this.button_search = new JButton("Поиск по ID");
        this.button_search.setBackground(Color.BLACK);
        this.button_search.setForeground(Color.WHITE);
        buttonPanel.add(button_search, gbc);

        this.button_flight = new JButton("Рейсы");
        this.button_flight.setBackground(Color.BLACK);
        this.button_flight.setForeground(Color.WHITE);
        buttonPanel.add(button_flight, gbc);

        // Добавляем панель с кнопками в центр окна
        this.setLayout(new BorderLayout());
        this.add(buttonPanel, BorderLayout.CENTER);

        // Добавляем слушателей
        addListeners();

        // Устанавливаем видимость окна
        setVisible(true);
    }


    public void addListeners() {
        //Добавим слушателей на кнопки
        button_icebreaker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Icebreaker();
            }
        });
        button_sailboat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sailboat();
            }
        });
        button_steamboat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Steamboat();
            }
        });
        button_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search_method();
            }
        });
        button_flight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                open_flight();
            }
        });
    }

    private void open_flight() {
        new WindowFlight(this, myTableModelFlight);
    }

    public void Icebreaker() {
        myTableModel.setBoat(3);
        Group.sortList(3);
        new WindowIcebreaker(this, myTableModel);
    }
    public void Sailboat() {
        myTableModel.setBoat(2);
        Group.sortList(2);
        new WindowSailboat(this, myTableModel);
    }
    public void Steamboat() {
        myTableModel.setBoat(1);
        Group.sortList(1);
        new WindowSteamboat(this, myTableModel);
    }
    public void search_method() {
        new WindowSearch();
    }


}

