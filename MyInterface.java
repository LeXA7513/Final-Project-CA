import javax.swing.*;
import java.awt.*;

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
        constraints.insets = new Insets(10, 0, 10, 20);
        constraints.gridx = 0;
        constraints.gridy = 0;
        fileInfoPanel.add(labelName, constraints);
    
        // create a label for Status
        JLabel labelStatus = new JLabel("File Status");
        GridBagConstraints constraints0 = new GridBagConstraints();
        constraints0.anchor = GridBagConstraints.WEST;
        constraints0.insets = new Insets(10, 0, 10, 20);
        constraints0.gridx = 0;
        constraints0.gridy = 4;
        fileInfoPanel.add(labelStatus, constraints0);
    
        // create a label for Code
        JLabel labelCode = new JLabel("Code");
        GridBagConstraints constraints1 = new GridBagConstraints();
        constraints1.anchor = GridBagConstraints.WEST;
        constraints1.insets = new Insets(10, 0, 10, 20);
        constraints1.gridx = 0;
        constraints1.gridy = 6;
        fileInfoPanel.add(labelCode, constraints1);
    
        // create a text area with scroll pane
        JTextArea textArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(textArea);
        GridBagConstraints constraints2 = new GridBagConstraints();
        constraints2.anchor = GridBagConstraints.WEST;
        constraints2.insets = new Insets(0, 0, 0, 0);
        constraints2.gridx = 0;
        constraints2.gridy = 7;
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
        constraints3.insets = new Insets(10, 0, 10, 20);
        constraints3.gridx = 0;
        constraints3.gridy = 0;
        registerPanel.add(labelNext, constraints3);
    
        // create a label for T0
        JLabel labelT0 = new JLabel("t0");
        GridBagConstraints constraints4 = new GridBagConstraints();
        constraints4.anchor = GridBagConstraints.WEST;
        constraints4.insets = new Insets(10, 0, 10, 20);
        constraints4.gridx = 0;
        constraints4.gridy = 1;
        registerPanel.add(labelT0, constraints4);
    
        // set border for the panel
        registerPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Register"));
    
        // add the panel to the content pane
        contentPane.add(registerPanel);
    
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    

    public static void main(String[] args) {
        MyInterface myInterface = new MyInterface();
        myInterface.setVisible(true);
    }
}
