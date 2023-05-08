import javax.swing.*;
import java.io.File;
import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.ScrollPane;

import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class Interface_test {
    private static Simulator simulate = null;
    private static JButton button2, button3, button4;
    private static JLabel file_name, count_t0, count_t1, count_t2, count_t3, count_pc, status;

    public static void main(String[] args) {

        JFrame f = new JFrame("Simulator");

        Border blackline = BorderFactory.createLineBorder(Color.black);
        TitledBorder file_info_border = BorderFactory.createTitledBorder(blackline, "File Info");
        TitledBorder registers_border = BorderFactory.createTitledBorder(blackline, "Registers");
        TitledBorder memory_info_border = BorderFactory.createTitledBorder(blackline, "Memory Info");

        JPanel file_info_panel = new JPanel();
        file_info_panel.setBorder(file_info_border);
        file_info_panel.setBounds(30, 10, 300, 250);
        file_info_panel.setLayout(null);

        JPanel registers_panel = new JPanel();
        registers_panel.setBorder(registers_border);
        registers_panel.setBounds(30, 260, 200, 150);
        registers_panel.setLayout(null);

        JPanel memory_info_panel = new JPanel();
        memory_info_panel.setBorder(memory_info_border);
        memory_info_panel.setBounds(350, 10, 200, 245);
        memory_info_panel.setLayout(null);

        JLabel label11 = new JLabel("File Name");
        label11.setBounds(20, 20, 100, 30);

        file_name = new JLabel("");
        file_name.setBounds(20, 40, 200, 30);

        JLabel label12 = new JLabel("File Status");
        label12.setBounds(20, 65, 100, 30);

        status = new JLabel("");
        status.setBounds(20, 85, 250, 30);

        JLabel label13 = new JLabel("Code");
        label13.setBounds(20, 105, 100, 30);

        JTextArea code_text = new JTextArea(20,50);
        code_text.setEditable(false);
        code_text.setBounds(20, 130, 250, 110);

        JLabel label16 = new JLabel("t0");
        label16.setBounds(20, 20, 100, 30);

        JLabel label17 = new JLabel("t1");
        label17.setBounds(20, 40, 100, 30);

        JLabel label18 = new JLabel("t2");
        label18.setBounds(20, 60, 100, 30);

        JLabel label19 = new JLabel("t3");
        label19.setBounds(20, 80, 100, 30);

        JLabel label120 = new JLabel("PC");
        label120.setBounds(20, 100, 100, 30);

        count_t0 = new JLabel("0");
        count_t0.setBounds(170, 20, 100, 30);

        count_t1 = new JLabel("0");
        count_t1.setBounds(170, 40, 100, 30);

        count_t2 = new JLabel("0");
        count_t2.setBounds(170, 60, 100, 30);

        count_t3 = new JLabel("0");
        count_t3.setBounds(170, 80, 100, 30);

        count_pc = new JLabel("0");
        count_pc.setBounds(170, 100, 100, 30);

        JLabel label21 = new JLabel("Variables");
        label21.setBounds(20, 25, 100, 30);

        JTextArea data_text = new JTextArea();
        data_text.setEditable(false);
        data_text.setBounds(20, 50, 150, 80);

        JLabel label23 = new JLabel("Stack");
        label23.setBounds(20, 130, 100, 30);

        JTextArea stack_text = new JTextArea();
        stack_text.setEditable(false);
        stack_text.setBounds(20, 155, 150, 80);

        JButton button = new JButton("Load File");
        button.setBounds(375, 270, 150, 30);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String filePath = selectedFile.getAbsolutePath();
                    simulate = new Simulator(filePath);
                    code_text.setText(get.getCode(simulate));
                    data_text.setText(get.getDataText(simulate));
                    status.setText("File Load");
                    button2.setEnabled(true);
                    button3.setEnabled(true);
                    button4.setEnabled(true);
                    file_name.setText(selectedFile.getName());
                }
            }
        });

        button2 = new JButton("Check File");
        button2.setEnabled(false);
        button2.setBounds(375, 310, 150, 30);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button3.setEnabled(false);
                button4.setEnabled(false);
                if (simulate != null) {
                    try {
                        String error = simulate.checkProgram(get.getCode(simulate));
                        if (error == null) {
                            status.setText("Checked program");
                            button3.setEnabled(true);
                            button4.setEnabled(true);
                        } else {
                            status.setText(error);
                        }
                    } catch (Exception ex) {
                        status.setText(ex.getMessage());
                    }
                }
            }
        });

        button3 = new JButton("Simulate");
        button3.setEnabled(false);
        button3.setBounds(375, 350, 150, 30);
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button2.setEnabled(false);
                button4.setEnabled(false);
                if (simulate != null) {
                    try {
                        String error = simulate.simulateProgram(get.getCode(simulate), simulate.pc);
                        if (error == null) {
                            status.setText("Simulated Program");
                            button3.setEnabled(false);
                            count_t0.setText(String.valueOf(simulate.registers[0]));
                            count_t1.setText(String.valueOf(simulate.registers[1]));
                            count_t2.setText(String.valueOf(simulate.registers[2]));
                            count_t3.setText(String.valueOf(simulate.registers[3]));
                            data_text.setText(get.getDataText(simulate));
                            count_pc.setText(String.valueOf(simulate.pc));
                            stack_text.setText(get.getStackText(simulate));
                        } else {
                            status.setText(error);
                            System.out.println(error);
                        }
                    } catch (Exception ex) {
                        status.setText(ex.getMessage());
                        System.out.println(ex.getMessage());
                    }
                }
            }
        });

        button4 = new JButton("Step Simulation");
        button4.setEnabled(false);
        button4.setBounds(375, 390, 150, 30);
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button2.setEnabled(false);
                if (simulate != null) {
                    try {
                        String error = simulate.simulateProgram1line(get.getCode(simulate), simulate.pc);
                        if (error == null) {
                            status.setText("Step-by-Step Simulated Program");
                            count_t0.setText(String.valueOf(simulate.registers[0]));
                            count_t1.setText(String.valueOf(simulate.registers[1]));
                            count_t2.setText(String.valueOf(simulate.registers[2]));
                            count_t3.setText(String.valueOf(simulate.registers[3]));
                            data_text.setText(get.getDataText(simulate));
                            count_pc.setText(String.valueOf(simulate.pc));
                            stack_text.setText(get.getStackText(simulate));
                        } else if (error.equals("Simulated Program")) {
                            status.setText(error);
                            button3.setEnabled(false);
                            button4.setEnabled(false);
                            count_t0.setText(String.valueOf(simulate.registers[0]));
                            count_t1.setText(String.valueOf(simulate.registers[1]));
                            count_t2.setText(String.valueOf(simulate.registers[2]));
                            count_t3.setText(String.valueOf(simulate.registers[3]));
                            data_text.setText(get.getDataText(simulate));
                            count_pc.setText(String.valueOf(simulate.pc));
                            stack_text.setText(get.getStackText(simulate));
                        } else {
                            status.setText(error);
                            System.out.println(error);
                        }
                    } catch (Exception ex) {
                        status.setText(ex.getMessage());
                        System.out.println(ex.getMessage());
                    }
                }
            }
        });
        
        Font newLabelFont = new Font(label21.getFont().getName(), Font.ITALIC, label21.getFont().getSize());

        file_name.setFont(newLabelFont);
        count_pc.setFont(newLabelFont);
        count_t0.setFont(newLabelFont);
        count_t1.setFont(newLabelFont);
        count_t2.setFont(newLabelFont);
        count_t3.setFont(newLabelFont);
        status.setFont(newLabelFont);

        file_info_panel.add(label11);
        file_info_panel.add(label12);
        file_info_panel.add(label13);
        file_info_panel.add(file_name);
        file_info_panel.add(status);
        file_info_panel.add(code_text);

        registers_panel.add(label16);
        registers_panel.add(label17);
        registers_panel.add(label18);
        registers_panel.add(label19);
        registers_panel.add(label120);
        registers_panel.add(count_t0);
        registers_panel.add(count_t1);
        registers_panel.add(count_t2);
        registers_panel.add(count_t3);
        registers_panel.add(count_pc);
        memory_info_panel.add(label21);
        memory_info_panel.add(label23);
        memory_info_panel.add(data_text);
        memory_info_panel.add(stack_text);
        f.add(button);
        f.add(button2);
        f.add(button3);
        f.add(button4);
        f.add(file_info_panel);
        f.add(registers_panel);
        f.add(memory_info_panel);

        f.setSize(600, 500);
        f.setLayout(null);
        f.setVisible(true);
        f.setResizable(false);

    }
}

