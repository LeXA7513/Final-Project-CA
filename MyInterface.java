import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MyInterface extends JFrame {

    public MyInterface() {
        super("Assembly Simulator");

        // create a content pane with BoxLayout manager
        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
    
        // create a new panel with GridBagLayout manager for File Info
        JPanel fileInfoPanel = new JPanel(new GridBagLayout());
    
        JLabel labelName = new JLabel("File Name");
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 0, 5, 20);
        constraints.gridx = 0;
        constraints.gridy = 0;
        fileInfoPanel.add(labelName, constraints);

        JLabel labelname = new JLabel("Empty");
        GridBagConstraints constraints01 = new GridBagConstraints();
        constraints01.anchor = GridBagConstraints.WEST;
        constraints01.insets = new Insets(0, 0, 10, 20);
        constraints01.gridx = 0;
        constraints01.gridy = 5;
        fileInfoPanel.add(labelname, constraints01);
    
        // create a label for Status
        JLabel labelStatus = new JLabel("File Status");
        GridBagConstraints constraints0 = new GridBagConstraints();
        constraints0.anchor = GridBagConstraints.WEST;
        constraints0.insets = new Insets(10, 0, 5, 20);
        constraints0.gridx = 0;
        constraints0.gridy = 6;
        fileInfoPanel.add(labelStatus, constraints0);

        JLabel labelstatus = new JLabel("No File");
        GridBagConstraints constraints02 = new GridBagConstraints();
        constraints02.anchor = GridBagConstraints.WEST;
        constraints02.insets = new Insets(0, 0, 10, 20);
        constraints02.gridx = 0;
        constraints02.gridy = 7;
        fileInfoPanel.add(labelstatus, constraints02);
    
        // create a label for Code
        JLabel labelCode = new JLabel("Code");
        GridBagConstraints constraints1 = new GridBagConstraints();
        constraints1.anchor = GridBagConstraints.WEST;
        constraints1.insets = new Insets(10, 0, 10, 20);
        constraints1.gridx = 0;
        constraints1.gridy = 10;
        fileInfoPanel.add(labelCode, constraints1);
    
        // create a text area with scroll pane
        JTextArea textArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(textArea);
        GridBagConstraints constraints2 = new GridBagConstraints();
        constraints2.anchor = GridBagConstraints.WEST;
        constraints2.insets = new Insets(0, 0, 0, 0);
        constraints2.gridx = 0;
        constraints2.gridy = 12;
        fileInfoPanel.add(scrollPane, constraints2);
    
        // set border for the panel
        fileInfoPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "File Info"));
    
        // add the panel to the content pane
        contentPane.add(fileInfoPanel);
    
        // create a new panel with GridBagLayout manager for Register
        JPanel registerPanel = new JPanel(new GridBagLayout());
    
        // create a label for Next instruction
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
    
        // create a label for T0
        JLabel labelT0 = new JLabel("t0");
        GridBagConstraints constraints4 = new GridBagConstraints();
        constraints4.anchor = GridBagConstraints.WEST;
        constraints4.insets = new Insets(10, 0, 10, 0);
        constraints4.gridx = 0;
        constraints4.gridy = 3;
        registerPanel.add(labelT0, constraints4);

        JLabel labelt0 = new JLabel("0");
        GridBagConstraints constraints03 = new GridBagConstraints();
        constraints03.anchor = GridBagConstraints.WEST;
        constraints03.insets = new Insets(10, 0, 10, 0);
        constraints03.gridx = 1;
        constraints03.gridy = 3;
        registerPanel.add(labelt0, constraints03);

        // create a label for T1
        JLabel labelT1 = new JLabel("t1");
        GridBagConstraints constraints5 = new GridBagConstraints();
        constraints5.anchor = GridBagConstraints.WEST;
        constraints5.insets = new Insets(10, 0, 10, 0);
        constraints5.gridx = 0;
        constraints5.gridy = 4;
        registerPanel.add(labelT1, constraints5);

        JLabel labelt1 = new JLabel("0");
        GridBagConstraints constraints04 = new GridBagConstraints();
        constraints04.anchor = GridBagConstraints.WEST;
        constraints04.insets = new Insets(10, 0, 10, 0);
        constraints04.gridx = 1;
        constraints04.gridy = 4;
        registerPanel.add(labelt1, constraints04);

        // create a label for T2
        JLabel labelT2 = new JLabel("t2");
        GridBagConstraints constraints6 = new GridBagConstraints();
        constraints6.anchor = GridBagConstraints.WEST;
        constraints6.insets = new Insets(10, 0, 10, 0);
        constraints6.gridx = 0;
        constraints6.gridy = 5;
        registerPanel.add(labelT2, constraints6);

        JLabel labelt2 = new JLabel("0");
        GridBagConstraints constraints05 = new GridBagConstraints();
        constraints05.anchor = GridBagConstraints.WEST;
        constraints05.insets = new Insets(10, 0, 10, 0);
        constraints05.gridx = 1;
        constraints05.gridy = 5;
        registerPanel.add(labelt2, constraints05);

        // create a label for T3
        JLabel labelT3 = new JLabel("t3");
        GridBagConstraints constraints7 = new GridBagConstraints();
        constraints7.anchor = GridBagConstraints.WEST;
        constraints7.insets = new Insets(10, 0, 10, 0);
        constraints7.gridx = 0;
        constraints7.gridy = 6;
        registerPanel.add(labelT3, constraints7);

        JLabel labelt3 = new JLabel("0");
        GridBagConstraints constraints06 = new GridBagConstraints();
        constraints06.anchor = GridBagConstraints.WEST;
        constraints06.insets = new Insets(10, 0, 10, 0);
        constraints06.gridx = 1;
        constraints06.gridy = 6;
        registerPanel.add(labelt3, constraints06);

        // create a label for PC
        JLabel labelPC = new JLabel("PC");
        GridBagConstraints constraints8 = new GridBagConstraints();
        constraints8.anchor = GridBagConstraints.WEST;
        constraints8.insets = new Insets(10, 0, 10, 30);
        constraints8.gridx = 0;
        constraints8.gridy = 7;
        registerPanel.add(labelPC, constraints8);

        JLabel labelpc = new JLabel("0");
        GridBagConstraints constraints07 = new GridBagConstraints();
        constraints07.anchor = GridBagConstraints.WEST;
        constraints07.insets = new Insets(10, 0, 10, 30);
        constraints07.gridx = 1;
        constraints07.gridy = 7;
        registerPanel.add(labelpc, constraints07);

        // set border for the panel
        registerPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Register"));
    
        // add the panel to the content pane
        contentPane.add(registerPanel);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.add(fileInfoPanel);
        panel1.add(registerPanel);    
        contentPane.add(panel1);

        JPanel memoryPanel = new JPanel(new GridBagLayout());

        // create a label for Variables
        JLabel labelVariables = new JLabel("Variables");
        GridBagConstraints constraints9 = new GridBagConstraints();
        constraints9.anchor = GridBagConstraints.WEST;
        constraints9.insets = new Insets(10, 0, 10, 0);
        constraints9.gridx = 0;
        constraints9.gridy = 0;
        memoryPanel.add(labelVariables, constraints9);

        // create a text area with scrollpane
        JTextArea textArea2 = new JTextArea(5, 20);
        textArea2.setEditable(false);
        JScrollPane scrollPane2 = new JScrollPane(textArea2);

        GridBagConstraints constraints10 = new GridBagConstraints();
        constraints10.gridx = 0;
        constraints10.gridy = 1;
        constraints10.gridwidth = 2;
        memoryPanel.add(scrollPane2, constraints10);

        // create a label for Stack
        JLabel labelStack = new JLabel("Stack");
        GridBagConstraints constraints11 = new GridBagConstraints();
        constraints11.anchor = GridBagConstraints.WEST;
        constraints11.insets = new Insets(10, 0, 10, 0);
        constraints11.gridx = 0;
        constraints11.gridy = 2;
        memoryPanel.add(labelStack, constraints11);

        // create a text area with scrollpane
        JTextArea textArea3 = new JTextArea(5, 20);
        textArea3.setEditable(false);
        JScrollPane scrollPane3 = new JScrollPane(textArea3);

        GridBagConstraints constraints12 = new GridBagConstraints();
        constraints12.gridx = 0;
        constraints12.gridy = 3;
        constraints12.gridwidth = 2;
        memoryPanel.add(scrollPane3, constraints12);

        // set border for the panel
        memoryPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Memory info"));

        // add the panel to the content pane
        contentPane.add(memoryPanel);

        JPanel buttonPanel = new JPanel(new GridBagLayout());

        JButton button1 = new JButton("Load File");
        GridBagConstraints constraints13 = new GridBagConstraints();
        constraints13.gridx = 0;
        constraints13.gridy = 0;
        constraints13.insets = new Insets(10, 0, 10, 0);
        buttonPanel.add(button1, constraints13);

        JButton button2 = new JButton("Check File");
        GridBagConstraints constraints14 = new GridBagConstraints();
        constraints14.gridx = 0;
        constraints14.gridy = 1;
        constraints14.insets = new Insets(10, 0, 10, 0);
        buttonPanel.add(button2, constraints14);

        JButton button3 = new JButton("Simulate");
        GridBagConstraints constraints15 = new GridBagConstraints();
        constraints15.gridx = 0;
        constraints15.gridy = 2;
        constraints15.insets = new Insets(10, 0, 10, 0);
        buttonPanel.add(button3, constraints15);

        JButton button4 = new JButton("Step Simulation");
        GridBagConstraints constraints16 = new GridBagConstraints();
        constraints16.gridx = 0;
        constraints16.gridy = 3;
        constraints16.insets = new Insets(10, 0, 10, 0);
        buttonPanel.add(button4, constraints16);

        // add button to the content pane
        contentPane.add(buttonPanel);

        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.add(memoryPanel);
        containerPanel.add(buttonPanel);
        contentPane.add(containerPanel);

        JPanel BigPanel = new JPanel();
        BigPanel.setLayout(new BoxLayout(BigPanel, BoxLayout.X_AXIS));
    
        // add newPanel and newPanel3 to containerPanel
        BigPanel.add(panel1);
        BigPanel.add(containerPanel);
    
        // add containerPanel to the content pane
        contentPane.add(BigPanel);
        
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    

    public static void main(String[] args) {
        MyInterface myInterface = new MyInterface();
        myInterface.setVisible(true);
    }
}
