import javax.swing.*;
import java.awt.*;

class Interface extends JFrame{

    private JLabel labelName = new JLabel("File Name");
    private JLabel labelStatus = new JLabel("File Status");
    private JLabel labelCode = new JLabel("Code");
    private JTextArea textField1 = new JTextArea(20, 20); 

    private JLabel labelNextInstruction = new JLabel("Next Instruction");
    private JLabel labelT0 = new JLabel("t0");
    private JLabel labelT1 = new JLabel("t1");
    private JLabel labelT2 = new JLabel("t2");
    private JLabel labelT3 = new JLabel("t3");
    private JLabel labelPC = new JLabel("PC");

    private JLabel labelVariables = new JLabel("Variables");
    private JTextArea textField2 = new JTextArea(10, 10);
    private JLabel labelStack = new JLabel("Stack");
    private JTextArea textField3 = new JTextArea(10, 10);

    private JButton button1 = new JButton("Load File");
    private JButton button2 = new JButton("Check File");
    private JButton button3 = new JButton("Simulate");
    private JButton button4 = new JButton("Step Simulation");

    public Interface(){
        super("Assembly Simulator");
    
        // create a content pane with BoxLayout manager
        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
    
        // create a new panel with GridBagLayout manager for file info
        JPanel newPanel = new JPanel(new GridBagLayout());
    
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 0, 10, 20);
    
        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        newPanel.add(labelName, constraints);
    
        constraints.gridy = 4;
        newPanel.add(labelStatus, constraints);
    
        constraints.gridx = 0;
        constraints.gridy = 7;
        newPanel.add(labelCode, constraints);
    
        constraints.gridy = 8;
        newPanel.add(textField1, constraints);
    
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridheight = 3;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.LINE_START;
    
        // set border for the panel
        newPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "File Info"));
    
        // create a new panel with GridBagLayout manager for memory info
        JPanel newPanel3 = new JPanel(new GridBagLayout());
    
        GridBagConstraints constraints3 = new GridBagConstraints();
        constraints3.anchor = GridBagConstraints.WEST;
        constraints3.insets = new Insets(10, 0, 10, 20);
    
        constraints3.gridy = 2;
        newPanel3.add(labelVariables, constraints3);

        constraints3.gridy = 3;
        newPanel3.add(textField2, constraints3);
    
        constraints3.gridx = 0;
        constraints3.gridy = 4;
        newPanel3.add(labelStack, constraints3);
        
        constraints3.gridy = 5;
        newPanel3.add(textField3, constraints3);

        constraints3.gridx = 1;
        constraints3.gridy = 0;
        constraints3.gridheight = 3;
        constraints3.gridwidth = 1;
        constraints3.fill = GridBagConstraints.LINE_END;
    
        // set border for the panel
        newPanel3.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Memory info"));
    
        // create a new panel with BoxLayout manager for newPanel and newPanel3
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.X_AXIS));
    
        // add newPanel and newPanel3 to containerPanel
        containerPanel.add(newPanel);
        containerPanel.add(newPanel3);
    
        // add containerPanel to the content pane
        contentPane.add(containerPanel);

        JPanel newPanel2 = new JPanel(new GridBagLayout());

        Container contentPane2 = getContentPane();
        contentPane2.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        GridBagConstraints constraints2 = new GridBagConstraints();
        constraints2.anchor = GridBagConstraints.WEST;
        constraints2.insets = new Insets(10, 10, 10, 10);

        // add components to the panel
        constraints2.gridx = 0;
        constraints2.gridy = 0;
        newPanel2.add(labelNextInstruction, constraints2);

        constraints2.gridy = 2;
        newPanel2.add(labelT0, constraints2);

        constraints2.gridx = 0;
        constraints2.gridy = 4;
        newPanel2.add(labelT1, constraints2);

        constraints2.gridy = 6;
        newPanel2.add(labelT2, constraints2);

        constraints2.gridy = 8;
        newPanel2.add(labelT3, constraints2);

        constraints2.gridy = 10;
        newPanel2.add(labelPC, constraints2);

        constraints2.gridx = 0;
        constraints2.gridy = 8;
        constraints2.gridwidth = 2;
        constraints2.anchor = GridBagConstraints.LINE_START;

        // set border for the panel
        newPanel2.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Registers"));

        
        

        // create a new panel with BoxLayout manager for newPanel and newPanel3
        JPanel containerPanel2 = new JPanel();
        containerPanel2.setLayout(new BoxLayout(containerPanel2, BoxLayout.X_AXIS));
    
        // add newPanel and newPanel3 to containerPanel
        containerPanel2.add(newPanel2);
        containerPanel2.add(button1);
        containerPanel2.add(button2);
        containerPanel2.add(button3);
        containerPanel2.add(button4);
    
        // add containerPanel to the content pane
        contentPane2.add(containerPanel2);

        pack();
        setLocationRelativeTo(null);
    }
     
    public static void main(String[] args) {
        // set look and feel to the system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
         
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Interface().setVisible(true);
            }
        });
    }
    

}