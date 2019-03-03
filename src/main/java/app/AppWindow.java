package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class AppWindow extends JFrame {

    //размеры окон и начальные координаты
    private static final int WIN_HEIGHT = 555; // высота окна
    private static final int WIN_WIDTH = 507; // ширина окна
    private static final int WIN_POS_X = 800; // начальная координата
    private static final int WIN_POS_Y = 300; // начальная координата

    private JTextField keyBoardPressedField;
    private JTextField mouseClickedField;
    private JPanel upperPanel;
    private JPanel bottomPanel;
    private JTextArea textArea;

    AppWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(WIN_POS_X, WIN_POS_Y, WIN_WIDTH, WIN_HEIGHT);        //размеры
        setTitle("Keyboard And Mouse Tester");
        setResizable(false);
        addComponentsToPane(getContentPane());  //добавляем компоненты
        addListeners(getContentPane()); //добавляем слушателей
        setVisible(true);
    }

    //добавление компонентов на форму
    private void addComponentsToPane(Container pane) {
        upperPanel = new JPanel(new GridLayout(1, 2)); //верхняя панель
        bottomPanel = new JPanel(new GridLayout(1,1));  //нижняя

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        bottomPanel.add(textArea);

        keyBoardPressedField = new JTextField("Number pressed");
        keyBoardPressedField.setEditable(false);

        mouseClickedField = new JTextField("Mouse clicked");
        mouseClickedField.setEditable(false);

        upperPanel.add(keyBoardPressedField);
        upperPanel.add(mouseClickedField);

        // добавление панели к окну
        add(upperPanel, BorderLayout.PAGE_START);
        add(bottomPanel);
    }

    //добавление слушателей
    private void addListeners(Container pane) {
        //анализ нажатой цифровой клавиши и игнорирование остальных
        textArea.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int k = e.getKeyCode();
                if ((k >= 48 && k <= 57) || ((k >= 96 && k <= 105))) {  //учитываем нажатие numpad
                    keyBoardPressedField.setText(String.format("Number pressed: [%s]", e.getKeyChar()));
                }
            }
        });

        //анализ клика мышки
        textArea.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                mouseClickedField.setText(String.format("Mouse clicked: [X: %d; Y: %d]", e.getPoint().x ,e.getPoint().y));
            }
        });
    }

}
