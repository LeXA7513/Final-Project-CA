import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MyInterface extends JFrame {

    private static Simulator simulate = null;
    private static JButton button2, button3, button4,button1;
    private static JLabel file_name, count_t0, count_t1, count_t2, count_t3, count_pc, status, data_text, stack_text, code_text, labelnext;


    public MyInterface() {
        super("Assembly Simulator");

        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
    

        JPanel fileInfoPanel = new JPanel(new GridBagLayout());
    
        JLabel labelName = new JLabel("File Name");
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 0, 5, 20);
        constraints.gridx = 0;
        constraints.gridy = 0;
        fileInfoPanel.add(labelName, constraints);

        JLabel file_name = new JLabel(" ");
        GridBagConstraints constraints01 = new GridBagConstraints();
        constraints01.anchor = GridBagConstraints.WEST;
        constraints01.insets = new Insets(0, 0, 10, 20);
        constraints01.gridx = 0;
        constraints01.gridy = 5;
        fileInfoPanel.add(file_name, constraints01);
    
        JLabel labelStatus = new JLabel("File Status");
        GridBagConstraints constraints0 = new GridBagConstraints();
        constraints0.anchor = GridBagConstraints.WEST;
        constraints0.insets = new Insets(10, 0, 5, 20);
        constraints0.gridx = 0;
        constraints0.gridy = 6;
        fileInfoPanel.add(labelStatus, constraints0);

        JLabel status = new JLabel("");
        GridBagConstraints constraints02 = new GridBagConstraints();
        constraints02.anchor = GridBagConstraints.WEST;
        constraints02.insets = new Insets(0, 0, 0, 20);
        constraints02.gridx = 0;
        constraints02.gridy = 7;
        fileInfoPanel.add(status, constraints02);
    
        JLabel labelcode = new JLabel("Code");
        GridBagConstraints constraints1 = new GridBagConstraints();
        constraints1.anchor = GridBagConstraints.WEST;
        constraints1.insets = new Insets(10, 0, 10, 20);
        constraints1.gridx = 0;
        constraints1.gridy = 10;
        fileInfoPanel.add(labelcode, constraints1);
    
        JTextArea code_text = new JTextArea(10, 35);
        JScrollPane scrollPane = new JScrollPane(code_text);
        GridBagConstraints constraints2 = new GridBagConstraints();
        constraints2.anchor = GridBagConstraints.WEST;
        constraints2.insets = new Insets(10, 0, 0, 0);
        constraints2.gridx = 0;
        constraints2.gridy = 12;
        fileInfoPanel.add(scrollPane, constraints2);
    
        fileInfoPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "File Info"));
    
        contentPane.add(fileInfoPanel);


    
        JPanel registerPanel = new JPanel(new GridBagLayout());
    
        JLabel labelNext = new JLabel("Next instruction");
        GridBagConstraints constraints3 = new GridBagConstraints();
        constraints3.anchor = GridBagConstraints.WEST;
        constraints3.insets = new Insets(10, 0, 5, 0);
        constraints3.gridx = 0;
        constraints3.gridy = 0;
        registerPanel.add(labelNext, constraints3);

        JLabel labelnext = new JLabel("Empty");
        GridBagConstraints constraints00 = new GridBagConstraints();
        constraints00.anchor = GridBagConstraints.WEST;
        constraints00.insets = new Insets(0, 0, 10, 0);
        constraints00.gridx = 0;
        constraints00.gridy = 1;
        registerPanel.add(labelnext, constraints00);
    
        JLabel labelT0 = new JLabel("t0");
        GridBagConstraints constraints4 = new GridBagConstraints();
        constraints4.anchor = GridBagConstraints.WEST;
        constraints4.insets = new Insets(10, 0, 10, 0);
        constraints4.gridx = 0;
        constraints4.gridy = 3;
        registerPanel.add(labelT0, constraints4);

        JLabel count_t0 = new JLabel("0");
        GridBagConstraints constraints03 = new GridBagConstraints();
        constraints03.anchor = GridBagConstraints.WEST;
        constraints03.insets = new Insets(10, 0, 10, 20);
        constraints03.gridx = 1;
        constraints03.gridy = 3;
        registerPanel.add(count_t0, constraints03);

        JLabel labelT1 = new JLabel("t1");
        GridBagConstraints constraints5 = new GridBagConstraints();
        constraints5.anchor = GridBagConstraints.WEST;
        constraints5.insets = new Insets(10, 0, 10, 0);
        constraints5.gridx = 0;
        constraints5.gridy = 4;
        registerPanel.add(labelT1, constraints5);

        JLabel count_t1 = new JLabel("0");
        GridBagConstraints constraints04 = new GridBagConstraints();
        constraints04.anchor = GridBagConstraints.WEST;
        constraints04.insets = new Insets(10, 0, 10, 20);
        constraints04.gridx = 1;
        constraints04.gridy = 4;
        registerPanel.add(count_t1, constraints04);

        JLabel labelT2 = new JLabel("t2");
        GridBagConstraints constraints6 = new GridBagConstraints();
        constraints6.anchor = GridBagConstraints.WEST;
        constraints6.insets = new Insets(10, 0, 10, 0);
        constraints6.gridx = 0;
        constraints6.gridy = 5;
        registerPanel.add(labelT2, constraints6);

        JLabel count_t2 = new JLabel("0");
        GridBagConstraints constraints05 = new GridBagConstraints();
        constraints05.anchor = GridBagConstraints.WEST;
        constraints05.insets = new Insets(10, 0, 10, 20);
        constraints05.gridx = 1;
        constraints05.gridy = 5;
        registerPanel.add(count_t2, constraints05);

        JLabel labelT3 = new JLabel("t3");
        GridBagConstraints constraints7 = new GridBagConstraints();
        constraints7.anchor = GridBagConstraints.WEST;
        constraints7.insets = new Insets(10, 0, 10, 0);
        constraints7.gridx = 0;
        constraints7.gridy = 6;
        registerPanel.add(labelT3, constraints7);

        JLabel count_t3 = new JLabel("0");
        GridBagConstraints constraints06 = new GridBagConstraints();
        constraints06.anchor = GridBagConstraints.WEST;
        constraints06.insets = new Insets(10, 0, 10, 20);
        constraints06.gridx = 1;
        constraints06.gridy = 6;
        registerPanel.add(count_t3, constraints06);

        JLabel labelPC = new JLabel("PC");
        GridBagConstraints constraints8 = new GridBagConstraints();
        constraints8.anchor = GridBagConstraints.WEST;
        constraints8.insets = new Insets(10, 0, 10, 0);
        constraints8.gridx = 0;
        constraints8.gridy = 7;
        registerPanel.add(labelPC, constraints8);

        JLabel count_pc = new JLabel("0");
        GridBagConstraints constraints07 = new GridBagConstraints();
        constraints07.anchor = GridBagConstraints.WEST;
        constraints07.insets = new Insets(10, 0, 10, 20);
        constraints07.gridx = 1;
        constraints07.gridy = 7;
        registerPanel.add(count_pc, constraints07);

        JLabel labelPC1 = new JLabel("");
        GridBagConstraints constraints90 = new GridBagConstraints();
        constraints90.anchor = GridBagConstraints.WEST;
        constraints90.insets = new Insets(10, 0, 10, 300);
        constraints90.gridx = 1;
        constraints90.gridy = 8;
        registerPanel.add(labelPC1, constraints90);


        registerPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Register"));
    
        contentPane.add(registerPanel);


        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.add(fileInfoPanel);
        panel1.add(registerPanel);    
        contentPane.add(panel1);




        JPanel memoryPanel = new JPanel(new GridBagLayout());

        JLabel labelVariables = new JLabel("Variables");
        GridBagConstraints constraints9 = new GridBagConstraints();
        constraints9.anchor = GridBagConstraints.WEST;
        constraints9.insets = new Insets(10, 0, 10, 0);
        constraints9.gridx = 0;
        constraints9.gridy = 0;
        memoryPanel.add(labelVariables, constraints9);

        JTextArea data_text = new JTextArea(5, 20);
        data_text.setEditable(false);
        JScrollPane scrollPane2 = new JScrollPane(data_text);

        GridBagConstraints constraints10 = new GridBagConstraints();
        constraints10.gridx = 0;
        constraints10.gridy = 1;
        constraints10.gridwidth = 2;
        memoryPanel.add(scrollPane2, constraints10);

        JLabel labelStack = new JLabel("Stack");
        GridBagConstraints constraints11 = new GridBagConstraints();
        constraints11.anchor = GridBagConstraints.WEST;
        constraints11.insets = new Insets(10, 0, 10, 0);
        constraints11.gridx = 0;
        constraints11.gridy = 2;
        memoryPanel.add(labelStack, constraints11);

        JTextArea stack_text = new JTextArea(5, 20);
        stack_text.setEditable(false);
        JScrollPane scrollPane3 = new JScrollPane(stack_text);

        GridBagConstraints constraints12 = new GridBagConstraints();
        constraints12.gridx = 0;
        constraints12.gridy = 3;
        constraints12.gridwidth = 2;
        memoryPanel.add(scrollPane3, constraints12);

        memoryPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Memory info"));

        contentPane.add(memoryPanel);




        JPanel buttonPanel = new JPanel(new GridBagLayout());

        JButton button1 = new JButton("Load File");
        GridBagConstraints constraints13 = new GridBagConstraints();
        constraints13.gridx = 0;
        constraints13.gridy = 0;
        constraints13.insets = new Insets(10, 0, 10, 0);
        buttonPanel.add(button1, constraints13);
        button1.addActionListener(new ActionListener() {
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

                

        JButton button2 = new JButton("Check File");
        GridBagConstraints constraints14 = new GridBagConstraints();
        constraints14.gridx = 0;
        constraints14.gridy = 1;
        constraints14.insets = new Insets(10, 0, 10, 0);
        buttonPanel.add(button2, constraints14);
        button2.setEnabled(false);
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

        JButton button3 = new JButton("Simulate");
        GridBagConstraints constraints15 = new GridBagConstraints();
        constraints15.gridx = 0;
        constraints15.gridy = 2;
        constraints15.insets = new Insets(10, 0, 10, 0);
        buttonPanel.add(button3, constraints15);
        button3.setEnabled(false);
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

        JButton button4 = new JButton("Step Simulation");
        GridBagConstraints constraints16 = new GridBagConstraints();
        constraints16.gridx = 0;
        constraints16.gridy = 3;
        constraints16.insets = new Insets(10, 0, 10, 0);
        buttonPanel.add(button4, constraints16);
        button4.setEnabled(false);
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

        contentPane.add(buttonPanel);


        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.add(memoryPanel);
        containerPanel.add(buttonPanel);
        contentPane.add(containerPanel);



        JPanel BigPanel = new JPanel();
        BigPanel.setLayout(new BoxLayout(BigPanel, BoxLayout.X_AXIS));
        BigPanel.add(panel1);
        BigPanel.add(containerPanel);  
        contentPane.add(BigPanel);
        

        
        Font newLabelFont = new Font(BigPanel.getFont().getName(), Font.ITALIC, BigPanel.getFont().getSize());

        file_name.setFont(newLabelFont);
        count_pc.setFont(newLabelFont);
        count_t0.setFont(newLabelFont);
        count_t1.setFont(newLabelFont);
        count_t2.setFont(newLabelFont);
        count_t3.setFont(newLabelFont);
        labelnext.setFont(newLabelFont);
        status.setFont(newLabelFont);

        Font newLabelFont1 = new Font(BigPanel.getFont().getName(), Font.PLAIN, BigPanel.getFont().getSize());

        labelName.setFont(newLabelFont1);
        labelPC.setFont(newLabelFont1);
        labelT0.setFont(newLabelFont1);
        labelT1.setFont(newLabelFont1);
        labelT2.setFont(newLabelFont1);
        labelT3.setFont(newLabelFont1);
        labelStatus.setFont(newLabelFont1);
        labelNext.setFont(newLabelFont1);
        labelVariables.setFont(newLabelFont1);
        labelStack.setFont(newLabelFont1);
        labelcode.setFont(newLabelFont1);



        
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    

    public static void main(String[] args) {
        MyInterface myInterface = new MyInterface();
        myInterface.setVisible(true);
    }
}
