import javax.swing.*;
import java.io.File;
import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

class Interface_test {
    private static Simulator simulate = null;
    private static JButton button2, button3, button4;
    private static JLabel file_name, count_t0, count_t1, count_t2, count_t3, count_pc, status;

    public static void main(String[] args) {

        JFrame f = new JFrame("Simulator");

        JLabel label = new JLabel("File Info");
        label.setBounds(50, 0, 100, 30);

        JLabel label11 = new JLabel("File Name");
        label11.setBounds(50, 30, 100, 30);

        file_name = new JLabel("");
        file_name.setBounds(50, 50, 200, 30);

        JLabel label12 = new JLabel("File Status");
        label12.setBounds(50, 75, 100, 30);

        status = new JLabel("");
        status.setBounds(50, 95, 250, 30);

        JLabel label13 = new JLabel("Code");
        label13.setBounds(50, 115, 100, 30);

        JTextArea code_text = new JTextArea();
        code_text.setEditable(false);
        code_text.setBounds(50, 140, 150, 80);

        JLabel label14 = new JLabel("Registers");
        label14.setBounds(50, 230, 100, 30);

        JLabel label15 = new JLabel("Next instruction");
        label15.setBounds(50, 270, 100, 30);

        JLabel label16 = new JLabel("t0");
        label16.setBounds(50, 310, 100, 30);

        JLabel label17 = new JLabel("t1");
        label17.setBounds(50, 330, 100, 30);

        JLabel label18 = new JLabel("t2");
        label18.setBounds(50, 350, 100, 30);

        JLabel label19 = new JLabel("t3");
        label19.setBounds(50, 370, 100, 30);

        JLabel label120 = new JLabel("PC");
        label120.setBounds(50, 390, 100, 30);

        count_t0 = new JLabel("0");
        count_t0.setBounds(200, 310, 100, 30);

        count_t1 = new JLabel("0");
        count_t1.setBounds(200, 330, 100, 30);

        count_t2 = new JLabel("0");
        count_t2.setBounds(200, 350, 100, 30);

        count_t3 = new JLabel("0");
        count_t3.setBounds(200, 370, 100, 30);

        count_pc = new JLabel("0");
        count_pc.setBounds(200, 390, 100, 30);

        JLabel label20 = new JLabel("Memory Info");
        label20.setBounds(400, 0, 100, 30);

        JLabel label21 = new JLabel("Variables");
        label21.setBounds(400, 55, 100, 30);

        JTextArea data_text = new JTextArea();
        data_text.setEditable(false);
        data_text.setBounds(400, 80, 150, 80);

        JLabel label23 = new JLabel("Stack");
        label23.setBounds(400, 160, 100, 30);

        JTextArea stack_text = new JTextArea();
        stack_text.setEditable(false);
        stack_text.setBounds(400, 185, 150, 80);

        JButton button = new JButton("Load File");
        button.setBounds(400, 280, 150, 30);
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
        button2.setBounds(400, 320, 150, 30);
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
        button3.setBounds(400, 360, 150, 30);
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
        button4.setBounds(400, 400, 150, 30);
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

        Font newLabelFont = new Font(label.getFont().getName(), Font.ITALIC, label.getFont().getSize());

        file_name.setFont(newLabelFont);
        count_pc.setFont(newLabelFont);
        count_t0.setFont(newLabelFont);
        count_t1.setFont(newLabelFont);
        count_t2.setFont(newLabelFont);
        count_t3.setFont(newLabelFont);
        status.setFont(newLabelFont);

        Font newLabelFont1 = new Font(label.getFont().getName(), Font.PLAIN, label.getFont().getSize());
        label11.setFont(newLabelFont1);
        label12.setFont(newLabelFont1);
       


        f.add(label);
        f.add(label21);
        f.add(label11);
        f.add(label12);
        f.add(label13);
        f.add(label14);
        f.add(label15);
        f.add(label16);
        f.add(label17);
        f.add(label18);
        f.add(label19);
        f.add(label120);
        f.add(code_text);
        f.add(data_text);
        f.add(stack_text);
        f.add(label20);
        f.add(label21);
        f.add(label23);
        f.add(file_name);
        // f.add(textArea2);
        f.add(button);
        f.add(button2);
        f.add(button3);
        f.add(button4);
        f.add(count_t0);
        f.add(count_t1);
        f.add(count_t2);
        f.add(count_t3);
        f.add(count_pc);
        f.add(status);

        f.setSize(700, 500);
        f.setLayout(null);
        f.setVisible(true);
    }
}