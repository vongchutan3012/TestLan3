package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class QuanLyHocSinh extends JFrame implements ActionListener {
    private JTextField hoTenField, ngaySinhField, gioiTinhField;
    private JButton btnXacNhan, btnXuatFile;
    private JTextArea resultArea;

    public QuanLyHocSinh() {
        // Cấu hình JFrame
        setTitle("Quản Lý Học Sinh");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        // Tạo các label và text field
        JLabel hoTenLabel = new JLabel("Họ và Tên:");
        hoTenField = new JTextField();

        JLabel ngaySinhLabel = new JLabel("Ngày Sinh:");
        ngaySinhField = new JTextField();

        JLabel gioiTinhLabel = new JLabel("Giới Tính:");
        gioiTinhField = new JTextField();

        // Tạo các nút bấm
        btnXacNhan = new JButton("Xác Nhận");
        btnXacNhan.addActionListener(this);

        btnXuatFile = new JButton("Xuất ra file");
        btnXuatFile.addActionListener(this);

        // Tạo area để hiển thị kết quả
        resultArea = new JTextArea();
        resultArea.setEditable(false);

        // Thêm các thành phần vào JFrame
        add(hoTenLabel);
        add(hoTenField);
        add(ngaySinhLabel);
        add(ngaySinhField);
        add(gioiTinhLabel);
        add(gioiTinhField);
        add(btnXacNhan);
        add(btnXuatFile);
        add(new JScrollPane(resultArea));

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnXacNhan) {
            String hoTen = hoTenField.getText();
            String ngaySinh = ngaySinhField.getText();
            String gioiTinh = gioiTinhField.getText();

            String result = "Họ và Tên: " + hoTen + "\n" +
                    "Ngày Sinh: " + ngaySinh + "\n" +
                    "Giới Tính: " + gioiTinh + "\n";

            resultArea.setText(result);
        } else if (e.getSource() == btnXuatFile) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("thong_tin_hoc_sinh.txt"))) {
                writer.write(resultArea.getText());
                JOptionPane.showMessageDialog(this, "Xuất file thành công!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi ghi file!");
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(QuanLyHocSinh::new);
    }
}
