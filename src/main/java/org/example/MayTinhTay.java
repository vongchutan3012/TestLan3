package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MayTinhTay extends JFrame implements ActionListener {
    private JTextField display;
    private JPanel panel;
    private String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
    };
    private String currentInput = "";
    private double result = 0;
    private String operator = "";

    public MayTinhTay() {
        // Cấu hình JFrame
        setTitle("Máy Tính Tay");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tạo display để hiển thị kết quả
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);

        // Tạo panel chứa các nút bấm
        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        // Thêm các nút bấm vào panel
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 24));
            button.addActionListener(this);
            panel.add(button);
        }

        // Thêm display và panel vào JFrame
        add(display, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9' || command.equals(".")) {
            currentInput += command;
            display.setText(currentInput);
        } else if (command.equals("=")) {
            calculate();
            display.setText(String.valueOf(result));
            currentInput = "";
            operator = "";
        } else {
            if (!currentInput.equals("")) {
                calculate();
                operator = command;
                currentInput = "";
            } else {
                operator = command;
            }
        }
    }

    private void calculate() {
        double input = currentInput.isEmpty() ? 0 : Double.parseDouble(currentInput);

        switch (operator) {
            case "+" -> result += input;
            case "-" -> result -= input;
            case "*" -> result *= input;
            case "/" -> result /= input;
            default -> result = input;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MayTinhTay calculator = new MayTinhTay();
            calculator.setVisible(true);
        });
    }
}
